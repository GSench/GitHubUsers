package ru.gsench.githubusers.presentation.presenter;

import java.net.URL;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.interactor.MainInteractor;
import ru.gsench.githubusers.domain.usecase.UserUseCase;
import ru.gsench.githubusers.presentation.view.CoordinatorView;

/**
 * Created by grish on 07.05.2017.
 */

public class CoordinatorPresenter {

    private MainInteractor interactor;
    private CoordinatorView view;

    public CoordinatorPresenter(SystemInterface system){
        interactor = new MainInteractor(this, system);
    }

    public void setView(CoordinatorView view){
        this.view=view;
    }

    public void start(){
        view.init();
    }

    public void onSearchInput(String text){
        view.openUserList(new UserListPresenter(interactor.getUserListUseCase()));
        interactor.onSearchInput(text);
    }

    public void onStartButton(){
        view.openSearchView();
    }

    public void onOpenUser(UserUseCase useCase){
        view.openUser(new UserPresenter(useCase));
    }

    public void openInBrowser(URL url){
        view.openBrowser(url);
    }

}
