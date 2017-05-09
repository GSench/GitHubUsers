package ru.gsench.githubusers.presentation.view;

import java.net.URL;

import ru.gsench.githubusers.domain.usecase.UserListUseCase;
import ru.gsench.githubusers.domain.usecase.UserUseCase;

/**
 * Created by grish on 09.05.2017.
 */

public interface CoordinatorView {
    void init();
    void openSearchView();
    void openUserList(UserListUseCase userListUseCase);
    void openUser(UserUseCase useCase);
    void openBrowser(URL url);
}
