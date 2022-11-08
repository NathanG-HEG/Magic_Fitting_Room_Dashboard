package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.FileHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UploadButton extends JButton {
    public UploadButton(){
        super();
        this.setText("Upload selected pictures");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileHandler.upload();
            }
        });
    }
}
