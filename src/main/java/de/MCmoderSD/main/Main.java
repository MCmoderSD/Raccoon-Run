package de.MCmoderSD.main;

import de.MCmoderSD.UI.Frame;

public class Main {
    public static boolean IS_RUNNING = true;

    public static void main(String[] args) {
        new Frame(new Config(args));
    }
}
