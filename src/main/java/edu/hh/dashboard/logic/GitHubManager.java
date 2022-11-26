package edu.hh.dashboard.logic;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class GitHubManager {
    private String gitHubRepo;
    private String localRepository;
    private Git git;
    private static GitHubManager instance;

    private GitHubManager() {
        try {
            configGit();
        } catch (GitAPIException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static GitHubManager getGitHubManager() {
        if (instance == null) {
            instance = new GitHubManager();
        }
        return instance;
    }

    public void removeFiles(File[] files) throws GitAPIException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if (files != null) {
            for (File f : files) {
                git.rm()
                        .addFilepattern(f.getName())
                        .call();
                System.out.println("Removed " + f.getName());
            }
            System.out.println(localRepository);

            commitAndPushChanges("Removed "+files.length+" files, committed and pushed the changes");

            System.out.println("Committed and pushed (deletion) " + files.length + " new files.");
        }
    }

    public void sendFiles(File[] files) throws GitAPIException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        if (files != null) {
            for (File f : files) {
                git.add()
                        .addFilepattern(f.getName())
                        .call();
                System.out.println("Added " + f.getName());
            }
            System.out.println(localRepository);

            commitAndPushChanges("Uploaded " + files.length + " new files");

            System.out.println("Committed and pushed " + files.length + " new files.");

        }
    }

    public void commitAndPushChanges(String message) throws GitAPIException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("softala-tailorfit", "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");
        git.pull().setRemote("origin")
                .setRemoteBranchName("master")
                .setCredentialsProvider(credentialsProvider)
                .call();
        git.commit()
                .setAuthor("DashboardApp", Settings.getEmailAddress())
                .setMessage(message)
                .call();
        git.push()
                .setCredentialsProvider(credentialsProvider)
                .call();
    }

    private void configGit() throws GitAPIException, IOException {
        gitHubRepo = Settings.getGitHubRepository() + ".git";
        localRepository = Settings.getLocalRepository();
        File localRepoFile = new File(localRepository + "/.git");
        if (!localRepoFile.exists()) {
            git = Git.cloneRepository().setURI(gitHubRepo).setDirectory(new File(localRepository)).call();
        }
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repo = builder.setGitDir(localRepoFile)
                .readEnvironment()
                .findGitDir()
                .build();
        git = new Git(repo);
        git.pull().call();
    }

}
