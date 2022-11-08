package edu.hh.dashboard.logic;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public abstract class FileHandler {
    private static File fileToUpload;
    private static File localRepository = new File("C:/temp/test");
    private static File[] selectedFiles;

    private final static String[] extensions = {"jpeg", "jpg", "png"};
    private final static FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image", extensions);

    private static void addFileToLocalRepo(File file) {
        fileToUpload = new File(localRepository.getAbsolutePath() + '/' + file.getName());
        try {
            if (fileToUpload.createNewFile()) {
                System.out.println("Copying " + file.toPath() + " to " + fileToUpload.toPath());
                Files.copy(file.toPath(), fileToUpload.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void addToLocalRepo(File[] files) {
        if (!localRepository.exists()) {
            localRepository.mkdir();
        }
        if (files != null) {
            for (File f : files) {
                if (fileNameExtensionFilter.accept(f))
                    addFileToLocalRepo(f);
            }
        }
    }

    public static void upload() {
        addToLocalRepo(selectedFiles);

    }

    public static File[] getSelectedFiles() {
        return selectedFiles;
    }

    public static void setSelectedFiles(File[] selectedFiles) {
        FileHandler.selectedFiles = selectedFiles;
    }
}
