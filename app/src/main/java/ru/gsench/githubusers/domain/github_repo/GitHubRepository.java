package ru.gsench.githubusers.domain.github_repo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by grish on 01.05.2017.
 */

public class GitHubRepository {

    private int id;
    private String name;
    private GitHubUserShort owner;
    private boolean privateRepo;
    private boolean fork;
    private String description;
    private String language;
    private int forks;
    private Date createdAt;
    private Date updatedAt;
    private int stars;

    public GitHubRepository(int id, String name, GitHubUserShort owner, boolean privateRepo, boolean fork, String description, String language, int forks, Date createdAt, Date updatedAt, int stars) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.privateRepo = privateRepo;
        this.fork = fork;
        this.description = description;
        this.language = language;
        this.forks = forks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GitHubUserShort getOwner() {
        return owner;
    }

    public boolean isPrivateRepo() {
        return privateRepo;
    }

    public boolean isFork() {
        return fork;
    }

    public URL getUrl() {
        try {
            return new URL("https://api.github.com/repos/"+owner.getLogin()+"/"+name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public URL getHtmlUrl() {
        try {
            return new URL("https://github.com/"+owner.getLogin()+"/"+name);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public int getForks() {
        return forks;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getStars() {
        return stars;
    }

}
