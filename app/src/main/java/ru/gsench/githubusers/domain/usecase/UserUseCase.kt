package ru.gsench.githubusers.domain.usecase

import ru.gsench.githubusers.domain.github_repo.GitHubRepository
import ru.gsench.githubusers.domain.interactor.UserModel
import ru.gsench.githubusers.presentation.presenter.UserPresenter

/**
 * Created by grish on 09.05.2017.
 */

interface UserUseCase {
    fun getUser(presenter: UserPresenter)
    val user: UserModel
    fun getRepositories(presenter: UserPresenter)
    fun getPinnedRepositories(presenter: UserPresenter)
    fun openInBrowser()
    fun notifyFavorChanged(userShort: UserModel)
    fun onRepoClick(repository: GitHubRepository)
}
