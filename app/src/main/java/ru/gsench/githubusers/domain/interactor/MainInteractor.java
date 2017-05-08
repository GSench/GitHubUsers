package ru.gsench.githubusers.domain.interactor;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubRequests;
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter;

/**
 * Created by grish on 06.05.2017.
 */

public class MainInteractor {

    private GitHubRequests requests;
    private SystemInterface system;
    CoordinatorPresenter coordinator;

    public MainInteractor(CoordinatorPresenter coordinator, SystemInterface system){
        this.system=system;
        this.coordinator=coordinator;
    }

}
