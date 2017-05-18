package ru.gsench.githubusers.presentation.view

import ru.gsench.githubusers.domain.github_repo.GitHubUser
import ru.gsench.githubusers.domain.interactor.UserModel

/**
 * Created by grish on 09.05.2017.
 */

interface UserView {
    fun init()
    fun showWithAnimation()
    fun setUser(param: GitHubUser)
    fun setUser(userShort: UserModel)
    fun notifyReposUpdate(offset: Int, count: Int)
    fun closeView()
    fun showLoadingError()
    fun showParseError()
    fun showUnexpectedError()
    fun showUserLoading()
    fun hideUserLoading()
    fun showRepoLoading()
    fun hideRepoLoading()
}
