package ru.gsench.githubusers.domain.github_repo

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

import ru.gsench.githubusers.domain.utils.Pair

/**
 * Created by grish on 01.05.2017.
 */

object ResponseParser {

    private val githubDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    @Throws(ParseException::class)
    fun parseSearchResults(response: String): Pair<ArrayList<GitHubUserShort>, Int> {
        try {
            val parser = JsonParser()
            val mainObject = parser.parse(response).asJsonObject

            val totalCount = mainObject.get("total_count").asInt
            if (totalCount == 0) Pair(ArrayList<GitHubUserShort>(), 0)

            val users = mainObject.getAsJsonArray("items")
            val userShorts = ArrayList<GitHubUserShort>()

            for (i in 0..users.size() - 1) userShorts.add(initUserShort(users.get(i).asJsonObject))

            return Pair(userShorts, totalCount)
        } catch (t: Throwable) {
            throw ParseException()
        }

    }

    @Throws(ParseException::class)
    private fun initUserShort(user: JsonObject): GitHubUserShort {
        try {
            val id = user.get("id").asInt
            val login = user.get("login").asString
            return GitHubUserShort(id, login)
        } catch (t: Throwable) {
            throw ParseException()
        }

    }

    @Throws(ParseException::class)
    fun parseRepositories(response: String): ArrayList<GitHubRepository> {
        try {
            val parser = JsonParser()
            val result = ArrayList<GitHubRepository>()
            val array = parser.parse(response).asJsonArray

            var id: JsonElement
            var forks: JsonElement
            var stars: JsonElement
            var name: JsonElement
            var desc: JsonElement
            var lang: JsonElement
            var privateRepo: JsonElement
            var fork: JsonElement
            var owner: GitHubUserShort
            var created: Date
            var updated: Date

            var rawRepo: JsonObject

            for (i in 0..array.size() - 1) {
                rawRepo = array.get(i).asJsonObject
                id = rawRepo.get("id")
                name = rawRepo.get("name")
                owner = initUserShort(rawRepo.get("owner").asJsonObject)
                privateRepo = rawRepo.get("private")
                fork = rawRepo.get("fork")
                desc = rawRepo.get("description")
                lang = rawRepo.get("language")
                forks = rawRepo.get("forks")
                stars = rawRepo.get("stargazers_count")
                created = githubDateFormat.parse(rawRepo.get("created_at").asString)
                updated = githubDateFormat.parse(rawRepo.get("updated_at").asString)
                result.add(GitHubRepository(
                        if (!id.isJsonNull) id.asInt else -1,
                        if (!name.isJsonNull) name.asString else null,
                        owner,
                        !privateRepo.isJsonNull && privateRepo.asBoolean,
                        !fork.isJsonNull && fork.asBoolean,
                        if (!desc.isJsonNull) desc.asString else null,
                        if (!lang.isJsonNull) lang.asString else null,
                        if (!forks.isJsonNull) forks.asInt else 0,
                        created, updated,
                        if (!stars.isJsonNull) stars.asInt else 0))
            }
            return result
        } catch (t: Throwable) {
            throw ParseException()
        }

    }

    @Throws(ParseException::class)
    fun parseUser(response: String): GitHubUser {
        try {
            val parser = JsonParser()
            val mainObject = parser.parse(response).asJsonObject
            val userShort = initUserShort(mainObject)
            val bio: JsonElement
            val location: JsonElement
            val company: JsonElement
            val email: JsonElement
            val name: JsonElement
            bio = mainObject.get("bio")
            location = mainObject.get("location")
            company = mainObject.get("company")
            email = mainObject.get("email")
            name = mainObject.get("name")
            val created = githubDateFormat.parse(mainObject.get("created_at").asString)
            val updated = githubDateFormat.parse(mainObject.get("updated_at").asString)
            return GitHubUser(
                    userShort,
                    if (!bio.isJsonNull) bio.asString else null,
                    if (!location.isJsonNull) location.asString else null,
                    if (!email.isJsonNull) email.asString else null,
                    if (!company.isJsonNull) company.asString else null,
                    if (!name.isJsonNull) name.asString else userShort.login,
                    created, updated)
        } catch (t: Throwable) {
            t.printStackTrace()
            throw ParseException()
        }

    }

    class ParseException : Exception()

}
