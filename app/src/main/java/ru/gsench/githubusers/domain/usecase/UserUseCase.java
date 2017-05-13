package ru.gsench.githubusers.domain.usecase;

import ru.gsench.githubusers.domain.interactor.UserModel;
import ru.gsench.githubusers.presentation.presenter.UserPresenter;

/**
 * Created by grish on 09.05.2017.
 */

public interface UserUseCase {
    public void getUser(UserPresenter presenter);
    public UserModel getUser();
    public void getRepositories(UserPresenter presenter);
    public void getPinnedRepositories(UserPresenter presenter);
    public void openInBrowser();
    public void notifyFavorChanged(UserModel userShort);
}
