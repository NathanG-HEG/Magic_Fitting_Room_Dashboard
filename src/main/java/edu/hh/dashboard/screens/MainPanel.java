package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/***
 * Main panel of the unlocked application
 */
public class MainPanel extends JPanel implements ActionListener {
    private WindowFrame frame;
    private MenuButton settingsButton;
    private MenuButton logOutButton;

    /***
     * Default constructor
     * @param frame the WindowFrame that displays the main panel
     */
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

    /***
     * Method to create the comboBox in the main panel
     * @return a ComboBox object with the adequate ActionListener
     */
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

    /***
     * Creates the panel that contains the right buttons
     * @return A panel containing the right buttons
     */
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

    /***
     * Creates a panel with the components that make up the Upload panel
     * @return A panel with the add button, drop down list, and delete button
     */
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

    /***
     * Method to switch from main panel to login panel or settings panel
     * @param ae the event to be processed
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == logOutButton) {
            frame.changePanel(new LoginPanel(frame));
        } else if (ae.getSource() == settingsButton) {
            frame.changePanel(new SettingsPanel(frame));
        }
    }
}
