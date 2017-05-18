package ru.gsench.githubusers.domain.github_repo

import java.net.MalformedURLException
import java.net.URL
import java.util.Date

/**
 * Created by grish on 01.05.2017.
 */

class GitHubRepository(
        val id: Int,
        val name: String,
        val owner: GitHubUserShort,
        val isPrivateRepo: Boolean,
        val isFork: Boolean,
        val description: String,
        val language: String,
        val forks: Int,
        val createdAt: Date,
        val updatedAt: Date,
        val stars: Int) {

    val url: URL
        get() {
            try {
                return URL("https://api.github.com/repos/" + owner.login + "/" + name)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return null!!
        }

    val htmlUrl: URL
        get() {
            try {
                return URL("https://github.com/" + owner.login + "/" + name)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return null!!
        }

}
