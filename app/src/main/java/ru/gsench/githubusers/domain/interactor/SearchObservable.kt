package ru.gsench.githubusers.domain.interactor

import java.io.IOException
import java.net.URL
import java.util.ArrayList

import ru.gsench.githubusers.domain.SystemInterface
import ru.gsench.githubusers.domain.github_repo.GitHubRequests
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort
import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.utils.Pair

/**
 * Created by grish on 11.05.2017.
 */

class SearchObservable(query: String,
                       private val system: SystemInterface,
                       private val favorites: FavoritesManagement) : UserListObservable(query) {

    @Throws(IOException::class, ResponseParser.ParseException::class)
    override fun obtain(limit: Int, offset: Int): Pair<ArrayList<UserModel>, Int> {
        val url = GitHubRequests.searchUser(query, limit, offset)
        val result = String(system.httpGet(url!!, null).t)
        val users = ResponseParser.parseSearchResults(result)
        return Pair(
                favorites.sortFavorites(users.t),
                if (users.u > 1000) 1000 else users.u)
    }
}
