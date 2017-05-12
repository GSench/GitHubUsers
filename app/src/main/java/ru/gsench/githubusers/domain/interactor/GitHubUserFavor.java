package ru.gsench.githubusers.domain.interactor;

import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;

/**
 * Created by grish on 11.05.2017.
 */

public class GitHubUserFavor extends GitHubUserShort {

    public boolean isFavorite() {
        return isFavorite;
    }

    private boolean isFavorite = false;

    public GitHubUserFavor(int id, String login) {
        super(id, login);
    }

    public GitHubUserFavor(int id, String login, boolean favorite) {
        super(id, login);
        isFavorite = favorite;
    }

    public GitHubUserFavor(GitHubUserShort user, boolean favorite){
        super(user.getId(), user.getLogin());
        this.isFavorite=favorite;
    }

    public GitHubUserFavor setFavorite(boolean favor){
        isFavorite=favor;
        return this;
    }
}
