package ru.gsench.githubusers.domain.github_repo

import java.net.MalformedURLException
import java.net.URL

/**
 * Created by grish on 01.05.2017.
 */

open class GitHubUserShort(val id: Int, val login: String) {

    val url: URL
        get() {
            var ret:URL?=null
            try {
                ret= URL("https://api.github.com/users/" + login)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return ret!!
        }

    val htmlUrl: URL
        get() {
            var ret: URL? = null
            try {
                ret = URL("https://github.com/" + login)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return ret!!
        }

    val avatar: URL
        get() {
            var ret:URL?=null
            try {
                ret= URL("https://avatars0.githubusercontent.com/u/$id?v=3")
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return ret!!
        }

    val repositories: URL
        get() {
            var ret:URL?=null
            try {
                ret = URL("https://api.github.com/users/$login/repos")
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
            return ret!!
        }

}
