package edu.hh.dashboard.logic;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("Information.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray settings = (JSONArray) obj;
            System.out.println(settings);

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
        String local = (String) employee.get("localRepository");
        //System.out.println("local: "+local);
        localRepository = local;

        //Get github Repository
        String github = (String) employee.get("gitHubRepository");
        //System.out.println("github: "+github);
        gitHubRepository = github;

        //Get email Adress
        String email = (String) employee.get("emailAddress");
        //System.out.println("Email: "+email);
        emailAddress = email;
    }
}
