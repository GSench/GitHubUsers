package ru.gsench.githubusers.presentation.view;

import java.net.URL;

import ru.gsench.githubusers.presentation.presenter.UserListPresenter;
import ru.gsench.githubusers.presentation.presenter.UserPresenter;

/**
 * Created by grish on 09.05.2017.
 */

public interface CoordinatorView {
    void init();
    void openSearchView();
    void openUserList(UserListPresenter presenter);
    void openUser(UserPresenter presenter);
    void openBrowser(URL url);
}
