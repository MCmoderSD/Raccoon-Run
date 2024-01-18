package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Background;
import de.MCmoderSD.objects.Obstacle;
import de.MCmoderSD.objects.Raccoon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    // Associations
    private final Frame frame;
    private final Config config;
    private final InputHandler inputHandler;
    private final Game game;

    // Attributes
    private final JLabel scoreLabel;

    // Constructor
    public GamePanel(Frame frame, Config config) {
        this.frame = frame;
        this.config = config;

        // Init Panel

        setLayout(null);
        setPreferredSize(config.getSize());
        setFocusable(true);
        requestFocus();
        frame.add(this);
        frame.pack();

        // Score Label
        scoreLabel = new JLabel("Score: ");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(config.getScoreColor());
        scoreLabel.setSize(getWidth() / 8, getHeight() / 40);
        scoreLabel.setLocation(getWidth() - scoreLabel.getWidth() - 10, 10);
        add(scoreLabel);

        // Init Game and Controls
        game = new Game(this, config);
        inputHandler = new InputHandler(frame, game);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g = (Graphics2D) graphics;

        // Objects
        Background background = game.getBackground();
        Raccoon raccoon = game.getRaccoon();
        ArrayList<Obstacle> obstacles = game.getObstacles();


        // Render

        // Draw Game
        g.setColor(background.getColor());
        g.fillRect(background.getX(), background.getY(), background.getWidth(), background.getHeight());
        g.drawImage(background.getImage(), background.getX(), background.getY(), background.getWidth(), background.getHeight(), null);

        // Draw Obstacles
        for (Obstacle obstacle : obstacles) {
            g.setColor(obstacle.getColor());
            g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            g.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight(), null);
        }

        // Draw Raccoon
        g.setColor(raccoon.getColor());
        g.fillRect(raccoon.getX(), raccoon.getY(), raccoon.getWidth(), raccoon.getHeight());
        g.drawImage(raccoon.getImage(), raccoon.getX(), raccoon.getY(), raccoon.getWidth(), raccoon.getHeight(), null);

        // Draw Hitbox
        if (game.isDebug()) {
            for (Obstacle obstacle : obstacles) {
                g.setColor(obstacle.getHitboxColor());
                g.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            }

            g.setColor(raccoon.getHitboxColor());
            g.drawRect(raccoon.getX(), raccoon.getY(), raccoon.getWidth(), raccoon.getHeight());
        }

        scoreLabel.setText("Score: " + game.getScore());
        paintComponents(g);
    }
}