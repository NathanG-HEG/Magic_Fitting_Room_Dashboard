package edu.hh.dashboard;


import com.formdev.flatlaf.FlatLightLaf;
import edu.hh.dashboard.screens.WindowFrame;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new WindowFrame();
    }
}
