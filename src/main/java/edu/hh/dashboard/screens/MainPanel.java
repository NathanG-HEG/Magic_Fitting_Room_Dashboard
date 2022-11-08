package edu.hh.dashboard.screens;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private MenuButton settingsButton;
    private MenuButton logOutButton;
    private SelectButton selectButton;
    private UploadButton uploadButton;
    private JPanel buttonsPanel;
    private JPanel uploadPanel;

    public MainPanel() {
        this.setLayout(new BorderLayout());
        uploadPanel = createUploadPanel();
        this.add(uploadPanel, BorderLayout.CENTER);
        buttonsPanel = createButtonPanel();
        this.add(buttonsPanel, BorderLayout.LINE_END);
        this.setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        settingsButton = new MenuButton();
        settingsButton.setText("Settings");
        panel.add(settingsButton);
        logOutButton = new MenuButton();
        logOutButton.setText("Log out");
        panel.add(logOutButton);

        panel.setVisible(true);
        return panel;
    }

    private JPanel createUploadPanel() {
        JPanel panel = new JPanel();
        selectButton = new SelectButton(panel);
        panel.add(selectButton);

        uploadButton = new UploadButton();
        panel.add(uploadButton);

        panel.setVisible(true);
        return panel;
    }

}
