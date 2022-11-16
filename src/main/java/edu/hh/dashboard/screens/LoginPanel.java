package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.Settings;
import edu.hh.dashboard.logic.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

class LoginPanel extends JPanel implements ActionListener {
    private WindowFrame frame;
    private MenuButton loginButton;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel credentialsPanel;
    private JPanel buttonPanel;
    private JLabel userLabel, passLabel;
    private final JTextField textField1, textField2;
    private final String USERNAME = "admin";

    LoginPanel(WindowFrame frame) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.frame = frame;

        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");

        //create text field to get username from the user
        textField1 = new JTextField(15);

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");

        //create text field to get password from the user
        textField2 = new JPasswordField(15);

        //create submit button
        loginButton = new MenuButton();
        loginButton.setText("Login");

        //create panels to put credentials in
        usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout());
        usernamePanel.add(userLabel);
        usernamePanel.add(textField1);

        passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout());
        passwordPanel.add(passLabel);
        passwordPanel.add(textField2);

        //create panel to put button in
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);

        // add panels to the LoginPanel
        credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.PAGE_AXIS));
        credentialsPanel.add(usernamePanel);
        credentialsPanel.add(passwordPanel);
        this.add(credentialsPanel);
        this.add(buttonPanel);

        // add action listener to the button
        loginButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        String hashValue = Utilities.hash(passValue);
        if (userValue.equals(USERNAME) && hashValue.equals(Settings.getHash())) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            frame.changePanel(new MainPanel(frame));
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
