package ru.gsench.githubusers.domain.github_repo

import java.io.UnsupportedEncodingException
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

/**
 * Created by grish on 01.05.2017.
 */

object GitHubRequests {

    fun searchUser(searchFor: String, limit: Int, offset: Int): URL? {
        try {
            return URL("https://api.github.com/search/users?q=" + URLEncoder.encode(searchFor, "utf-8") + "&page=" + (offset / limit + 1) + "&per_page=" + limit)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            return null
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
            return null
        }

    }

}
