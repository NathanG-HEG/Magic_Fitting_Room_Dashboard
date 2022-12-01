package edu.hh.dashboard.logic;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static edu.hh.dashboard.logic.Utilities.CLOTHES_CATEGORY;
import static edu.hh.dashboard.logic.Utilities.chosenCategory;

/***
 * Class to handle the file on the local file system
 */
public abstract class FileHandler {
    private static File localPicturesRepository = new File(Settings.getLocalRepository() + "/clothes/" + CLOTHES_CATEGORY[Utilities.chosenCategory]);
    private static File[] selectedFiles;

    private final static String[] extensions = {"jpeg", "jpg", "png"};
    private final static FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Image", extensions);

    /***
     * Method to move files from selected location to local Git repository
     * @param file the file that is added
     */
    private static void addFileToLocalRepo(File file) {
        File fileToUpload = new File(localPicturesRepository.getAbsolutePath() + '/' + file.getName());
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
     * Add files to local Git repo
     *
     * @param files array of File objects to add to the local repo
     */
    private static void addToLocalRepo(File[] files) {
        if (!localPicturesRepository.exists()) {
            localPicturesRepository.mkdir();
        }
        if (files != null) {
            for (File f : files) {
                if (fileNameExtensionFilter.accept(f))
                    addFileToLocalRepo(f);
            }
        }

    }

    /***
     * Remove files from local repo and call GitHubManager to delete from tracklist
     * @param files files to be deleted from repo
     */
    public static void removeFromLocalRepo(File[] files) {
        if (!localPicturesRepository.exists()) {
            localPicturesRepository.mkdir();
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

    /***
     * Calls the GitHubManager to add files to the remote repo
     */
    public static void upload() {
        GitHubManager ghm = GitHubManager.getGitHubManager();
        addToLocalRepo(selectedFiles);
        updateHtml();
        try {
            ghm.sendFiles(selectedFiles);
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }

    private static void updateHtml() {
        HtmlEditor.modifyDivElementsFromFolder(Settings.getLocalRepository() + "/clothes/" + CLOTHES_CATEGORY[chosenCategory]);
        GitHubManager ghm = GitHubManager.getGitHubManager();
        try {
            ghm.commitHtmlChanges();
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setSelectedFiles(File[] selectedFiles) {
        FileHandler.selectedFiles = selectedFiles;
    }
}
