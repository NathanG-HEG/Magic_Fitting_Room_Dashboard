package edu.hh.dashboard.screens;

import javax.swing.*;

public class WindowFrame extends JFrame {
    private JPanel currentPanel;

    public WindowFrame(){
        this.setSize(600,400);
        currentPanel = new MainPanel();
        this.add(currentPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public JPanel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }
}
