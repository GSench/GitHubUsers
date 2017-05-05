package ru.gsench.githubusers.domain.github_repo;

import java.util.Date;

/**
 * Created by grish on 01.05.2017.
 */

public class GitHubUser extends GitHubUserShort {

    public GitHubUser(int id, String login, String bio, String location, String email, String company, String name, Date createdAt, Date updatedAt) {
        super(id, login);
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.company = company;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public GitHubUser(GitHubUserShort userShort, String bio, String location, String email, String company, String name, Date createdAt, Date updatedAt){
        super(userShort.getId(), userShort.getLogin());
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.company = company;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private String bio;
    private String location;
    private String email;
    private String company;
    private String name;
    private Date createdAt, updatedAt;

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
