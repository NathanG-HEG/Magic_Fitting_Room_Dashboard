package edu.hh.dashboard.logic;

public abstract class Settings {
    private static String localRepository = "C:/temp/test/VirtualChangingRoomProto";
    private static String gitHubRepository = "https://github.com/NathanG-HEG/VirtualChangingRoomProto.git";
    private static String emailAddress = "nathan.gaillard@students.hevs.ch";

    public static String getLocalRepository() {
        return localRepository;
    }

    public static String getGitHubRepository() {
        return gitHubRepository;
    }

    public static String getEmailAddress() {
        return emailAddress;
    }
}
