package edu.hh.dashboard.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Utilities {
    public static String hash(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            return new String(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
