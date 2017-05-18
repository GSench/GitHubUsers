package ru.gsench.githubusers.domain.interactor

import java.io.IOException
import java.util.ArrayList

import ru.gsench.githubusers.domain.github_repo.ResponseParser
import ru.gsench.githubusers.domain.utils.Pair

/**
 * Created by grish on 12.05.2017.
 */

class FavoriteObservable(private val favorites: FavoritesManagement) : UserListObservable(FavoritesManagement.FAVORITES) {

    @Throws(IOException::class, ResponseParser.ParseException::class)
    override fun obtain(limit: Int, offset: Int): Pair<ArrayList<UserModel>, Int> {
        return Pair(favorites.getFavorites(), favorites.getFavorites().size)
    }
}
