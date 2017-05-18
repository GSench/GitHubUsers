package ru.gsench.githubusers.domain.interactor

import java.io.IOException
import java.net.URL
import java.util.ArrayList

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.github_repo.GitHubRepository
import ru.gsench.githubusers.domain.github_repo.GitHubUser
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort
import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.usecase.UserUseCase
import ru.gsench.githubusers.domain.utils.function
import ru.gsench.githubusers.presentation.presenter.UserPresenter

/**
 * Created by grish on 09.05.2017.
 */

class UserInteractor(private val system: SystemInterface, private val user: UserModel, private val openInBrowser: function<GitHubUserShort>, private val onFavoriteChanged: function<UserModel>, private val openRepo: function<GitHubRepository>) : UserUseCase {

    override fun getUser(presenter: UserPresenter) {
        system.doOnBackground(
                function<Void> {
                    var callback: function<Void>
                    try {
                        val url = user.url
                        val result = String(system.httpGet(url, null).t)
                        val user = ResponseParser.parseUser(result)
                        callback = function<Void> { presenter.onUserReceived(user) }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        callback = function<Void> { presenter.showLoadingError() }
                    } catch (e: ResponseParser.ParseException) {
                        e.printStackTrace()
                        callback = function<Void> { presenter.showParseError() }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        callback = function<Void> { presenter.showUnexpectedError() }
                    }

                    system.doOnForeground(callback)
                }
        )
    }

    override fun getUser(): UserModel {
        return user
    }

    override fun getRepositories(presenter: UserPresenter) {
        system.doOnBackground(
                function<Void> {
                    var callback: function<Void>
                    try {
                        val url = user.repositories
                        val result = String(system.httpGet(url, null).t)
                        val repos = ResponseParser.parseRepositories(result)
                        callback = function<Void> { presenter.onReposReceived(repos) }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        callback = function<Void> { presenter.showLoadingError() }
                    } catch (e: ResponseParser.ParseException) {
                        e.printStackTrace()
                        callback = function<Void> { presenter.showParseError() }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        callback = function<Void> { presenter.showUnexpectedError() }
                    }

                    system.doOnForeground(callback)
                }
        )
    }

    override fun getPinnedRepositories(presenter: UserPresenter) {
        //TODO getPinnedRepositories
    }

    override fun openInBrowser() {
        openInBrowser.run(user)
    }

    override fun notifyFavorChanged(userShort: UserModel) {
        onFavoriteChanged.run(user)
    }

    override fun onRepoClick(repository: GitHubRepository) {
        openRepo.run(repository)
    }
}
