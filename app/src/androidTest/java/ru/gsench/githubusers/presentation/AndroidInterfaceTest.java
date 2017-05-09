package ru.gsench.githubusers.presentation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import ru.gsench.githubusers.domain.utils.function;

/**
 * Created by grish on 09.05.2017.
 */
public class AndroidInterfaceTest {

    AndroidInterface androidInterface;

    @Before
    public void before(){
        androidInterface = new AndroidInterface(null);
    }

    @Test
    public void doOnBackground() throws Exception {
        androidInterface.doOnBackground(new function<Void>() {
            @Override
            public void run(Void... params) {
                Assert.assertNotEquals("main", Thread.currentThread().getName());
            }
        });
    }

    @Test
    public void httpGet() throws Exception {
        URL url = new URL("https://api.github.com/users/GSench");
        byte[] data = androidInterface.httpGet(url, null).t;
        Assert.assertEquals(
                "{\"login\":\"GSench\",\"id\":9394127,\"avatar_url\":\"https://avatars0.githubusercontent.com/u/9394127?v=3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/GSench\",\"html_url\":\"https://github.com/GSench\",\"followers_url\":\"https://api.github.com/users/GSench/followers\",\"following_url\":\"https://api.github.com/users/GSench/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/GSench/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/GSench/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/GSench/subscriptions\",\"organizations_url\":\"https://api.github.com/users/GSench/orgs\",\"repos_url\":\"https://api.github.com/users/GSench/repos\",\"events_url\":\"https://api.github.com/users/GSench/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/GSench/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"GSench\",\"company\":null,\"blog\":\"\",\"location\":\"Moscow\",\"email\":null,\"hireable\":null,\"bio\":null,\"public_repos\":4,\"public_gists\":0,\"followers\":0,\"following\":0,\"created_at\":\"2014-10-25T13:49:13Z\",\"updated_at\":\"2017-04-28T17:43:53Z\"}",
                new String(data));
    }

}