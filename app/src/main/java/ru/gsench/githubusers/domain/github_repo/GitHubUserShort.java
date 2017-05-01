package ru.gsench.githubusers.domain.github_repo;

import java.net.URL;

/**
 * Created by grish on 01.05.2017.
 */

public class GitHubUserShort {

    private int id;
    private String login;
    private URL url;
    private URL htmlUrl;
    private URL avatar;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public URL getUrl() {
        return url;
    }

    public URL getHtmlUrl() {
        return htmlUrl;
    }

    public URL getAvatar() {
        return avatar;
    }

    public GitHubUserShort(int id, String login, URL url, URL htmlUrl, URL avatar){
        this.id=id;
        this.login=login;
        this.url=url;
        this.htmlUrl=htmlUrl;
        this.avatar=avatar;
    }

}
