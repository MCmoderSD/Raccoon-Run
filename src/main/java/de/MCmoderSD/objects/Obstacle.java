package de.MCmoderSD.objects;


import de.MCmoderSD.main.Config;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Obstacle {

    // Attributes
    private final BufferedImage image;
    private final Color color;
    private final Color hitboxColor;
    private final int width;
    private final int height;
    private final float speed;
    // Variables
    private float x;
    private float y;
    private boolean collided;
    private boolean passed;


    // Constructors
    public Obstacle(Config config, float speed) {

        BufferedImage[] obstacleImages = config.getObstacleImages();

        image = obstacleImages[(int) Math.round(Math.random() * (obstacleImages.length - 1))];

        color = config.getObstacleColor();
        hitboxColor = config.getObstacleHitboxColor();

        collided = false;

        width = image.getWidth();
        height = image.getHeight();

        this.speed = speed;
    }

    // Methods
    public void move() {
        x -= speed;
    }

    // Setter
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void collision() {
        collided = true;
    }

    public void pass() {
        passed = true;
    }

    // Getter
    public BufferedImage getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return Math.toIntExact(Math.round(x));
    }

    public int getY() {
        return Math.toIntExact(Math.round(y));
    }

    public boolean isCollided() {
        return collided;
    }

    public boolean isPassed() {
        return passed;
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }

    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), width, height);
    }
}