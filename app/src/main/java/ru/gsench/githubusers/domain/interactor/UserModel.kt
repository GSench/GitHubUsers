package ru.gsench.githubusers.domain.interactor

import ru.gsench.githubusers.domain.github_repo.GitHubUserShort

/**
 * Created by grish on 11.05.2017.
 */

class UserModel : GitHubUserShort {

    fun isFavorite(): Boolean {
        return isFavorite
    }

    private var isFavorite = false

    constructor(id: Int, login: String) : super(id, login) {}

    constructor(id: Int, login: String, favorite: Boolean) : super(id, login) {
        isFavorite = favorite
    }

    constructor(user: GitHubUserShort, favorite: Boolean) : super(user.id, user.login) {
        this.isFavorite = favorite
    }

    fun setFavorite(favor: Boolean): UserModel {
        isFavorite = favor
        return this
    }
}
