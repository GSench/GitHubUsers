package ru.gsench.githubusers.domain.usecase;

import ru.gsench.githubusers.presentation.presenter.UserListPresenter;

/**
 * Created by grish on 06.05.2017.
 */

public interface UserListUseCase {
    public void updateList(int limit, int offset, UserListPresenter userListPresenter);
}
