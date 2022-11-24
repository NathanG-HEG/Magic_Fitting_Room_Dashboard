package edu.hh.dashboard.logic;

import org.eclipse.jgit.annotations.NonNull;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class FileHandler {
    private static File fileToUpload;
    private static File localRepository = new File(Settings.getLocalRepository());
    private static File[] selectedFiles;

    private final static String[] extensions = {"jpeg", "jpg", "png"};
    private final static FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image", extensions);

    /***
     * Method to move files from selected location to local Git repository
     * @param file the file that is added
     */
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

    /**
     * @param files
     */
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

    public static void removeFromLocalRepo(File[] files) {
        if (!localRepository.exists()) {
            localRepository.mkdir();
        }
        if (files != null) {
            GitHubManager ghm = GitHubManager.getGitHubManager();
            try {
                ghm.removeFiles(files);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (File f : files) {
                if (fileNameExtensionFilter.accept(f)) {
                    f.delete();
                }
            }
        }
    }

    public static void upload() {
        GitHubManager ghm = GitHubManager.getGitHubManager();
        addToLocalRepo(selectedFiles);
        try {
            ghm.sendFiles(selectedFiles);
        } catch (GitAPIException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static File[] getSelectedFiles() {
        return selectedFiles;
    }

    public static void setSelectedFiles(File[] selectedFiles) {
        FileHandler.selectedFiles = selectedFiles;
    }
}
