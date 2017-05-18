package ru.gsench.githubusers.domain.interactor

import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit

import ru.gsench.githubusers.domain.utils.function
import ru.gsench.githubusers.presentation.FakeSystem
import ru.gsench.githubusers.presentation.presenter.UserListPresenter
import ru.gsench.githubusers.presentation.view.FakeUserListView

/**
 * Created by grish on 08.05.2017.
 */
class UserListInteractorTest {

    internal var interactor: UserListInteractor? = null
    internal var presenter: UserListPresenter = null!!
    internal var userListView: FakeUserListView
    internal var system: FakeSystem

    internal var alicePage1 =
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
                    "}"
    internal var alicePage2 =
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
                    "}"
    internal var tomPage1 =
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
                    "}"

    @Before
    fun setUp() {
        system = FakeSystem()
        interactor = UserListInteractor(
                system,
                { Assert.assertNotNull(it) },
                { }
        )
        presenter = UserListPresenter(interactor!!)
        userListView = FakeUserListView()
        userListView.init()
        presenter.setView(userListView)
        presenter.start()
    }

    @Test
    @Throws(Exception::class)
    fun searchFor() {
        system.addRequest(alicePage1)

        println("---------Testing setObservable---------")
        interactor!!.setObservable(SearchObservable("Alice", system, FavoritesManagement(system)))
        TimeUnit.SECONDS.sleep(4)
        Assert.assertEquals(1, presenter.users!!.size.toLong())
        Assert.assertEquals("alice", presenter.users!![0].login)
        println("Test successful")
    }

    @Test
    @Throws(Exception::class)
    fun searchWithBreaking() {
        system.addRequest(alicePage1)
        system.addRequest(tomPage1)

        println("---------Testing searchWithBreaking---------")
        interactor!!.setObservable(SearchObservable("Alice", system, FavoritesManagement(system)))
        TimeUnit.SECONDS.sleep(1)
        interactor!!.setObservable(SearchObservable("Tom", system, FavoritesManagement(system)))
        TimeUnit.SECONDS.sleep(4)
        Assert.assertEquals(1, presenter.users!!.size.toLong())
        Assert.assertEquals("tom", presenter.users!![0].login)
        println("Test successful")
    }

    @Test
    @Throws(Exception::class)
    fun searchWithContinuing() {
        system.addRequest(alicePage1)
        system.addRequest(alicePage2)

        println("---------Testing searchWithContinuing---------")
        interactor!!.setObservable(SearchObservable("Alice", system, FavoritesManagement(system)))
        TimeUnit.SECONDS.sleep(4)
        Assert.assertEquals(1, presenter.users!!.size.toLong())
        Assert.assertEquals("alice", presenter.users!![0].login)
        presenter.scrolled(1, 1, 0)
        TimeUnit.SECONDS.sleep(4)
        Assert.assertEquals(2, presenter.users!!.size.toLong())
        Assert.assertEquals("alice", presenter.users!![0].login)
        Assert.assertEquals("mozamimy", presenter.users!![1].login)
        println("Test successful")
    }

    @Test
    @Throws(Exception::class)
    fun subscribe() {

    }

    @Test
    @Throws(Exception::class)
    fun updateList() {

    }

}