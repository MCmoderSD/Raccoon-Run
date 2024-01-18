package de.MCmoderSD.objects;

import de.MCmoderSD.main.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Raccoon {

    // Attributes
    private final BufferedImage image;
    private final Color color;
    private final Color hitboxColor;
    private final int width;
    private final int height;
    private final float jumpHeight;
    private final float gravity;

    // Variables
    private float x;
    private float y;
    private float speed;

    // Constructors
    public Raccoon(Config config, int x, int y) {
        color = config.getRaccoonColor();
        hitboxColor = config.getRaccoonHitboxColor();
        image = config.getRaccoonImage();
        width = image.getWidth();
        height = image.getHeight();

        jumpHeight = config.getJumpHeight();
        gravity = config.getGravity();

        this.x = x;
        this.y = y;

        speed = 0;
    }

    // Methods
    public void jump() {
        speed = -jumpHeight;
    }

    public void fall() {
        speed += gravity;
        y += speed;
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

    public Rectangle getHitbox() {
        return new Rectangle(getX(), getY(), width, height);
    }

    public Color getColor() {
        return color;
    }

    public Color getHitboxColor() {
        return hitboxColor;
    }
}