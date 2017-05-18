package ru.gsench.githubusers.domain.interactor

import java.io.IOException
import java.util.ArrayList

import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.utils.Pair

/**
 * Created by grish on 11.05.2017.
 */

abstract class UserListObservable(var query: String) {

    @Throws(IOException::class, ResponseParser.ParseException::class)
    abstract fun obtain(limit: Int, offset: Int): Pair<ArrayList<UserModel>, Int>

    override fun equals(other: Any?): Boolean {
        if (other !is UserListObservable) return false
        val compareWith = other
        return this.query == compareWith.query
    }
}
