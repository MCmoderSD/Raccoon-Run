package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.Color;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Background {

    // Attributes
    private final BufferedImage image;
    private final Color color;
    private final int width;
    private final int height;
    private final float speed;
    private final float y;

    // Variables
    private float x;

    // Constructors
    public Background(Config config, int x, int y) {
        image = config.getBackgroundImage();
        color = config.getBackgroundColor();

        speed = config.getBackgroundSpeed();

        width = image.getWidth();
        height = image.getHeight();

        this.x = x;
        this.y = y;
    }

    // Methods
    public void move() {
        x -= speed;
    }

    // Getter
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return Math.toIntExact(Math.round(x));
    }

    public int getY() {
        return Math.toIntExact(Math.round(y));
    }
}