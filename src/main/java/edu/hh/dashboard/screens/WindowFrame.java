package edu.hh.dashboard.screens;

import edu.hh.dashboard.Main;

import javax.swing.*;

public class WindowFrame extends JFrame {
    private JPanel currentPanel;

    public WindowFrame() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Magic fitting room - Dashboard");
        ImageIcon appIcon = new ImageIcon(Main.class.getResource("/Amandan_uusi_logo_png.png"));
        this.setIconImage(appIcon.getImage());
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        //currentPanel = new MainPanel();
        currentPanel = new LoginPanel(this);
        this.add(currentPanel);
        this.setVisible(true);
    }


    public void changePanel(JPanel newPanel) {
        this.remove(currentPanel);
        this.add(newPanel);
        this.currentPanel = newPanel;
        this.revalidate();
        this.repaint();
    }
}
