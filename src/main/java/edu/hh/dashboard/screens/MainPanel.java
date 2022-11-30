package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {
    private WindowFrame frame;
    private MenuButton settingsButton;
    private MenuButton logOutButton;

    public MainPanel(WindowFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        JPanel uploadPanel = createUploadPanel();
        this.add(uploadPanel, BorderLayout.CENTER);
        JPanel buttonsPanel = createButtonPanel();
        this.add(buttonsPanel, BorderLayout.LINE_END);
        this.setVisible(true);
        logOutButton.addActionListener(this);
        settingsButton.addActionListener(this);

    }

    private JComboBox<String> createComboBox(){
        JComboBox<String> jComboBox = new JComboBox<>(Utilities.CLOTHES_CATEGORY);
        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilities.chosenCategory = jComboBox.getSelectedIndex();
            }
        });

        return jComboBox;
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
        panel.setLayout(new FlowLayout());
        AddButton addButton = new AddButton(panel);
        panel.add(addButton);

        JComboBox<String> jComboBox = createComboBox();
        panel.add(jComboBox);

        DeleteButton deleteButton = new DeleteButton(panel);
        panel.add(deleteButton);

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
