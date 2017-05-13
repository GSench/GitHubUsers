package ru.gsench.githubusers.presentation.presenter;

import java.util.ArrayList;

import ru.gsench.githubusers.domain.github_repo.GitHubRepository;
import ru.gsench.githubusers.domain.github_repo.GitHubUser;
import ru.gsench.githubusers.domain.interactor.UserModel;
import ru.gsench.githubusers.domain.usecase.UserUseCase;
import ru.gsench.githubusers.presentation.view.UserView;

/**
 * Created by grish on 09.05.2017.
 */

public class UserPresenter {

    private UserUseCase interactor;
    private UserView view;

    private GitHubUser user;
    private ArrayList<GitHubRepository> repos;
    private ArrayList<GitHubRepository> pinnedRepos;

    public UserPresenter(UserUseCase interactor){
        this.interactor=interactor;
    }

    public void setView(UserView view){
        this.view=view;
    }

    public void start(){
        view.init();
        updateUser();
    }

    private void updateUser(){
        //view.showUserLoading();
        UserModel user = interactor.getUser();
        view.setUser(user);
        interactor.getUser(this);
        //interactor.getRepositories(this);
        //interactor.getPinnedRepositories(this);

    }

    public void onUserReceived(GitHubUser user){
        this.user=user;
        view.setUser(user);
        if(repos!=null) view.hideUserLoading();
    }

    public void onReposReceived(ArrayList<GitHubRepository> repos){
        this.repos=repos;
        if(user!=null) view.hideUserLoading();
        if(pinnedRepos==null) view.setRepositories(repos);
        else updateReposWithPinned();
    }

    public void onPinnedReposReceived(ArrayList<GitHubRepository> repos){
        pinnedRepos=repos;
        updateReposWithPinned();
    }

    private void updateReposWithPinned(){
        if(repos==null||pinnedRepos==null) return;
        for(GitHubRepository repo: repos){
            for(GitHubRepository pinRepo: pinnedRepos){
                if(repo.getId()==pinRepo.getId()){
                    repos.remove(repo);
                    repos.add(0, repo);
                }
            }
        }
        view.setRepositories(repos);
    }

    public void openInBrowser(){
        interactor.openInBrowser();
    }

    public void onBackPressed(){
        view.closeView();
    }

    public void showLoadingError() {
        view.showLoadingError();
    }

    public void showParseError() {
        view.showParseError();
    }

    public void showUnexpectedError() {
        view.showUnexpectedError();
    }

    public void onFavorClick(UserModel userShort) {
        userShort.setFavorite(!userShort.isFavorite());
        interactor.notifyFavorChanged(userShort);
    }
}
