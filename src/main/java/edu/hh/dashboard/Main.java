package edu.hh.dashboard;


import com.formdev.flatlaf.FlatLightLaf;
import edu.hh.dashboard.screens.WindowFrame;
import static edu.hh.dashboard.logic.Settings.settingsStartup;


/***
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        settingsStartup();
        new WindowFrame();
    }
}
