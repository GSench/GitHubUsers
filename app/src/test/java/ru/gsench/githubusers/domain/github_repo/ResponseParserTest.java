package ru.gsench.githubusers.domain.github_repo;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Григорий Сенченок on 04.05.2017.
 */

public class ResponseParserTest {

    @Test
    public void parseSearchResultsTestCaseNorm(){
        ArrayList<GitHubUserShort> result = null;
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
            ).t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<GitHubUserShort> expected = new ArrayList<>();
        try {
            expected.add(new GitHubUserShort(1, "mojombo"));
            expected.add(new GitHubUserShort(32314, "tmcw"));
            expected.add(new GitHubUserShort(90888, "tomdale"));
            expected.add(new GitHubUserShort(411425, "tommy351"));
        } catch (Exception e){}
        Assert.assertNotNull(result);
        Assert.assertEquals(expected.size(), result.size());
        for(int i=0; i<expected.size(); i++){
            Assert.assertEquals(expected.get(i).getId(), result.get(i).getId());
            Assert.assertEquals(expected.get(i).getLogin(), result.get(i).getLogin());
        }
    }

    @Test
    public void parseRepositoriesTestCaseNorm(){

    }

    @Test
    public void parseUserTestCaseNorm(){

    }

    @Test(expected = Exception.class)
    public void parseSearchResultsTestCaseNull() throws Exception {
        ArrayList<GitHubUserShort> result = ResponseParser.parseSearchResults(null).t;
    }

    @Test
    public void parseRepositoriesTestCaseNull(){

    }

    @Test
    public void parseUserTestCaseNull(){

    }

    @Test
    public void parseSearchResultsTestCaseWrong(){

    }

    @Test
    public void parseRepositoriesTestCaseWrong(){

    }

    @Test
    public void parseUserTestCaseWrong(){

    }

}
