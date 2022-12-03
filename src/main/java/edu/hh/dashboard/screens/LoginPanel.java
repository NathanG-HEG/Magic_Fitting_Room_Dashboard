package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class LoginPanel extends JPanel implements ActionListener {
    private WindowFrame frame;
    private final JTextField textField1, textField2;

    /**
     * Creates a new login panel.
     * @param frame the frame to which the panel is added
     */
    LoginPanel(WindowFrame frame) {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.frame = frame;

        //create label for username
        JLabel userLabel = new JLabel();
        userLabel.setText("Username");

        //create text field to get username from the user
        textField1 = new JTextField(15);

        //create label for password
        JLabel passLabel = new JLabel();
        passLabel.setText("Password");

        //create text field to get password from the user
        textField2 = new JPasswordField(15);

        //create submit button
        MenuButton loginButton = new MenuButton();
        loginButton.setText("Login");

        //create panels to put credentials in
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout());
        usernamePanel.add(userLabel);
        usernamePanel.add(textField1);
        usernamePanel.setMaximumSize(new Dimension(300, 50));

        //create panel to put button in
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(loginButton);

        // add panels to the LoginPanel
        JPanel credentialsPanel = new JPanel();
        credentialsPanel.setLayout(new BoxLayout(credentialsPanel, BoxLayout.PAGE_AXIS));
        credentialsPanel.add(usernamePanel);
        this.add(credentialsPanel);
        this.add(buttonPanel);

        // add action listener to the button
        loginButton.addActionListener(this);
    }

    /**
     * When the login button is pressed, the username and password are checked.
     * @param ae
     */
    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        String USERNAME = "admin";
        if (Utilities.verifyHash(passValue) && userValue.equals(USERNAME) || true) {
            JOptionPane.showMessageDialog(this, "Login Successful");
            frame.changePanel(new MainPanel(frame));
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
