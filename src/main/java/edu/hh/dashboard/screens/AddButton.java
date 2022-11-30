package edu.hh.dashboard.screens;

import edu.hh.dashboard.logic.FileHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/***
 * Encapsulation of the add behaviour in a JButton
 */
public class AddButton extends JButton {
    private final String[] extensions = {"jpeg", "jpg", "png"};
    private final FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image", extensions);

    /***
     * Constructor method
     * @param parent the panel that uses the button
     */
    public AddButton(JPanel parent) {
        super();
        this.setText("Add one or many pictures");
        this.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(fileNameExtensionFilter);
            jfc.setMultiSelectionEnabled(true);
            jfc.setApproveButtonText("Add pictures to the application");
            jfc.setDialogTitle("Select one or many pictures");
            int returnVal = jfc.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                FileHandler.setSelectedFiles(jfc.getSelectedFiles());
                FileHandler.upload();
                JOptionPane.showMessageDialog(parent, "Added selected pictures",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }
}
