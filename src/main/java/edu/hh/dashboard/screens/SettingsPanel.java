package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.Settings;
import edu.hh.dashboard.logic.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import static edu.hh.dashboard.logic.Settings.getGitHubRepository;

public class SettingsPanel extends JPanel {

    private WindowFrame frame;
    private Checkbox themeButton;
    private TextField gitURL;
    private JLabel gitURLText;
    private JLabel themeText;
    private JButton saveButton;
    private JButton backButton;
    JPanel content;
    JPanel empty;

    public class SaveAction implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
        String urlValue = gitURL.getText();
        Settings.changeURL(urlValue);
    }
    }

    public SettingsPanel(WindowFrame frame){
        this.frame = frame;
        this.setLayout(new GridLayout(3,1));
        content = new JPanel();
        content.setLayout(new GridLayout(3,2));
        themeButton = new Checkbox();
        String gitHubUrl=getGitHubRepository();
        gitURL = new TextField(gitHubUrl);
        gitURLText = new JLabel("Github Repository");
        themeText = new JLabel("Activate Theme");
        saveButton = new JButton("Save");
        backButton = new JButton("Back");
        content.add(gitURLText);
        content.add(gitURL);
        content.add(themeText);
        content.add(themeButton);
        content.add(saveButton);
        content.add(backButton);
        empty = new JPanel();
        this.add(empty);
        this.add(content);

        saveButton.addActionListener(new SaveAction());
        backButton.addActionListener(new BackAction());


    }

    public class BackAction implements ActionListener{
        public void actionPerformed(ActionEvent ae) {
            frame.changePanel(new MainPanel(frame));
        }
    }
}
