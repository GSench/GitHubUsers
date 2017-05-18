package ru.gsench.githubusers.presentation.presenter

import java.net.URL

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.interactor.MainInteractor
import ru.gsench.githubusers.domain.usecase.UserUseCase
import ru.gsench.githubusers.presentation.view.CoordinatorView

import android.R.attr.id

/**
 * Created by grish on 07.05.2017.
 */

class CoordinatorPresenter(system: SystemInterface) {

    private val interactor: MainInteractor
    private var view: CoordinatorView? = null

    init {
        interactor = MainInteractor(this, system)
    }

    fun setView(view: CoordinatorView) {
        this.view = view
    }

    fun start() {
        view!!.init()
    }

    fun onSearchInput(text: String) {
        interactor.onSearchInput(text)
    }

    fun onStartButton() {
        view!!.openSearchView()
        view!!.openUserList(UserListPresenter(interactor.userListUseCase))
    }

    fun onOpenUser(useCase: UserUseCase) {
        view!!.openUser(UserPresenter(useCase))
    }

    fun openInBrowser(url: URL) {
        view!!.openBrowser(url)
    }

    fun onFavoritesMenuClick() {
        interactor.onFavoritesOpen()
    }

    fun onBackPressed() {
        if (view!!.isUserOpened)
            view!!.closeUser()
        else
            view!!.closeView()
    }
}
