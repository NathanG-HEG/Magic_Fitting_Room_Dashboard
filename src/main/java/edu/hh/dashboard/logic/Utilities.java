package edu.hh.dashboard.logic;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/***
 * Misc class to link others and cryptography
 */
public abstract class Utilities {

    public static int chosenCategory = 0;
    public final static String[] CLOTHES_CATEGORY = {"Earth", "Fire", "Metal", "Water", "Wood"};

    /***
     * Hash a string with Argon2
     * @param input the input to be hashed
     * @return the hash as well as other information in a String object
     */
    public static String hash(String input) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);

        return argon2.hash(2, 15 * 1024, 1, input.toCharArray());
    }

    /***
     * Check if an input correspond to a stored hash
     * @param input the hash as well as other information in a String object (see Argon2 doc)
     * @return true if the hashes correspond
     */
    public static boolean verifyHash(String input) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, 32, 64);
        return argon2.verify(Settings.getHash(), input.toCharArray());
    }
}
