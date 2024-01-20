package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    // Associations
    private final Game game;

    // Attributes
    private boolean f3Pressed;

    public InputHandler(Frame frame, Game game) {
        frame.addKeyListener(this);
        this.game = game;
        f3Pressed = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        // Variables
        if (e.getKeyCode() == KeyEvent.VK_F3) f3Pressed = true;

        // Exit
        boolean strgQ = e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Q;
        boolean altQ = e.isAltDown() && e.getKeyCode() == KeyEvent.VK_Q;
        boolean altF4 = e.isAltDown() && e.getKeyCode() == KeyEvent.VK_F4;
        if (strgQ || altQ || altF4) System.exit(0);

        // Pause
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) game.togglePause();

        // Debug
        if (f3Pressed && e.getKeyCode() == KeyEvent.VK_B) game.toggleHitboxes();
        if (f3Pressed && e.getKeyCode() == KeyEvent.VK_F) game.toggleFPS();

        // Movement
        boolean space = e.getKeyCode() == KeyEvent.VK_SPACE;
        boolean up = e.getKeyCode() == KeyEvent.VK_UP;
        boolean w = e.getKeyCode() == KeyEvent.VK_W;
        boolean enter = e.getKeyCode() == KeyEvent.VK_ENTER;
        if (space || up || w || enter) game.jump();
    }

    @Override
    public void keyReleased(KeyEvent e) {

        // Variables
        if (e.getKeyCode() == KeyEvent.VK_F3) f3Pressed = false;
    }
}