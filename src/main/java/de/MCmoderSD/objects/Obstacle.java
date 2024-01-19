package de.MCmoderSD.objects;


import de.MCmoderSD.main.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Obstacle {

    // Attributes
    private final BufferedImage image;
    private final Color color;
    private final Color hitboxColor;
    private final int width;
    private final int height;
    private final float speed;
    private final float y;

    // Variables
    private float x;


    // Constructors
    public Obstacle(Config config, BufferedImage image, int x, int y, float speed) {

        this.image = image;
        this.speed = speed;
        this.x = x;
        this.y = y;

        color = config.getObstacleColor();
        hitboxColor = config.getObstacleHitboxColor();

        width = image.getWidth();
        height = image.getHeight();
    }

    // Methods
    public void move() {
        x -= speed;
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