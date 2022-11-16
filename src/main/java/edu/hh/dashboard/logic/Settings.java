package edu.hh.dashboard.logic;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Settings {
    private static String localRepository = "";
    private static String gitHubRepository = "";
    private static String emailAddress = "";
    private static String hash = "";
    private static JSONObject settings = null;

    //JSON parser object to parse read file
    public static JSONParser jsonParser = new JSONParser();

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

    public static void settingsStartup() {
        try (FileReader reader = new FileReader("Information.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject jsettings = (JSONObject) obj;
            System.out.println(jsettings);
            settings = jsettings;

            //read the Setting informations
            readSettings(settings);

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
        String local = (String) gitInfo.get("localRepository");
        //System.out.println("local: "+local);
        localRepository = local;

        //Get github Repository
        String github = (String) gitInfo.get("gitHubRepository");
        //System.out.println("github: "+github);
        gitHubRepository = github;

        //Get email Adress
        String email = (String) gitInfo.get("emailAddress");
        //System.out.println("Email: "+email);
        emailAddress = email;

        //Get hash
        String jhash = (String) gitInfo.get("hash");
        hash = jhash;
    }

    public static void changeURL(String url) {
        //Update JSON File
        JSONObject newJson = new JSONObject();
        newJson.put("localRepository", localRepository);
        newJson.put("gitHubRepository", url);
        newJson.put("emailAddress", emailAddress);
        newJson.put("hash", hash);

        //Write JSON file
        try (FileWriter file = new FileWriter("Information.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(newJson.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        readSettings(newJson);
    }
}
