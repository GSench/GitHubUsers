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

class MainInteractor(
        private val coordinator: CoordinatorPresenter,
        private val system: SystemInterface) {
    private var userListInteractor: UserListInteractor? = null
    private val favorites: FavoritesManagement
    private var mode = MODE_SEARCH

    init {
        favorites = FavoritesManagement(system)
    }

    fun onSearchInput(text: String) {
        mode = MODE_SEARCH
        initUserListInteractor()
        val text = text.trim { it <= ' ' }
        if (text == "") return
        userListInteractor!!.setObservable(SearchObservable(text, system, favorites))
    }

    fun onFavoritesOpen() {
        mode = MODE_FAVOR
        initUserListInteractor()
        userListInteractor!!.setObservable(FavoriteObservable(favorites))
    }

    val userListUseCase: UserListUseCase?
        get() {
            initUserListInteractor()
            return userListInteractor
        }

    private val onFavoriteChanged = { user : UserModel ->
        if (user.isFavorite())
            favorites.addToFavorites(user)
        else
            favorites.removeFromFavorites(user)
        if (mode == MODE_FAVOR)
            onFavoritesOpen()
        else if (mode == MODE_SEARCH) userListInteractor!!.updateUser(user)
    }

    private fun initUserListInteractor() {
        if (userListInteractor == null)
            userListInteractor = UserListInteractor(
                    system,
                    { userModel : UserModel -> coordinator.onOpenUser(
                            UserInteractor(
                                    system,
                                    userModel,
                                    { user : GitHubUserShort -> coordinator.openInBrowser(user.htmlUrl) },
                                    onFavoriteChanged,
                                    { repo : GitHubRepository -> coordinator.openInBrowser(repo.htmlUrl) }))
                    },
                    onFavoriteChanged)
    }

    companion object {

        private val MODE_SEARCH = 1
        private val MODE_FAVOR = 2
    }
}
