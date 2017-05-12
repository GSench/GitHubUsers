package ru.gsench.githubusers.domain.interactor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.FakeSystem;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;
import ru.gsench.githubusers.presentation.view.FakeUserListView;

/**
 * Created by grish on 08.05.2017.
 */
public class UserListInteractorTest {

    UserListInteractor interactor;
    UserListPresenter presenter;
    FakeUserListView userListView;
    FakeSystem system;

    String alicePage1 =
            "{\n" +
                    "  \"total_count\": 4050,\n" +
                    "  \"incomplete_results\": false,\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"login\": \"alice\",\n" +
                    "      \"id\": 95208,\n" +
                    "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/95208?v=3\",\n" +
                    "      \"gravatar_id\": \"\",\n" +
                    "      \"url\": \"https://api.github.com/users/alice\",\n" +
                    "      \"html_url\": \"https://github.com/alice\",\n" +
                    "      \"followers_url\": \"https://api.github.com/users/alice/followers\",\n" +
                    "      \"following_url\": \"https://api.github.com/users/alice/following{/other_user}\",\n" +
                    "      \"gists_url\": \"https://api.github.com/users/alice/gists{/gist_id}\",\n" +
                    "      \"starred_url\": \"https://api.github.com/users/alice/starred{/owner}{/repo}\",\n" +
                    "      \"subscriptions_url\": \"https://api.github.com/users/alice/subscriptions\",\n" +
                    "      \"organizations_url\": \"https://api.github.com/users/alice/orgs\",\n" +
                    "      \"repos_url\": \"https://api.github.com/users/alice/repos\",\n" +
                    "      \"events_url\": \"https://api.github.com/users/alice/events{/privacy}\",\n" +
                    "      \"received_events_url\": \"https://api.github.com/users/alice/received_events\",\n" +
                    "      \"type\": \"User\",\n" +
                    "      \"site_admin\": false,\n" +
                    "      \"score\": 103.71144\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
    String alicePage2 =
            "{\n" +
                    "  \"total_count\": 4050,\n" +
                    "  \"incomplete_results\": false,\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"login\": \"mozamimy\",\n" +
                    "      \"id\": 1119006,\n" +
                    "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/1119006?v=3\",\n" +
                    "      \"gravatar_id\": \"\",\n" +
                    "      \"url\": \"https://api.github.com/users/mozamimy\",\n" +
                    "      \"html_url\": \"https://github.com/mozamimy\",\n" +
                    "      \"followers_url\": \"https://api.github.com/users/mozamimy/followers\",\n" +
                    "      \"following_url\": \"https://api.github.com/users/mozamimy/following{/other_user}\",\n" +
                    "      \"gists_url\": \"https://api.github.com/users/mozamimy/gists{/gist_id}\",\n" +
                    "      \"starred_url\": \"https://api.github.com/users/mozamimy/starred{/owner}{/repo}\",\n" +
                    "      \"subscriptions_url\": \"https://api.github.com/users/mozamimy/subscriptions\",\n" +
                    "      \"organizations_url\": \"https://api.github.com/users/mozamimy/orgs\",\n" +
                    "      \"repos_url\": \"https://api.github.com/users/mozamimy/repos\",\n" +
                    "      \"events_url\": \"https://api.github.com/users/mozamimy/events{/privacy}\",\n" +
                    "      \"received_events_url\": \"https://api.github.com/users/mozamimy/received_events\",\n" +
                    "      \"type\": \"User\",\n" +
                    "      \"site_admin\": false,\n" +
                    "      \"score\": 39.217987\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
    String tomPage1 =
            "{\n" +
                    "  \"total_count\": 51147,\n" +
                    "  \"incomplete_results\": false,\n" +
                    "  \"items\": [\n" +
                    "    {\n" +
                    "      \"login\": \"tom\",\n" +
                    "      \"id\": 748,\n" +
                    "      \"avatar_url\": \"https://avatars2.githubusercontent.com/u/748?v=3\",\n" +
                    "      \"gravatar_id\": \"\",\n" +
                    "      \"url\": \"https://api.github.com/users/tom\",\n" +
                    "      \"html_url\": \"https://github.com/tom\",\n" +
                    "      \"followers_url\": \"https://api.github.com/users/tom/followers\",\n" +
                    "      \"following_url\": \"https://api.github.com/users/tom/following{/other_user}\",\n" +
                    "      \"gists_url\": \"https://api.github.com/users/tom/gists{/gist_id}\",\n" +
                    "      \"starred_url\": \"https://api.github.com/users/tom/starred{/owner}{/repo}\",\n" +
                    "      \"subscriptions_url\": \"https://api.github.com/users/tom/subscriptions\",\n" +
                    "      \"organizations_url\": \"https://api.github.com/users/tom/orgs\",\n" +
                    "      \"repos_url\": \"https://api.github.com/users/tom/repos\",\n" +
                    "      \"events_url\": \"https://api.github.com/users/tom/events{/privacy}\",\n" +
                    "      \"received_events_url\": \"https://api.github.com/users/tom/received_events\",\n" +
                    "      \"type\": \"User\",\n" +
                    "      \"site_admin\": false,\n" +
                    "      \"score\": 70.84543\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

    @Before
    public void setUp() {
        system = new FakeSystem();
        interactor = new UserListInteractor(
                system,
                new function<GitHubUserFavor>() {
                    @Override
                    public void run(GitHubUserFavor... params) {
                        Assert.assertNotNull(params[0]);
                    }
                },
                new function<GitHubUserFavor>() {
                    @Override
                    public void run(GitHubUserFavor... params) {

                    }
                }
        );
        presenter = new UserListPresenter(interactor);
        userListView = new FakeUserListView();
        userListView.init();
        presenter.setView(userListView);
        presenter.start();
    }

    @Test
    public void searchFor() throws Exception {
        system.addRequest(alicePage1);

        System.out.println("---------Testing setObservable---------");
        interactor.setObservable(new SearchObservable("Alice", system, new FavoritesManagement(system)));
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(1, presenter.getUsers().size());
        Assert.assertEquals("alice", presenter.getUsers().get(0).getLogin());
        System.out.println("Test successful");
    }

    @Test
    public void searchWithBreaking() throws Exception {
        system.addRequest(alicePage1);
        system.addRequest(tomPage1);

        System.out.println("---------Testing searchWithBreaking---------");
        interactor.setObservable(new SearchObservable("Alice", system, new FavoritesManagement(system)));
        TimeUnit.SECONDS.sleep(1);
        interactor.setObservable(new SearchObservable("Tom", system, new FavoritesManagement(system)));
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(1, presenter.getUsers().size());
        Assert.assertEquals("tom", presenter.getUsers().get(0).getLogin());
        System.out.println("Test successful");
    }

    @Test
    public void searchWithContinuing() throws Exception {
        system.addRequest(alicePage1);
        system.addRequest(alicePage2);

        System.out.println("---------Testing searchWithContinuing---------");
        interactor.setObservable(new SearchObservable("Alice", system, new FavoritesManagement(system)));
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(1, presenter.getUsers().size());
        Assert.assertEquals("alice", presenter.getUsers().get(0).getLogin());
        presenter.scrolled(1, 1, 0);
        TimeUnit.SECONDS.sleep(4);
        Assert.assertEquals(2, presenter.getUsers().size());
        Assert.assertEquals("alice", presenter.getUsers().get(0).getLogin());
        Assert.assertEquals("mozamimy", presenter.getUsers().get(1).getLogin());
        System.out.println("Test successful");
    }

    @Test
    public void subscribe() throws Exception {

    }

    @Test
    public void updateList() throws Exception {

    }

}