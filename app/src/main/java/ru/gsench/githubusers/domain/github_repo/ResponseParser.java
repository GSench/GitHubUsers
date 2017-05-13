package ru.gsench.githubusers.domain.github_repo;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ru.gsench.githubusers.domain.utils.Pair;

/**
 * Created by grish on 01.05.2017.
 */

public class ResponseParser {

    private static DateFormat githubDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static Pair<ArrayList<GitHubUserShort>, Integer> parseSearchResults(String response) throws ParseException {
        try {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(response).getAsJsonObject();

            int totalCount = mainObject.get("total_count").getAsInt();
            if(totalCount==0) new Pair<>(new ArrayList<GitHubUserShort>(), 0);

            JsonArray users = mainObject.getAsJsonArray("items");
            ArrayList<GitHubUserShort> userShorts = new ArrayList<>();

            for(int i=0; i<users.size(); i++) userShorts.add(initUserShort(users.get(i).getAsJsonObject()));

            return new Pair<>(userShorts, totalCount);
        } catch (Throwable t){
            throw new ParseException();
        }
    }

    private static GitHubUserShort initUserShort(JsonObject user) throws ParseException {
        try {
            int id = user.get("id").getAsInt();
            String login = user.get("login").getAsString();
            return new GitHubUserShort(id, login);
        } catch (Throwable t){
            throw new ParseException();
        }
    }

    public static ArrayList<GitHubRepository> parseRepositories(String response) throws ParseException {
        try {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(response).getAsJsonObject();

            ArrayList<GitHubRepository> result = new ArrayList<>();
            JsonArray array = mainObject.getAsJsonArray();

            int id, forks, stars;
            String name, desc, lang;
            boolean privateRepo, fork;
            GitHubUserShort owner;
            Date created, updated;

            JsonObject rawRepo;

            for(int i=0; i<array.size(); i++){
                rawRepo = array.get(i).getAsJsonObject();
                id = rawRepo.get("id").getAsInt();
                name = rawRepo.get("name").getAsString();
                owner = initUserShort(rawRepo.get("owner").getAsJsonObject());
                privateRepo = rawRepo.get("private").getAsBoolean();
                fork = rawRepo.get("fork").getAsBoolean();
                desc = rawRepo.get("description").getAsString();
                lang = rawRepo.get("language").getAsString();
                forks = rawRepo.get("forks").getAsInt();
                stars = rawRepo.get("stars").getAsInt();
                created = githubDateFormat.parse(mainObject.get("created_at").getAsString());
                updated = githubDateFormat.parse(mainObject.get("updated_at").getAsString());
                result.add(new GitHubRepository(
                        id, name, owner, privateRepo, fork, desc, lang, forks, created, updated, stars));
            }
            return result;
        } catch (Throwable t){
            throw new ParseException();
        }
    }

    public static GitHubUser parseUser(String response) throws ParseException {
        try {
            JsonParser parser = new JsonParser();
            JsonObject mainObject = parser.parse(response).getAsJsonObject();
            GitHubUserShort userShort = initUserShort(mainObject);
            String bio = null, location = null, company = null, email = null, name = userShort.getLogin();
            try {
                bio = mainObject.get("bio").getAsString();
            } catch (Exception e){}
            try {
                location = mainObject.get("location").getAsString();
            } catch (Exception e){}
            try {
                company = mainObject.get("company").getAsString();
            } catch (Exception e){}
            try {
                email = mainObject.get("email").getAsString();
            } catch (Exception e){}
            try {
                name = mainObject.get("name").getAsString();
            } catch (Exception e){}
            Date created = githubDateFormat.parse(mainObject.get("created_at").getAsString());
            Date updated = githubDateFormat.parse(mainObject.get("updated_at").getAsString());
            return new GitHubUser(userShort, bio, location, email, company, name, created, updated);
        } catch (Throwable t){
            throw new ParseException();
        }
    }

    public static class ParseException extends Exception{}

}
