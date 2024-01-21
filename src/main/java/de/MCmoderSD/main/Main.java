package de.MCmoderSD.main;

import de.MCmoderSD.UI.Frame;
import de.MCmoderSD.utilities.Calculate;

public class Main {
    public static boolean IS_RUNNING = true;

    public static void main(String[] args) {
        if (Calculate.doesFileExist("/config/default.json")) new Frame(new Config(args));
        else new Frame(new Config(args, "https://raw.githubusercontent.com/MCmoderSD/Raccoon-Run/master/src/main/resources/"));
    }
}
