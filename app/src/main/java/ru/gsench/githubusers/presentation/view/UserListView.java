package ru.gsench.githubusers.presentation.view;

import java.util.ArrayList;

import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;

/**
 * Created by grish on 01.04.2017.
 */

public interface UserListView {

    public void init();
    public void addUsers(ArrayList<GitHubUserShort> users);
    public void clearList();
    public void showLoadingError();
    public void showParseError();
    public void showUnexpectedError();
}
