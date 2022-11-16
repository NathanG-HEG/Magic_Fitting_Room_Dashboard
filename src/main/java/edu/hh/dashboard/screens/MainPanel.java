package edu.hh.dashboard.screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private WindowFrame frame;
    private MenuButton settingsButton;
    private MenuButton logOutButton;
    private SelectButton selectButton;
    private UploadButton uploadButton;
    private JPanel buttonsPanel;
    private JPanel uploadPanel;

    public MainPanel(WindowFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        uploadPanel = createUploadPanel();
        this.add(uploadPanel, BorderLayout.CENTER);
        buttonsPanel = createButtonPanel();
        this.add(buttonsPanel, BorderLayout.LINE_END);
        this.setVisible(true);
        logOutButton.addActionListener(this);
        settingsButton.addActionListener(this);
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logOutButton) {
            frame.changePanel(new LoginPanel(frame));
        } else if (ae.getSource() == settingsButton) {
            frame.changePanel(new SettingsPanel(frame));
        }
    }
}
