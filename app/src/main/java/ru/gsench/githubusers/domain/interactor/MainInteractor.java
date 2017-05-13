package ru.gsench.githubusers.domain.interactor;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.domain.usecase.UserListUseCase;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter;

/**
 * Created by grish on 06.05.2017.
 */

public class MainInteractor {

    private SystemInterface system;
    private CoordinatorPresenter coordinator;
    private UserListInteractor userListInteractor;
    private FavoritesManagement favorites;

    private static final int MODE_SEARCH = 1;
    private static final int MODE_FAVOR = 2;
    private int mode = MODE_SEARCH;

    public MainInteractor(CoordinatorPresenter coordinator, SystemInterface system){
        this.system=system;
        this.coordinator=coordinator;
        favorites=new FavoritesManagement(system);
    }

    public void onSearchInput(String text) {
        mode=MODE_SEARCH;
        initUserListInteractor();
        text = text.trim();
        if(text.equals("")) return;
        userListInteractor.setObservable(new SearchObservable(text, system, favorites));
    }

    public void onFavoritesOpen(){
        mode=MODE_FAVOR;
        initUserListInteractor();
        userListInteractor.setObservable(new FavoriteObservable(favorites));
    }

    public UserListUseCase getUserListUseCase() {
        initUserListInteractor();
        return userListInteractor;
    }

    private function<UserModel> onFavoriteChanged = new function<UserModel>() {
        @Override
        public void run(UserModel... params) {
            if(params[0].isFavorite()) favorites.addToFavorites(params[0]);
            else favorites.removeFromFavorites(params[0]);
            if(mode==MODE_FAVOR) onFavoritesOpen();
            else if(mode==MODE_SEARCH) userListInteractor.updateUser(params[0]);
        }
    };

    private void initUserListInteractor(){
        if(userListInteractor==null)
            userListInteractor = new UserListInteractor(system, new function<UserModel>() {
                @Override
                public void run(UserModel... params) {
                    coordinator.onOpenUser(new UserInteractor(system, params[0], new function<GitHubUserShort>() {
                        @Override
                        public void run(GitHubUserShort... params) {
                            coordinator.openInBrowser(params[0].getHtmlUrl());
                        }
                    }, onFavoriteChanged));
                }
            }, onFavoriteChanged);
    }
}
