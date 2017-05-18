package ru.gsench.githubusers.domain.interactor

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.usecase.UserListUseCase
import ru.gsench.githubusers.presentation.presenter.UserListPresenter
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by grish on 08.05.2017.
 */

class UserListInteractor(val system: SystemInterface,
                         private val onOpenUser: (UserModel)->Unit,
                         private val onFavoriteChanged: (UserModel)->Unit) : UserListUseCase {
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
        val queryTest = observable //execution observable on new thread
        system.doOnBackground {
            try {
                val users = observable!!.obtain(limit, offset)
                present( { presenter!!.addUsers(users.t, users.u) }, queryTest )
            } catch (e: IOException) {
                e.printStackTrace()
                present( {
                    if (!isRetry) presenter!!.showLoadingError()
                    system.doOnBackground {
                        try {
                            TimeUnit.SECONDS.sleep(1)
                        } catch (e1: InterruptedException) {
                        }

                        system.doOnForeground { updateList(limit, offset, true) }
                    }
                }, queryTest)
            } catch (e: ResponseParser.ParseException) {
                e.printStackTrace()
                present( { presenter!!.showParseError() }, queryTest)
            } catch (e: Exception) {
                e.printStackTrace()
                present({ presenter!!.showUnexpectedError() }, queryTest)
            }
            loading = false
        }
    }

    private fun present(result: ()->Unit, forRequest: UserListObservable?) {
        if (observable == null || observable != forRequest) return  //checking that results are up to date
        system.doOnForeground(result) //performing results
    }

    override fun openUser(user: UserModel) {
        onOpenUser(user)
    }

    override fun pushFavorite(user: UserModel) {
        onFavoriteChanged(user)
    }

    fun updateUser(userModel: UserModel) {
        presenter!!.updateUser(userModel)
    }
}
