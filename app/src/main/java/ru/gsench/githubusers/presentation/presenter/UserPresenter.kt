package ru.gsench.githubusers.presentation.presenter

import java.util.ArrayList

import ru.gsench.githubusers.domain.github_repo.GitHubRepository
import ru.gsench.githubusers.domain.github_repo.GitHubUser
import ru.gsench.githubusers.domain.interactor.UserModel
import ru.gsench.githubusers.domain.usecase.UserUseCase
import ru.gsench.githubusers.presentation.view.UserView

/**
 * Created by grish on 09.05.2017.
 */

class UserPresenter(private val interactor: UserUseCase) {
    private var view: UserView? = null

    private var user: GitHubUser? = null
    private var repos: ArrayList<GitHubRepository>? = null
    private var pinnedRepos: ArrayList<GitHubRepository>? = null

    fun setView(view: UserView) {
        this.view = view
    }

    fun start() {
        view!!.init()
        updateUser()
    }

    private fun updateUser() {
        val user = interactor.user
        repos = ArrayList<GitHubRepository>()
        view!!.setUser(user)
        interactor.getUser(this)
        interactor.getRepositories(this)
        //interactor.getPinnedRepositories(this);

    }

    fun onUserReceived(user: GitHubUser) {
        this.user = user
        view!!.setUser(user)
        view!!.hideUserLoading()
    }

    fun onReposReceived(repos: ArrayList<GitHubRepository>) {
        this.repos!!.addAll(repos)
        view!!.hideRepoLoading()
        if (pinnedRepos == null)
            view!!.notifyReposUpdate(0, this.repos!!.size)
        else
            updateReposWithPinned()
    }

    fun onPinnedReposReceived(repos: ArrayList<GitHubRepository>) {
        pinnedRepos = repos
        updateReposWithPinned()
    }

    private fun updateReposWithPinned() {
        if (repos == null || pinnedRepos == null) return
        for (repo in repos!!) {
            for (pinRepo in pinnedRepos!!) {
                if (repo.id == pinRepo.id) {
                    repos!!.remove(repo)
                    repos!!.add(0, repo)
                }
            }
        }
        view!!.notifyReposUpdate(0, repos!!.size)
    }

    fun openInBrowser() {
        interactor.openInBrowser()
    }

    fun onBackPressed() {
        view!!.closeView()
    }

    fun showLoadingError() {
        view!!.showLoadingError()
    }

    fun showParseError() {
        view!!.showParseError()
    }

    fun showUnexpectedError() {
        view!!.showUnexpectedError()
    }

    fun onFavorClick(userShort: UserModel) {
        userShort.setFavorite(!userShort.isFavorite())
        interactor.notifyFavorChanged(userShort)
    }

    fun getRepositoryAt(i: Int): GitHubRepository {
        return repos!![i]
    }

    fun onRepoClick(repository: GitHubRepository) {
        interactor.onRepoClick(repository)
    }

    val reposCount: Int
        get() = repos!!.size
}
