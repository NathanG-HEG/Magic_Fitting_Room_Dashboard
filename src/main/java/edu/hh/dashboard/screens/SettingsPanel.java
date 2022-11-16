package edu.hh.dashboard.screens;

import javax.swing.*;
import java.awt.*;

import static edu.hh.dashboard.logic.Settings.getGitHubRepository;

public class SettingsPanel extends JPanel {

    private Checkbox themeButton;
    private TextField gitURL;
    private JLabel gitURLText;
    private JLabel themeText;
    private JButton saveButton;
    JPanel content;
    JPanel empty;

    public class SaveAction implements ActionListener{
    public void actionPerformed(ActionEvent ae) {
        String urlValue = gitURL.getText();
        Settings.changeURL(urlValue);
    }
    }

    public SettingsPanel(){
        this.setLayout(new GridLayout(3,1));
        content = new JPanel();
        content.setLayout(new GridLayout(3,2));
        themeButton = new Checkbox();
        String gitHubUrl=getGitHubRepository();
        gitURL = new TextField(gitHubUrl);
        gitURLText = new JLabel("Github Repository");
        themeText = new JLabel("Activate Theme");
        saveButton = new JButton("Save");
        content.add(gitURLText);
        content.add(gitURL);
        content.add(themeText);
        content.add(themeButton);
        content.add(saveButton);
        empty = new JPanel();
        this.add(empty);
        this.add(content);




    }
}
