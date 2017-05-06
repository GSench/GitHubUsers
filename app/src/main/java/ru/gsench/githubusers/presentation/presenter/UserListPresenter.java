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
    private int offset = 0;

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
        offset = 0;
        interactor.updateList(limit, offset, this);
    }

    public void addUsers(ArrayList<GitHubUserShort> users){
        offset+=users.size();
        view.addUsers(users);
    }

    public void scrolled(int visibleItemCount, int totalItemCount, int pastVisibleItems){

    }

}
