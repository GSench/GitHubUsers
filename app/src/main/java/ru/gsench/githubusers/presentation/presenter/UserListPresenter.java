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
    private final int padding = 6;

    private UserListUseCase interactor;

    private ArrayList<GitHubUserShort> users;

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
        users = new ArrayList<>();
        interactor.subscribe(this);
    }

    public void addUsers(ArrayList<GitHubUserShort> users){
        this.users.addAll(users);
        view.notifyUsersAdded(offset, users.size());
        offset+=users.size();
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
        users.clear();
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

    public void onBackPressed(){
        view.closeView();
    }

    public GitHubUserShort getUserAt(int i) {
        return users.get(i);
    }

    public int getUserCount() {
        return users.size();
    }

    public void addToFavor(GitHubUserShort user) {
        interactor.addToFavor(user);
    }
}
