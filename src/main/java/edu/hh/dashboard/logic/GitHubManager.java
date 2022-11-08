package edu.hh.dashboard.logic;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

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

    public void sendFiles() throws GitAPIException {
        File[] localRepoFile = new File(localRepository).listFiles();
        if (localRepoFile != null) {
            for (File f : localRepoFile) {
                git.add()
                        .addFilepattern(f.getAbsolutePath())
                        .call();
            }
            git.commit()
                    .setAuthor("DashboardApp", Settings.getEmailAddress())
                    .setMessage("Uploaded " + localRepoFile.length + " new files")
                    .call();

            git.push()
                    .call();
        }
    }

    public void configGit() throws GitAPIException, IOException {
        gitHubRepo = Settings.getGitHubRepository();
        localRepository = Settings.getLocalRepository();
        File localRepoFile = new File(localRepository+"/.git");
        if (!localRepoFile.exists()) {
            git = Git.cloneRepository().setURI(gitHubRepo).setDirectory(new File(localRepository)).call();
        }
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repo = builder.setGitDir(localRepoFile)
                .readEnvironment()
                .findGitDir()
                .build();
        git = new Git(repo);

    }

    public void setGitHubRepo(String gitHubRepo) {
        this.gitHubRepo = gitHubRepo;
    }

    public void setLocalRepository(String localRepository) {
        this.localRepository = localRepository;
    }

    public String getGitHubRepo() {
        return gitHubRepo;
    }

    public String getLocalRepository() {
        return localRepository;
    }
}
