package ru.gsench.githubusers.domain.github_repo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
            ArrayList<GitHubRepository> result = new ArrayList<>();
            JsonArray array = parser.parse(response).getAsJsonArray();

            JsonElement id, forks, stars;
            JsonElement name, desc, lang;
            JsonElement privateRepo, fork;
            GitHubUserShort owner;
            Date created, updated;

            JsonObject rawRepo;

            for(int i=0; i<array.size(); i++){
                rawRepo = array.get(i).getAsJsonObject();
                id = rawRepo.get("id");
                name = rawRepo.get("name");
                owner = initUserShort(rawRepo.get("owner").getAsJsonObject());
                privateRepo = rawRepo.get("private");
                fork = rawRepo.get("fork");
                desc = rawRepo.get("description");
                lang = rawRepo.get("language");
                forks = rawRepo.get("forks");
                stars = rawRepo.get("stargazers_count");
                created = githubDateFormat.parse(rawRepo.get("created_at").getAsString());
                updated = githubDateFormat.parse(rawRepo.get("updated_at").getAsString());
                result.add(new GitHubRepository(
                        !id.isJsonNull() ? id.getAsInt() : -1,
                        !name.isJsonNull() ? name.getAsString() : null,
                        owner,
                        !privateRepo.isJsonNull() && privateRepo.getAsBoolean(),
                        !fork.isJsonNull() && fork.getAsBoolean(),
                        !desc.isJsonNull() ? desc.getAsString() : null,
                        !lang.isJsonNull() ? lang.getAsString() : null,
                        !forks.isJsonNull() ? forks.getAsInt(): 0,
                        created, updated,
                        !stars.isJsonNull() ? stars.getAsInt() : 0));
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
            JsonElement bio, location, company, email;
            JsonElement name;
            bio = mainObject.get("bio");
            location = mainObject.get("location");
            company = mainObject.get("company");
            email = mainObject.get("email");
            name = mainObject.get("name");
            Date created = githubDateFormat.parse(mainObject.get("created_at").getAsString());
            Date updated = githubDateFormat.parse(mainObject.get("updated_at").getAsString());
            return new GitHubUser(
                    userShort,
                    !bio.isJsonNull()?bio.getAsString():null,
                    !location.isJsonNull()?location.getAsString():null,
                    !email.isJsonNull()?email.getAsString():null,
                    !company.isJsonNull()?company.getAsString():null,
                    !name.isJsonNull()?name.getAsString():userShort.getLogin(),
                    created, updated);
        } catch (Throwable t){
            t.printStackTrace();
            throw new ParseException();
        }
    }

    public static class ParseException extends Exception{}

}
