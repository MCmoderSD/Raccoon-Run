package de.MCmoderSD.UI;

import de.MCmoderSD.main.Config;
import de.MCmoderSD.utilities.Calculate;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Frame extends JFrame {
    public Frame(Config config) {

        // Frame
        super(Config.title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(Config.resizable);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setIconImage(Config.icon);

        // GamePanel
        new GamePanel(this, config);

        // Init Frame
        setLocation(Calculate.centerOfJFrame(this, false));
        setVisible(true);
    }
}
