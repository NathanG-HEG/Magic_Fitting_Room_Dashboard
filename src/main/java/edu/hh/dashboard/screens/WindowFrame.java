package edu.hh.dashboard.screens;

import javax.swing.*;

public class WindowFrame extends JFrame {
    private JPanel currentPanel;
    private ImageIcon appIcon = new ImageIcon(Main.class.getResource("/Amandan_uusi_logo_png.png"));

    public WindowFrame() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Magic fitting room - Dashboard");
        this.setIconImage(appIcon.getImage());

        currentPanel = new MainPanel();
        this.add(currentPanel);
        this.setVisible(true);
    }


    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
}
