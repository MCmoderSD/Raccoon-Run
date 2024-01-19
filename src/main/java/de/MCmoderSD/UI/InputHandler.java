package de.MCmoderSD.UI;

import de.MCmoderSD.core.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {

    // Associations
    private final Game game;

    public InputHandler(Frame frame, Game game) {
        frame.addKeyListener(this);
        this.game = game;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isAltDown() && e.getKeyCode() == KeyEvent.VK_Q) System.exit(0);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) game.togglePause();
        if (e.getKeyCode() == KeyEvent.VK_SPACE) game.jump();

        System.out.println("pressed");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
