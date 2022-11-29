package edu.hh.dashboard.screens;

import edu.hh.dashboard.Main;
import edu.hh.dashboard.logic.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static edu.hh.dashboard.logic.Settings.getGitHubRepository;

public class SettingsPanel extends JPanel {

    private WindowFrame frame;

    private JPasswordField passwordField;

    private TextField gitURL;

    JPanel content;
    JPanel empty;

    PopUp popup;

    //Bold Font
    Font boldFont = new Font("Bold", Font.BOLD, 12);


    public SettingsPanel(WindowFrame frame) {
        this.frame = frame;
        this.setLayout(new GridLayout(3, 1));

        content = new JPanel();
        content.setLayout(new GridLayout(3, 1));

        //Panel for the Git repository
        JPanel gitPanel = new JPanel();
        gitPanel.setLayout(new GridLayout(1, 2));
        JLabel gitURLText = new JLabel("Github Repository");
        gitURLText.setFont(boldFont);
        String gitHubUrl = getGitHubRepository();
        gitURL = new TextField(gitHubUrl);
        gitPanel.add(gitURLText);
        gitPanel.add(gitURL);
        gitPanel.setMaximumSize(new Dimension(300, 50));

        //Panel for the changing of Password
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(1, 2));
        JLabel passwordText = new JLabel("Change Password");
        passwordText.setFont(boldFont);
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordText);
        passwordPanel.add(passwordField);
        passwordPanel.setMaximumSize(new Dimension(300, 50));

        //Panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        //Adding to the layout
        content.add(gitPanel);
        content.add(passwordPanel);
        content.add(buttonPanel);
        empty = new JPanel();
        this.add(empty);
        this.add(content);

        //Add actionListener to the Buttons
        saveButton.addActionListener(new SaveAction());
        backButton.addActionListener(new BackAction());


    }

    public class SaveAction implements ActionListener{
        /**
         * Gets called when Save button was clicked
         * @param ae
         */
        public void actionPerformed(ActionEvent ae){
            popup = new PopUp();
        }
    }
    public class SaveForRealAction implements ActionListener {
        /**
         * Gets called when the second Save button in the Popup gets clicked
         * @param ae
         */
        public void actionPerformed(ActionEvent ae) {
            String urlValue = gitURL.getText();
            Settings.changeURL(urlValue);

            String newPassword = String.valueOf(passwordField.getPassword());
            Settings.changePassword(newPassword);

            frame.changePanel(new MainPanel(frame));
            popup.dispose();

        }
    }

    public class CancelAction implements ActionListener {
        /**
         * Gets called when the Cancel Button in the Popup was clicked
         * @param ae
         */
        public void actionPerformed(ActionEvent ae) {

            popup.dispose();

            }
        }


    public class BackAction implements ActionListener {
        /**
         * Gets called when the Back button was clicked
         * @param ae
         */
        public void actionPerformed(ActionEvent ae) {
            frame.changePanel(new MainPanel(frame));
        }
    }


    private class PopUp extends JFrame {

        JPanel content;
        JPanel text;
        JPanel buttons;
        JLabel confirmation;
        JButton confirm;
        JButton cancel;

        public PopUp() {
            this.setSize(300, 200);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle("Magic fitting room - Settings");
            ImageIcon appIcon = new ImageIcon(Main.class.getResource("/Amandan_uusi_logo_png.png"));
            this.setIconImage(appIcon.getImage());
            this.setLocationRelativeTo(null);
            this.setResizable(false);

            content = new JPanel();
            content.setLayout(new GridLayout(2, 1));
            text = new JPanel(new GridLayout(1,1));
            confirmation = new JLabel("Are you sure you want to save the changes?");
            confirmation.setFont(boldFont);
            text.add(confirmation);
            text.setMaximumSize(new Dimension(300, 50));
            buttons = new JPanel(new GridLayout(1,2));
            confirm = new JButton("Confirm");
            cancel = new JButton("Cancel");
            buttons.add(confirm);
            buttons.add(cancel);
            text.setMaximumSize(new Dimension(300, 50));

            content.add(text);
            content.add(buttons);

            confirm.addActionListener(new SaveForRealAction());
            cancel.addActionListener(new CancelAction());

            this.add(content);
            this.setVisible(true);
        }
    }

}

