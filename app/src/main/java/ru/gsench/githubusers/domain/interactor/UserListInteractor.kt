package ru.gsench.githubusers.domain.interactor

import java.io.IOException
import java.util.ArrayList
import java.util.concurrent.TimeUnit

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.usecase.UserListUseCase
import ru.gsench.githubusers.domain.utils.Pair
import ru.gsench.githubusers.domain.utils.function
import ru.gsench.githubusers.presentation.presenter.UserListPresenter

/**
 * Created by grish on 08.05.2017.
 */

class UserListInteractor(private val system: SystemInterface, private val onOpenUser: function<UserModel>, private val onFavoriteChanged: function<UserModel>) : UserListUseCase {
    private var presenter: UserListPresenter? = null
    private var observable: UserListObservable? = null
    private var loading = false

    init {
        loading = false
    }

    //initializing search observable
    fun setObservable(observable: UserListObservable) {
        this.observable = observable
        subscribe()
    }

    //subscribe presenter to interactor for updating if search observable is initialized
    override fun subscribe(presenter: UserListPresenter) {
        this.presenter = presenter
        subscribe()
    }

    private fun subscribe() {
        if (presenter == null || observable == null) return  //checking search observable & subscription to be initialized
        loading = false //dropping previous observable if it's exist
        presenter!!.clearList() //clearing presenter if it's filled
        presenter!!.updateList() //then notifying presenter that observable is initialized
    }

    override fun updateList(limit: Int, offset: Int) {
        updateList(limit, offset, false)
    }

    private fun updateList(limit: Int, offset: Int, isRetry: Boolean) {
        if (observable == null || presenter == null || loading) return
        loading = true //protect for multiply queries
        val queryTest = observable
        system.doOnBackground //execution observable on new thread
        {
            var callback: function<Void>
            try {
                val users = observable!!.obtain(limit, offset)
                callback = function<Void> { presenter!!.addUsers(users.t, users.u) }
            } catch (e: IOException) {
                e.printStackTrace()
                callback = function<Void> {
                    if (!isRetry) presenter!!.showLoadingError()
                    system.doOnBackground {
                        try {
                            TimeUnit.SECONDS.sleep(1)
                        } catch (e1: InterruptedException) {
                        }

                        system.doOnForeground { updateList(limit, offset, true) }
                    }
                }
            } catch (e: ResponseParser.ParseException) {
                e.printStackTrace()
                callback = function<Void> { presenter!!.showParseError() }
            } catch (e: Exception) {
                e.printStackTrace()
                callback = function<Void> { presenter!!.showUnexpectedError() }
            }

            loading = false
            present(callback, queryTest)
        }
    }

    private fun present(result: function<Void>, forRequest: UserListObservable) {
        if (observable == null || observable != forRequest) return  //checking that results are up to date
        system.doOnForeground(result) //performing results
    }

    override fun openUser(user: UserModel) {
        onOpenUser.run(user)
    }

    override fun pushFavorite(user: UserModel) {
        onFavoriteChanged.run(user)
    }

    fun updateUser(userModel: UserModel) {
        presenter!!.updateUser(userModel)
    }
}
