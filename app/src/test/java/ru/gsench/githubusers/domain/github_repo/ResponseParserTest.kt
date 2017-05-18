package ru.gsench.githubusers.domain.github_repo

import org.junit.Assert
import org.junit.Test

import java.util.ArrayList

/**
 * Created by Григорий Сенченок on 04.05.2017.
 */

class ResponseParserTest {

    @Test
    fun parseSearchResultsTestCaseNorm() {
        var result: ArrayList<GitHubUserShort>? = null
        try {
            result = ResponseParser.parseSearchResults(
                    "{\n" +
                            "  \"total_count\": 4,\n" +
                            "  \"incomplete_results\": false,\n" +
                            "  \"items\": [\n" +
                            "    {\n" +
                            "      \"login\": \"mojombo\",\n" +
                            "      \"id\": 1,\n" +
                            "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/1?v=3\",\n" +
                            "      \"gravatar_id\": \"\",\n" +
                            "      \"url\": \"https://api.github.com/users/mojombo\",\n" +
                            "      \"html_url\": \"https://github.com/mojombo\",\n" +
                            "      \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
                            "      \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
                            "      \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
                            "      \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
                            "      \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
                            "      \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
                            "      \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
                            "      \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
                            "      \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
                            "      \"type\": \"User\",\n" +
                            "      \"site_admin\": false,\n" +
                            "      \"score\": 49.412918\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"login\": \"tmcw\",\n" +
                            "      \"id\": 32314,\n" +
                            "      \"avatar_url\": \"https://avatars1.githubusercontent.com/u/32314?v=3\",\n" +
                            "      \"gravatar_id\": \"\",\n" +
                            "      \"url\": \"https://api.github.com/users/tmcw\",\n" +
                            "      \"html_url\": \"https://github.com/tmcw\",\n" +
                            "      \"followers_url\": \"https://api.github.com/users/tmcw/followers\",\n" +
                            "      \"following_url\": \"https://api.github.com/users/tmcw/following{/other_user}\",\n" +
                            "      \"gists_url\": \"https://api.github.com/users/tmcw/gists{/gist_id}\",\n" +
                            "      \"starred_url\": \"https://api.github.com/users/tmcw/starred{/owner}{/repo}\",\n" +
                            "      \"subscriptions_url\": \"https://api.github.com/users/tmcw/subscriptions\",\n" +
                            "      \"organizations_url\": \"https://api.github.com/users/tmcw/orgs\",\n" +
                            "      \"repos_url\": \"https://api.github.com/users/tmcw/repos\",\n" +
                            "      \"events_url\": \"https://api.github.com/users/tmcw/events{/privacy}\",\n" +
                            "      \"received_events_url\": \"https://api.github.com/users/tmcw/received_events\",\n" +
                            "      \"type\": \"User\",\n" +
                            "      \"site_admin\": false,\n" +
                            "      \"score\": 39.44359\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"login\": \"tomdale\",\n" +
                            "      \"id\": 90888,\n" +
                            "      \"avatar_url\": \"https://avatars1.githubusercontent.com/u/90888?v=3\",\n" +
                            "      \"gravatar_id\": \"\",\n" +
                            "      \"url\": \"https://api.github.com/users/tomdale\",\n" +
                            "      \"html_url\": \"https://github.com/tomdale\",\n" +
                            "      \"followers_url\": \"https://api.github.com/users/tomdale/followers\",\n" +
                            "      \"following_url\": \"https://api.github.com/users/tomdale/following{/other_user}\",\n" +
                            "      \"gists_url\": \"https://api.github.com/users/tomdale/gists{/gist_id}\",\n" +
                            "      \"starred_url\": \"https://api.github.com/users/tomdale/starred{/owner}{/repo}\",\n" +
                            "      \"subscriptions_url\": \"https://api.github.com/users/tomdale/subscriptions\",\n" +
                            "      \"organizations_url\": \"https://api.github.com/users/tomdale/orgs\",\n" +
                            "      \"repos_url\": \"https://api.github.com/users/tomdale/repos\",\n" +
                            "      \"events_url\": \"https://api.github.com/users/tomdale/events{/privacy}\",\n" +
                            "      \"received_events_url\": \"https://api.github.com/users/tomdale/received_events\",\n" +
                            "      \"type\": \"User\",\n" +
                            "      \"site_admin\": false,\n" +
                            "      \"score\": 19.849504\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"login\": \"tommy351\",\n" +
                            "      \"id\": 411425,\n" +
                            "      \"avatar_url\": \"https://avatars3.githubusercontent.com/u/411425?v=3\",\n" +
                            "      \"gravatar_id\": \"\",\n" +
                            "      \"url\": \"https://api.github.com/users/tommy351\",\n" +
                            "      \"html_url\": \"https://github.com/tommy351\",\n" +
                            "      \"followers_url\": \"https://api.github.com/users/tommy351/followers\",\n" +
                            "      \"following_url\": \"https://api.github.com/users/tommy351/following{/other_user}\",\n" +
                            "      \"gists_url\": \"https://api.github.com/users/tommy351/gists{/gist_id}\",\n" +
                            "      \"starred_url\": \"https://api.github.com/users/tommy351/starred{/owner}{/repo}\",\n" +
                            "      \"subscriptions_url\": \"https://api.github.com/users/tommy351/subscriptions\",\n" +
                            "      \"organizations_url\": \"https://api.github.com/users/tommy351/orgs\",\n" +
                            "      \"repos_url\": \"https://api.github.com/users/tommy351/repos\",\n" +
                            "      \"events_url\": \"https://api.github.com/users/tommy351/events{/privacy}\",\n" +
                            "      \"received_events_url\": \"https://api.github.com/users/tommy351/received_events\",\n" +
                            "      \"type\": \"User\",\n" +
                            "      \"site_admin\": false,\n" +
                            "      \"score\": 15.481528\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}"
            ).t
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val expected = ArrayList<GitHubUserShort>()
        try {
            expected.add(GitHubUserShort(1, "mojombo"))
            expected.add(GitHubUserShort(32314, "tmcw"))
            expected.add(GitHubUserShort(90888, "tomdale"))
            expected.add(GitHubUserShort(411425, "tommy351"))
        } catch (e: Exception) {
        }

        Assert.assertNotNull(result)
        Assert.assertEquals(expected.size.toLong(), result!!.size.toLong())
        for (i in expected.indices) {
            Assert.assertEquals(expected[i].id.toLong(), result[i].id.toLong())
            Assert.assertEquals(expected[i].login, result[i].login)
        }
    }

    @Test
    fun parseRepositoriesTestCaseNorm() {

    }

    @Test
    fun parseUserTestCaseNorm() {

    }

    @Test(expected = Exception::class)
    @Throws(Exception::class)
    fun parseSearchResultsTestCaseNull() {
        val result = ResponseParser.parseSearchResults(null!!).t
    }

    @Test
    fun parseRepositoriesTestCaseNull() {

    }

    @Test
    fun parseUserTestCaseNull() {

    }

    @Test
    fun parseSearchResultsTestCaseWrong() {

    }

    @Test
    fun parseRepositoriesTestCaseWrong() {

    }

    @Test
    fun parseUserTestCaseWrong() {

    }

}
