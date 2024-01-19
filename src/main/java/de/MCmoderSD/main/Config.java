package de.MCmoderSD.main;


import de.MCmoderSD.utilities.image.ImageReader;
import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Constants
    private final String[] args;
    private final int width;
    private final int height;
    private final boolean resizable;
    private final Dimension size;

    // Game logic constants
    private final float jumpHeight;
    private final float gravity;
    private final float backgroundSpeed;
    private final float obstacleSpeed;
    private final float obstacleSpeedModifier;
    private final float obstacleSpawnRate;
    private final int maxFPS;

    // Assets
    private final BufferedImage icon;
    private final BufferedImage backgroundImage;
    private final BufferedImage raccoonImage;

    // Colors
    private final Color raccoonColor;
    private final Color raccoonHitboxColor;
    private final Color obstacleColor;
    private final Color obstacleHitboxColor;
    private final Color backgroundColor;
    private final Color fontColor;
    private final Color scoreColor;
    private final Color fpsColor;

    // Language
    private final String title;
    private final String scorePrefix;

    // Animations
    //private final ImageIcon rainbowAnimation;

    // Sounds
    /*private final String dieSound;
    private final String jumpSound;
    private final String hitSound;
    private final String pointSound;
    private final String rainbowSound;
    private final String backgroundMusic;*/

    // Constructor
    public Config(String[] args) {
        this.args = args;

        JsonUtility jsonUtility = new JsonUtility();
        ImageReader imageReader = new ImageReader();

        JsonNode config = jsonUtility.load("/config/default.json");

        // Constants
        width = config.get("width").asInt();
        height = config.get("height").asInt();
        resizable = config.get("resizable").asBoolean();
        size = new Dimension(width, height);

        // Game logic
        jumpHeight = config.get("jumpHeight").asFloat();
        gravity = config.get("gravity").asFloat();
        backgroundSpeed = config.get("backgroundSpeed").asFloat();
        obstacleSpeed = config.get("obstacleSpeed").asFloat();
        obstacleSpeedModifier = config.get("obstacleSpeedModifier").asFloat();
        obstacleSpawnRate = config.get("obstacleSpawnRate").asFloat();
        maxFPS = config.get("maxFPS").asInt();

        // Assets
        icon = imageReader.read(config.get("icon").asText());
        backgroundImage = imageReader.read(config.get("backgroundImage").asText());
        raccoonImage = imageReader.read(config.get("raccoonImage").asText());

        // Colors
        raccoonColor = config.get("raccoonColor").asColor();
        raccoonHitboxColor = config.get("raccoonHitboxColor").asColor();
        obstacleColor = config.get("obstacleColor").asColor();
        obstacleHitboxColor = config.get("obstacleHitboxColor").asColor();
        backgroundColor = config.get("backgroundColor").asColor();
        fontColor = config.get("fontColor").asColor();
        scoreColor = config.get("scoreColor").asColor();
        fpsColor = config.get("fpsColor").asColor();

        // Language
        title = config.get("title").asText();
        scorePrefix = config.get("scorePrefix").asText();
    }

    // Constants
    public String[] getArgs() {
        return args;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isResizable() {
        return resizable;
    }

    public Dimension getSize() {
        return size;
    }


    // Game logic
    public float getJumpHeight() {
        return jumpHeight;
    }

    public float getGravity() {
        return gravity;
    }

    public float getBackgroundSpeed() {
        return backgroundSpeed;
    }

    public float getObstacleSpeed() {
        return obstacleSpeed;
    }

    public float getObstacleSpeedModifier() {
        return obstacleSpeedModifier;
    }

    public float getObstacleSpawnRate() {
        return obstacleSpawnRate;
    }

    public int getMaxFPS() {
        return maxFPS;
    }


    // Assets
    public BufferedImage getIcon() {
        return icon;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public BufferedImage getRaccoonImage() {
        return raccoonImage;
    }


    // Colors
    public Color getRaccoonColor() {
        return raccoonColor;
    }

    public Color getRaccoonHitboxColor() {
        return raccoonHitboxColor;
    }

    public Color getObstacleColor() {
        return obstacleColor;
    }

    public Color getObstacleHitboxColor() {
        return obstacleHitboxColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getScoreColor() {
        return scoreColor;
    }

    public Color getFpsColor() {
        return fpsColor;
    }


    // Language
    public String getTitle() {
        return title;
    }
    public String getScorePrefix() {
        return scorePrefix;
    }
}