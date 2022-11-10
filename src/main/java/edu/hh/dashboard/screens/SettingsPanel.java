package edu.hh.dashboard.screens;

import javax.swing.*;
import java.awt.*;

import static edu.hh.dashboard.logic.Settings.getGitHubRepository;

public class SettingsPanel extends JPanel {

    private Checkbox themeButton;
    private TextField gitURL;
    private TextField gitURLText;
    private TextField themeText;
    private JButton saveButton;
    JPanel content;
    JPanel empty;


    public SettingsPanel(){
        this.setLayout(new GridLayout(3,1));
        content = new JPanel();
        content.setLayout(new GridLayout(3,2));
        themeButton = new Checkbox();
        String gitHubUrl=getGitHubRepository();
        gitURL = new TextField(gitHubUrl);
        gitURLText = new TextField("Github Repository");
        gitURLText.setEditable(false);
        themeText = new TextField("Activate Theme");
        themeText.setEditable(false);
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
