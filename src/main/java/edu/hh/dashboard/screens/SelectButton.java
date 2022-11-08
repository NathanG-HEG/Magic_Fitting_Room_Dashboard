package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.FileHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class SelectButton extends JButton {
    private final String[] extensions = {"jpeg", "jpg", "png"};
    private final FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image", extensions);

    public SelectButton(JPanel parent) {
        super();
        this.setText("Select a file");
        this.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(fileNameExtensionFilter);
            jfc.setMultiSelectionEnabled(true);
            int returnVal = jfc.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                FileHandler.setSelectedFiles(jfc.getSelectedFiles());
            }

        });
    }
}
