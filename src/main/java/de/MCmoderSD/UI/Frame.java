package de.MCmoderSD.UI;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Frame extends JFrame {
    public Frame(Config config) {

        // Frame
        super(config.getTitle());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(config.isResizable());
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(config.getIcon());

        // GamePanel
        new GamePanel(this, config);

        // Init Frame
        setLocation(Calculate.centerOfJFrame(this, false));
        setVisible(true);
    }
}
