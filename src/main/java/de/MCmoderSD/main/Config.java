package de.MCmoderSD.main;

import de.MCmoderSD.utilities.image.ImageReader;
import de.MCmoderSD.utilities.image.ImageStreamer;
import de.MCmoderSD.utilities.json.JsonNode;
import de.MCmoderSD.utilities.json.JsonUtility;
import de.MCmoderSD.utilities.sound.AudioPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("unused")
public class Config {

    // Utilities
    private final AudioPlayer audioPlayer;

    // Constants
    public static String[] args;
    public static int width;
    public static int height;
    public static boolean resizable;
    public static Dimension size;

    // Game logic constants
    public static float jumpHeight;
    public static float gravity;
    public static float backgroundSpeed;
    public static float obstacleSpeed;
    public static float obstacleSpeedModifier;
    public static int obstacleSpawnRate;
    public static int maxFPS;

    // Assets
    public static BufferedImage icon;
    public static BufferedImage backgroundImage;
    public static BufferedImage raccoonImage;
    public static BufferedImage[] obstacleImages;

    // Colors
    public static Color raccoonColor;
    public static Color raccoonHitboxColor;
    public static Color obstacleColor;
    public static Color obstacleHitboxColor;
    public static Color backgroundColor;
    public static Color fontColor;
    public static Color scoreColor;
    public static Color fpsColor;

    // Language
    public static String title;
    public static String scorePrefix;
    public static String fpsPrefix;

    // Animations
    // public static ImageIcon rainbowAnimation;

    // Sounds
    /*
     * public stastic String dieSound;
     * public stastic String jumpSound;
     * public stastic String hitSound;
     * public stastic String pointSound;
     * public stastic String rainbowSound;
     * public stastic String backgroundMusic;
     */

    // Constructor
    public Config(String[] args) {
        this.args = args;

        JsonUtility jsonUtility = new JsonUtility();
        ImageReader imageReader = new ImageReader();
        audioPlayer = new AudioPlayer();

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
        obstacleSpawnRate = config.get("obstacleSpawnRate").asInt();
        maxFPS = config.get("maxFPS").asInt();

        // Assets
        obstacleImages = new BufferedImage[1];
        icon = imageReader.read(config.get("icon").asText());
        backgroundImage = imageReader.read(config.get("backgroundImage").asText());
        raccoonImage = imageReader.read(config.get("raccoonImage").asText());
        obstacleImages[0] = imageReader.read(config.get("garbageCanImage").asText());

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
        fpsPrefix = config.get("fpsPrefix").asText();
    }

    // Constructor asset streaming
    public Config(String[] args, String url) {
        this.args = args;

        JsonUtility jsonUtility = new JsonUtility(url);
        ImageStreamer imageStreamer = new ImageStreamer(url);
        audioPlayer = new AudioPlayer(url);

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
        obstacleSpawnRate = config.get("obstacleSpawnRate").asInt();
        maxFPS = config.get("maxFPS").asInt();

        // Assets
        obstacleImages = new BufferedImage[1];
        icon = imageStreamer.read(config.get("icon").asText());
        backgroundImage = imageStreamer.read(config.get("backgroundImage").asText());
        raccoonImage = imageStreamer.read(config.get("raccoonImage").asText());
        obstacleImages[0] = imageStreamer.read(config.get("garbageCanImage").asText());

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
        fpsPrefix = config.get("fpsPrefix").asText();
    }

    // Getter
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}