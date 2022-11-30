package edu.hh.dashboard.logic;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public abstract class Settings {
    private static String localRepository = "";
    private static String gitHubRepository = "";
    private static String emailAddress = "";
    private static String hash = "";
    private static String encryptedToken ="";
    private static SecretKey secretKey;
    private static IvParameterSpec ivParameterSpec;

    //JSON parser object to parse read file
    public static JSONParser jsonParser = new JSONParser();

    /**
     * setUpKey() is used to create the secret Key
     * @param
     */
    public static void setUpKey() {
        try {
            secretKey = Utilities.generateKey(128);
            System.out.println(new String(secretKey.getEncoded()));
            ivParameterSpec = Utilities.generateIv();
            System.out.println(new String(ivParameterSpec.getIV()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the Hashed password
     * @return
     */
    public static String getHash() {
        return hash;
    }

    /**
     * Returns the local Git Repository
     * @return
     */
    public static String getLocalRepository() {
        return localRepository;
    }

    /**
     * Returns the Git Repository
     * @return
     */
    public static String getGitHubRepository() {
        return gitHubRepository;
    }

    /**
     * Returns the Email Address
     * @return
     */
    public static String getEmailAddress() {
        return emailAddress;
    }

    public static String getDecryptedToken() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        String algorithm = "AES/CBC/PKCS5Padding";
        String plainText = Utilities.decrypt(algorithm, encryptedToken, secretKey, ivParameterSpec);
        return plainText;
    }

    /**
     * settingsStartup() gets called at the Start to load the Settings from the JSON File
     */
    public static void settingsStartup() {
        try (FileReader reader = new FileReader("Settings.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject jsettings = (JSONObject) obj;
            System.out.println(jsettings);

            //read the Setting information
            readSettings(jsettings);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets called by settingsStartup() to do the actual reading of the JSON File
     * @param gitInfo
     */
    private static void readSettings(JSONObject gitInfo) {
        //Get local Repository
        localRepository = (String) gitInfo.get("localRepository");
        //Get gitHub Repository
        gitHubRepository = (String) gitInfo.get("gitHubRepository");
        //Get email Address
        emailAddress = (String) gitInfo.get("emailAddress");
        //Get hash
        hash = (String) gitInfo.get("hash");
        // Get token
        encryptedToken = (String) gitInfo.get("token");
    }

    /**
     * Used to change the GitURL in the Settings Panel
     * @param url
     */
    public static void changeURL(String url) {
        //Update JSON File
        JSONObject newJson = new JSONObject();
        newJson.put("localRepository", localRepository);
        newJson.put("gitHubRepository", url);
        newJson.put("emailAddress", emailAddress);
        newJson.put("hash", hash);
        newJson.put("token",encryptedToken);

        //Write JSON file
        try (FileWriter file = new FileWriter("Settings.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(newJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update
        readSettings(newJson);
    }

    /**
     * Used to change the Password in the Settings Panel
     * @param password
     */
    public static void changePassword(String password) {

        //Do the magic with hashing the given password
        String hashedPassword = Utilities.hash(password);

        //Update JSON File
        JSONObject newJson = new JSONObject();
        newJson.put("localRepository", localRepository);
        newJson.put("gitHubRepository", gitHubRepository);
        newJson.put("emailAddress", emailAddress);
        newJson.put("hash", hashedPassword);
        newJson.put("token",encryptedToken);

        //Write JSON file
        try (FileWriter file = new FileWriter("Settings.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(newJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Update
        readSettings(newJson);
    }
}
