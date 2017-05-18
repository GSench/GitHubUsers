package ru.gsench.githubusers.domain.usecase

import ru.gsench.githubusers.domain.interactor.UserModel
import ru.gsench.githubusers.presentation.presenter.UserListPresenter

/**
 * Created by grish on 06.05.2017.
 */

interface UserListUseCase {
    fun subscribe(presenter: UserListPresenter)
    fun updateList(limit: Int, offset: Int)
    fun openUser(user: UserModel)
    fun pushFavorite(user: UserModel)
}
