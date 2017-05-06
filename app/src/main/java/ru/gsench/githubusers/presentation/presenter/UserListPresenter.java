package ru.gsench.githubusers.presentation.presenter;

import java.util.ArrayList;

import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.domain.usecase.UserListUseCase;
import ru.gsench.githubusers.presentation.view.UserListView;

/**
 * Created by grish on 01.04.2017.
 */

public class UserListPresenter {

    private final int limit = 30;
    private final int padding = 20;

    private UserListUseCase interactor;

    public void setView(UserListView view) {
        this.view = view;
    }

    private UserListView view;

    public UserListPresenter(UserListUseCase interactor){
        this.interactor=interactor;
    }

    public void start(){
        view.init();
        interactor.updateList(limit, 0, this);
    }

    public void addUsers(ArrayList<GitHubUserShort> users){
        view.addUsers(users);
    }

    public void scrolled(int visibleItemCount, int totalItemCount, int pastVisibleItems){
        if (visibleItemCount + pastVisibleItems + padding >= totalItemCount) {

        }
    }

}
