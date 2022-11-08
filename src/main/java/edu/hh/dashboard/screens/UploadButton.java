package edu.hh.dashboard.screens;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class UploadButton extends JButton {
    public UploadButton(JPanel parent) {
        super();
        this.setText("Upload");
        this.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            int returnVal = jfc.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                System.out.println("File selected " + file.getAbsolutePath());
            }

        });
    }
}
