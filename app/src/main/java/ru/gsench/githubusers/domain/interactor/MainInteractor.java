package ru.gsench.githubusers.domain.interactor;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubRequests;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.domain.usecase.UserListUseCase;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter;

/**
 * Created by grish on 06.05.2017.
 */

public class MainInteractor {

    private GitHubRequests requests;
    private SystemInterface system;
    private CoordinatorPresenter coordinator;
    private UserListInteractor userListInteractor;

    public MainInteractor(CoordinatorPresenter coordinator, SystemInterface system){
        this.system=system;
        this.coordinator=coordinator;
    }

    public void onSearchInput(String text) {
        initUserListInteractor();
        text = text.trim();
        if(text.equals("")) return;
        userListInteractor.searchFor(text);
    }

    public UserListUseCase getUserListUseCase() {
        initUserListInteractor();
        return userListInteractor;
    }

    private void initUserListInteractor(){
        if(userListInteractor==null)
            userListInteractor = new UserListInteractor(system, new function<GitHubUserShort>() {
                @Override
                public void run(GitHubUserShort... params) {
                    coordinator.onOpenUser(new UserInteractor(system, params[0], new function<GitHubUserShort>() {
                        @Override
                        public void run(GitHubUserShort... params) {
                            coordinator.openInBrowser(params[0].getHtmlUrl());
                        }
                    }));
                }
            });
    }
}
