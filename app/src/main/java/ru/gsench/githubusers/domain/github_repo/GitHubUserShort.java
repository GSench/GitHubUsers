package ru.gsench.githubusers.domain.github_repo;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by grish on 01.05.2017.
 */

public class GitHubUserShort {

    private int id;
    private String login;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public GitHubUserShort(int id, String login){
        this.id=id;
        this.login=login;
    }

    public URL getUrl() {
        try {
            return new URL("https://api.github.com/users/"+login);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public URL getHtmlUrl() {
        try {
            return new URL("https://github.com/"+login);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public URL getAvatar() {
        try {
            return new URL("https://avatars0.githubusercontent.com/u/"+id+"?v=3");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public URL getRepositories(){
        try {
            return new URL("https://api.github.com/users/"+login+"/repos");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
