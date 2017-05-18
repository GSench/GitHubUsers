package ru.gsench.githubusers.presentation.view

import java.net.URL

import ru.gsench.githubusers.presentation.presenter.UserListPresenter
import ru.gsench.githubusers.presentation.presenter.UserPresenter

/**
 * Created by grish on 09.05.2017.
 */

interface CoordinatorView {
    fun init()
    fun openSearchView()
    fun openUserList(presenter: UserListPresenter)
    fun openUser(presenter: UserPresenter)
    fun openBrowser(url: URL)
    fun closeView()
    fun closeUser()
    val isUserOpened: Boolean
}
