package edu.hh.dashboard.logic;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public abstract class Utilities {
    public static String hash(String input) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

        return argon2.hash(2, 15 * 1024, 1, input.toCharArray());
    }

    public static boolean verifyHash(String input) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
        return argon2.verify(Settings.getHash(), input.toCharArray());
    }
}
