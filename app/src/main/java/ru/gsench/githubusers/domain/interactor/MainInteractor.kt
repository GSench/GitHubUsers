package ru.gsench.githubusers.domain.interactor

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.github_repo.GitHubRepository
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort
import ru.gsench.githubusers.domain.usecase.UserListUseCase
import ru.gsench.githubusers.domain.utils.function
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter

/**
 * Created by grish on 06.05.2017.
 */

class MainInteractor(private val coordinator: CoordinatorPresenter, private val system: SystemInterface) {
    private var userListInteractor: UserListInteractor? = null
    private val favorites: FavoritesManagement
    private var mode = MODE_SEARCH

    init {
        favorites = FavoritesManagement(system)
    }

    fun onSearchInput(text: String) {
        var text = text
        mode = MODE_SEARCH
        initUserListInteractor()
        text = text.trim { it <= ' ' }
        if (text == "") return
        userListInteractor!!.setObservable(SearchObservable(text, system, favorites))
    }

    fun onFavoritesOpen() {
        mode = MODE_FAVOR
        initUserListInteractor()
        userListInteractor!!.setObservable(FavoriteObservable(favorites))
    }

    val userListUseCase: UserListUseCase
        get() {
            initUserListInteractor()
            return userListInteractor
        }

    private val onFavoriteChanged = function<UserModel> { params ->
        if (params[0].isFavorite)
            favorites.addToFavorites(params[0])
        else
            favorites.removeFromFavorites(params[0])
        if (mode == MODE_FAVOR)
            onFavoritesOpen()
        else if (mode == MODE_SEARCH) userListInteractor!!.updateUser(params[0])
    }

    private fun initUserListInteractor() {
        if (userListInteractor == null)
            userListInteractor = UserListInteractor(system, function<UserModel> { params ->
                coordinator.onOpenUser(UserInteractor(system, params[0], function<GitHubUserShort> { params -> coordinator.openInBrowser(params[0].htmlUrl) }, onFavoriteChanged,
                        function<GitHubRepository> { params -> coordinator.openInBrowser(params[0].htmlUrl) }))
            }, onFavoriteChanged)
    }

    companion object {

        private val MODE_SEARCH = 1
        private val MODE_FAVOR = 2
    }
}
