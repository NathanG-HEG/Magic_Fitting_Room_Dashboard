package edu.hh.dashboard.logic;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import java.io.File;
import java.io.IOException;

/***
 * Class to encapsulate the methods of JGit
 */
public class GitHubManager {
    private String localRepository;
    private Git git;
    private static GitHubManager instance;

    /***
     * Singleton constructor
     */
    private GitHubManager() {
        try {
            configGit();
        } catch (GitAPIException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    /***
     * Unique instance getter
     * @return Adress of the GitHubManager object
     */
    public static GitHubManager getGitHubManager() {
        if (instance == null) {
            instance = new GitHubManager();
        }
        return instance;
    }

    /***
     * Remove files from Git track list and pushes the changes
     * @param files files to remove from track list
     * @throws GitAPIException
     */
    public void removeFiles(File[] files) throws GitAPIException {
        if (files != null) {
            for (File f : files) {
                git.rm()
                        .addFilepattern("clothes/"+Utilities.CLOTHES_CATEGORY[Utilities.chosenCategory]+"/"+f.getName())
                        .call();
                System.out.println("Removed " + f.getName());
            }
            System.out.println(localRepository);

            commitAndPushChanges("Removed " + files.length + " files, committed and pushed the changes");

            System.out.println("Committed and pushed (deletion) " + files.length + " new files.");
        }
    }

    /***
     * Add files to the Git track list and pushes them to remote repo
     * @param files files to add to repo
     * @throws GitAPIException
     */
    public void sendFiles(File[] files) throws GitAPIException {
        if (files != null) {
            for (File f : files) {
                git.add()
                        .addFilepattern("clothes/"+Utilities.CLOTHES_CATEGORY[Utilities.chosenCategory]+"/"+f.getName())
                        .call();
                System.out.println("Added " + f.getName());
            }
            System.out.println(localRepository);

            commitAndPushChanges("Uploaded " + files.length + " new files");

            System.out.println("Committed and pushed " + files.length + " new files.");

        }
    }

    /***
     * Updates the Git repo, commit and pushes the changes
     * @param message commit message
     * @throws GitAPIException
     */
    public void commitAndPushChanges(String message) throws GitAPIException {
        UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("softala-tailorfit", Settings.getToken());
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

    /***
     * Set up local git repository if folder is nonexistent
     * @throws GitAPIException
     * @throws IOException
     */
    private void configGit() throws GitAPIException, IOException {
        String gitHubRepo = Settings.getGitHubRepository() + ".git";
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
