package ru.gsench.githubusers.domain.github_repo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by grish on 01.05.2017.
 */

public class ResponseParser {

    public static ArrayList<GitHubUserShort> parseSearchResults(String response) throws Exception {

        JsonParser parser = new JsonParser();
        JsonObject mainObject = parser.parse(response).getAsJsonObject();

        int totalCount = mainObject.get("total_count").getAsInt();
        if(totalCount==0) new ArrayList<GitHubUserShort>();

        JsonArray users = mainObject.getAsJsonArray("items");
        ArrayList<GitHubUserShort> userShorts = new ArrayList<>();

        for(int i=0; i<users.size(); i++) userShorts.add(initUserShort(users.get(i).getAsJsonObject()));

        return userShorts;
    }

    private static GitHubUserShort initUserShort(JsonObject user) throws Exception {
        int id = user.get("id").getAsInt();
        String login = user.get("login").getAsString();
        URL url = new URL(user.get("url").getAsString());
        URL htmlURL = new URL(user.get("html_url").getAsString());
        URL avatar = new URL(user.get("avatar_url").getAsString());
        return new GitHubUserShort(id, login, url, htmlURL, avatar);
    }

    public static ArrayList<GitHubRepository> parseRepositories(String response){

        return null;
    }

    static GitHubUser parseUserResult(String response){
        return null;
    }

}
