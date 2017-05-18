package ru.gsench.githubusers.presentation.view

/**
 * Created by grish on 01.04.2017.
 */

interface UserListView {

    fun init()
    fun notifyUsersAdded(offset: Int, count: Int)
    fun clearList()
    fun showLoadingError()
    fun showParseError()
    fun showUnexpectedError()
    fun closeView()
    fun hideLoading()
    fun showLoading()
    fun notifyUserChanged(i: Int)
}
