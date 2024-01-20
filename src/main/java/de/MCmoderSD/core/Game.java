package de.MCmoderSD.core;

import de.MCmoderSD.UI.GamePanel;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.main.Main;
import de.MCmoderSD.objects.Background;
import de.MCmoderSD.objects.Obstacle;
import de.MCmoderSD.objects.Raccoon;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {

    // Associations
    private final GamePanel gamePanel;
    private final Config config;

    // Utilities
    private final Random random;

    // Attributes
    private final ArrayList<Double> events;
    private final ArrayList<Double> keys;
    private final ArrayList<Background> backgrounds;
    private final ArrayList<Obstacle> obstacles;
    private final Raccoon raccoon;

    // Constants
    private final double tickrate;
    private final boolean isLinux;

    // Variables
    private int frameRate;
    private int fps;
    private int score;
    private int obstacleSpawnTimer;
    private double speedModifier;
    private boolean gameStarted;
    private boolean isPaused;
    private boolean sound;
    private boolean debug;
    private boolean hitbox;
    private boolean showFPS;


    // Constructor
    public Game(GamePanel gamePanel, Config config) {

        this.gamePanel = gamePanel;
        this.config = config;

        // Init Utilities
        random = new Random();

        // Init Constants
        tickrate = 2777778;

        // Init Game Variables
        isLinux = System.getProperty("os.name").equals("Linux");
        gameStarted = false;
        isPaused = false;
        sound = true;
        debug = false;
        hitbox = false;
        showFPS = false;
        obstacleSpawnTimer = 0;
        speedModifier = 1;
        score = 0;
        frameRate = 1;

        // Init Attributes
        events = new ArrayList<>();
        keys = new ArrayList<>();
        backgrounds = new ArrayList<>();
        obstacles = new ArrayList<>();
        raccoon = new Raccoon(config);
        raccoon.setLocation(Math.toIntExact(Math.round(config.getWidth() * 0.1)), Math.toIntExact(Math.round(config.getHeight() * 0.5)) - raccoon.getHeight());

        // Init Backgrounds
        backgrounds.add(new Background(config, 0, 0));
        while (backgrounds.get(backgrounds.size() - 1).getX() + backgrounds.get(backgrounds.size() - 1).getWidth() < config.getWidth())
            backgrounds.add(new Background(config, backgrounds.get(backgrounds.size() - 1).getX() + backgrounds.get(backgrounds.size() - 1).getWidth(), 0));

        new Thread(this).start();
    }


    @Override
    public void run() {
        while (Main.IS_RUNNING) {

            // Timer Variables
            double delta = 0;
            long current;
            long timer = 0;
            long now = System.nanoTime();
            int renderedFrames = 0;

            // Game Loop
            while (gameStarted) {

                // Timer
                current = System.nanoTime();
                delta += (current - now) / (tickrate / speedModifier);
                timer += current - now;
                now = current;


                // Tick
                if (delta >= 1) {
                    if (isLinux) Toolkit.getDefaultToolkit().sync();


                    /* <-- Game Loop Start --> */

                    // Game Tick Event
                    double event = gameTick();

                    // Update Frame
                    boolean update = renderedFrames < config.getMaxFPS();
                    int modulo = renderedFrames % frameRate;
                    if (modulo != 0 && update) renderedFrames++;
                    if (modulo == 0 && update) {
                        if (isLinux) Toolkit.getDefaultToolkit().sync();
                        gamePanel.repaint();
                        renderedFrames++;
                    }

                    // FPS Counter
                    if (timer >= 1000000000) {
                        timer = 0;
                        fps = renderedFrames / frameRate;
                        renderedFrames = 0;
                    }

                    // Anti Cheat
                    events.add(event);

                    /* <-- Game Loop End --> */


                    if (isLinux) Toolkit.getDefaultToolkit().sync();
                    delta--;
                }
            }

            // Delay to prevent 100% CPU usage
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private double gameTick() {

        // Generate Event
        double event = random.nextDouble() * System.nanoTime();

        if (!isPaused) {
            // Game Tick Event

            // Temp lists for removal
            ArrayList<Background> backgroundsToRemove = new ArrayList<>();
            ArrayList<Obstacle> obstaclesToRemove = new ArrayList<>();

            // Remove elements that are out of bounds
            for (Background background : backgrounds)
                if (background.getX() + background.getWidth() < 0) backgroundsToRemove.add(background);
            for (Obstacle obstacle : obstacles)
                if (obstacle.getX() + obstacle.getWidth() < 0) obstaclesToRemove.add(obstacle);

            // Remove elements from original lists
            backgrounds.removeAll(backgroundsToRemove);
            obstacles.removeAll(obstaclesToRemove);

            // Background Spawn
            Background lastBackground = backgrounds.get(backgrounds.size() - 1);
            if (lastBackground.getX() + lastBackground.getWidth() <= config.getWidth())
                backgrounds.add(new Background(config, config.getWidth(), 0));

            // Obstacle Spawn
            if (obstacleSpawnTimer >= config.getObstacleSpawnRate()) {
                Obstacle obstacle = new Obstacle(config, 2);
                obstacle.setLocation(config.getWidth(), Math.toIntExact(Math.round(config.getHeight() * 0.5)) - obstacle.getHeight());
                obstacles.add(obstacle);
                obstacleSpawnTimer = 0;
            } else obstacleSpawnTimer++;

            // Collision Detection
            for (Obstacle obstacle : obstacles) if (raccoon.getHitbox().intersects(obstacle.getHitbox())) {
                obstacle.collision();
                gameStarted = false;
            }

            // Score
            for (Obstacle obstacle : obstacles) if (obstacle.getX() + obstacle.getWidth() < raccoon.getX() && !obstacle.isCollided() && !obstacle.isPassed()) {
                obstacle.pass();
                score++;
            }

            // Move Objects
            for (Background background : backgrounds) background.move();
            for (Obstacle obstacle : obstacles) obstacle.move();
            if (raccoon.getY() <= config.getHeight() * 0.5) raccoon.fall();
            raccoon.move(config.getHeight() * 0.5);
        }

        return event;
    }

    // Triggers
    public void jump() {
        if (!gameStarted) gameStarted = true;
        if (raccoon.getY() >= config.getHeight() * 0.5 - raccoon.getHeight()) raccoon.jump();
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public void toggleDebug() {
        debug = !debug;
    }

    public void toggleHitboxes() {
        hitbox = !hitbox;
    }

    public void toggleFPS() {
        showFPS = !showFPS;
    }

    // Getters
    public int getScore() {
        return score;
    }

    public int getFps() {
        return fps;
    }

    public Raccoon getRaccoon() {
        return raccoon;
    }

    public ArrayList<Background> getBackgrounds() {
        return backgrounds;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean isDebug() {
        return debug;
    }

    public boolean isHitbox() {
        return hitbox;
    }

    public boolean isShowFPS() {
        return showFPS;
    }
}
