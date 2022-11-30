package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.FileHandler;
import edu.hh.dashboard.logic.Settings;
import edu.hh.dashboard.logic.Utilities;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class DeleteButton extends JButton {
    private final String[] extensions = {"jpeg", "jpg", "png"};
    private final FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image", extensions);

    public DeleteButton(JPanel parent) {
        super();
        this.setText("Remove one or many pictures");
        this.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setCurrentDirectory(new File(Settings.getLocalRepository()+"/clothes/"+ Utilities.CLOTHES_CATEGORY[Utilities.chosenCategory]));
            jfc.setFileFilter(fileNameExtensionFilter);
            jfc.setMultiSelectionEnabled(true);
            jfc.setDialogTitle("Select one or many pictures");
            jfc.setApproveButtonText("Remove Pictures from the application");
            int returnVal = jfc.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                FileHandler.removeFromLocalRepo(jfc.getSelectedFiles());
                JOptionPane.showMessageDialog(parent, "Removed selected pictures",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
