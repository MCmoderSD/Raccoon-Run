package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;
import de.MCmoderSD.main.Config;
import de.MCmoderSD.objects.Background;
import de.MCmoderSD.objects.Obstacle;
import de.MCmoderSD.objects.Raccoon;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    // Associations
    private final Game game;

    // Attributes
    private final JLabel scoreLabel;
    private final JLabel fpsLabel;

    // Constructor
    public GamePanel(Frame frame, Config config) {
        super();

        // Init Panel
        setLayout(null);
        setPreferredSize(Config.size);
        frame.add(this);
        frame.pack();

        // Score Label
        scoreLabel = new JLabel(Config.scorePrefix);
        scoreLabel.setFont(new Font("Roboto", Font.PLAIN, Config.height / 40));
        scoreLabel.setForeground(Config.scoreColor);
        scoreLabel.setSize(getWidth() / 8, getHeight() / 40);
        scoreLabel.setLocation(getWidth() - scoreLabel.getWidth() - 10, 10);
        add(scoreLabel);

        // FPS Label
        fpsLabel = new JLabel(Config.fpsPrefix);
        fpsLabel.setFont(new Font("Roboto", Font.PLAIN, Config.height / 40));
        fpsLabel.setForeground(Config.fpsColor);
        fpsLabel.setSize(getWidth() / 8, getHeight() / 40);
        fpsLabel.setLocation(10, 10);
        add(fpsLabel);

        // Init Game and Controls
        game = new Game(this, config);
        new InputHandler(frame, game);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D g = (Graphics2D) graphics;

        // Objects
        ArrayList<Background> backgrounds = game.getBackgrounds();
        ArrayList<Obstacle> obstacles = game.getObstacles();
        Raccoon raccoon = game.getRaccoon();

        // Draw Background
        for (Background background : backgrounds) {
            g.setColor(background.getColor());
            g.fillRect(background.getX(), background.getY(), background.getWidth(), background.getHeight());
            g.drawImage(background.getImage(), background.getX(), background.getY(), background.getWidth(),
                    background.getHeight(), null);
        }

        // Draw Obstacles
        for (Obstacle obstacle : obstacles) {
            g.setColor(obstacle.getColor());
            // g.fillRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(),
            // obstacle.getHeight());
            g.drawImage(obstacle.getImage(), obstacle.getX(), obstacle.getY(), obstacle.getWidth(),
                    obstacle.getHeight(), null);
        }

        // Draw Raccoon
        g.setColor(raccoon.getColor());
        g.fillRect(raccoon.getX(), raccoon.getY(), raccoon.getWidth(), raccoon.getHeight());
        g.drawImage(raccoon.getImage(), raccoon.getX(), raccoon.getY(), raccoon.getWidth(), raccoon.getHeight(), null);

        // Draw Hitbox
        if (game.isHitbox()) {
            for (Obstacle obstacle : obstacles) {
                g.setColor(obstacle.getHitboxColor());
                g.drawRect(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight());
            }

            g.setColor(raccoon.getHitboxColor());
            g.drawRect(raccoon.getX(), raccoon.getY(), raccoon.getWidth(), raccoon.getHeight());
        }

        scoreLabel.setText(Config.scorePrefix + game.getScore());

        if (game.isShowFPS()) {
            fpsLabel.setText(Config.fpsPrefix + game.getFps());
            fpsLabel.setVisible(true);
        } else
            fpsLabel.setVisible(false);

        paintComponents(g);
    }
}