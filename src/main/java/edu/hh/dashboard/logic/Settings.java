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

    public static String getHash() {
        return hash;
    }

    public static String getLocalRepository() {
        return localRepository;
    }

    public static String getGitHubRepository() {
        return gitHubRepository;
    }

    public static String getEmailAddress() {
        return emailAddress;
    }

    public static String getDecryptedToken() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        String algorithm = "AES/CBC/PKCS5Padding";
        String plainText = Utilities.decrypt(algorithm, encryptedToken, secretKey, ivParameterSpec);
        return plainText;
    }

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

    private static void readSettings(JSONObject gitInfo) {
        //Get local Repository
        //System.out.println("local: "+local);
        localRepository = (String) gitInfo.get("localRepository");

        //Get gitHub Repository
        //System.out.println("github: "+github);
        gitHubRepository = (String) gitInfo.get("gitHubRepository");

        //Get email Address
        //System.out.println("Email: "+email);
        emailAddress = (String) gitInfo.get("emailAddress");

        //Get hash
        hash = (String) gitInfo.get("hash");

        // Get token
        encryptedToken = (String) gitInfo.get("token");
    }

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
