package ru.gsench.githubusers.domain.interactor

import java.io.IOException
import java.util.ArrayList

import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.utils.Pair

/**
 * Created by grish on 11.05.2017.
 */

abstract class UserListObservable(var query: String?) {

    @Throws(IOException::class, ResponseParser.ParseException::class)
    abstract fun obtain(limit: Int, offset: Int): Pair<ArrayList<UserModel>, Int>

    override fun equals(obj: Any?): Boolean {
        if (obj !is UserListObservable) return false
        val compareWith = obj
        return this.query == null && compareWith.query == null || this.query != null && this.query == compareWith.query
    }
}
