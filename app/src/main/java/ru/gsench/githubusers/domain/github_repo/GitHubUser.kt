package ru.gsench.githubusers.domain.github_repo

import java.util.Date

/**
 * Created by grish on 01.05.2017.
 */

class GitHubUser : GitHubUserShort {

    constructor(id: Int, login: String, bio: String, location: String, email: String, company: String, name: String, createdAt: Date, updatedAt: Date) : super(id, login) {
        this.bio = bio
        this.location = location
        this.email = email
        this.company = company
        this.name = name
        this.createdAt = createdAt
        this.updatedAt = updatedAt
    }

    constructor(userShort: GitHubUserShort, bio: String, location: String, email: String, company: String, name: String, createdAt: Date, updatedAt: Date) : super(userShort.id, userShort.login) {
        this.bio = bio
        this.location = location
        this.email = email
        this.company = company
        this.name = name
        this.createdAt = createdAt
        this.updatedAt = updatedAt
    }

    var bio: String
        private set
    var location: String
        private set
    var email: String
        private set
    var company: String
        private set
    var name: String
        private set
    var createdAt: Date
        private set
    var updatedAt: Date
        private set

}
