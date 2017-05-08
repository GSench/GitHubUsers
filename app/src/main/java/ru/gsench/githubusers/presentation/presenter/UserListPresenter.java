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
    private int offset;
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
        offset=0;
        interactor.subscribe(this);
    }

    public void addUsers(ArrayList<GitHubUserShort> users){
        offset+=users.size();
        view.addUsers(users);
    }

    public void scrolled(int visibleItemCount, int totalItemCount, int pastVisibleItems){
        if (visibleItemCount + pastVisibleItems + padding >= totalItemCount) {
            interactor.updateList(limit, offset);
        }
    }

    public void showUnexpectedError() {
        view.showUnexpectedError();
    }

    public void clearList(){
        offset=0;
        view.clearList();
    }

    public void onUserClicked(GitHubUserShort user){
        interactor.openUser(user);
    }

    public void showLoadingError(){
        view.showLoadingError();
    }

    public void showParseError(){
        view.showParseError();
    }

}
