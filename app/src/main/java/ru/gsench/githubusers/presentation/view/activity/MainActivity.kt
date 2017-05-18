package ru.gsench.githubusers.presentation.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

import java.net.URL

import ru.gsench.githubusers.R
import ru.gsench.githubusers.domain.utils.function
import ru.gsench.githubusers.presentation.AndroidInterface
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter
import ru.gsench.githubusers.presentation.presenter.UserListPresenter
import ru.gsench.githubusers.presentation.presenter.UserPresenter
import ru.gsench.githubusers.presentation.utils.AViewContainer
import ru.gsench.githubusers.presentation.view.CoordinatorView
import ru.gsench.githubusers.presentation.view.aview.UserAView
import ru.gsench.githubusers.presentation.view.aview.UserListAView
import ru.gsench.githubusers.presentation.view.view_etc.AnimationManager
import ru.gsench.githubusers.presentation.view.view_etc.PermissionManager
import ru.gsench.githubusers.presentation.view.view_etc.SuggestionsManager
import ru.gsench.githubusers.presentation.viewholder.MainViewHolder

class MainActivity : AppCompatActivity(), CoordinatorView {

    private var presenter: CoordinatorPresenter? = null
    private var permissionManager: PermissionManager? = null
    private var suggestionsManager: SuggestionsManager? = null
    private var viewHolder: MainViewHolder? = null
    private var userContainer: AViewContainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewHolder = MainViewHolder(findViewById(R.id.activity_main) as ViewGroup)
        permissionManager = PermissionManager(this)
        permissionManager!!.requestBasePermissions(this) {
            presenter = CoordinatorPresenter(AndroidInterface(this@MainActivity))
            presenter!!.setView(this@MainActivity)
            presenter!!.start()
        }
    }

    override fun init() {
        val onStartBtn = View.OnClickListener { presenter!!.onStartButton() }
        viewHolder!!.helloContent.setOnClickListener(onStartBtn)
        viewHolder!!.searchImage.setOnClickListener(onStartBtn)
        suggestionsManager = SuggestionsManager(viewHolder!!.searchView)
        suggestionsManager!!.init()
        viewHolder!!.searchView.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion) {
                presenter!!.onSearchInput(searchSuggestion.body)
            }

            override fun onSearchAction(currentQuery: String) {
                suggestionsManager!!.saveSuggestion(currentQuery.trim { it <= ' ' })
                presenter!!.onSearchInput(currentQuery)
            }
        })
        viewHolder!!.searchView.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.favorites -> {
                    viewHolder!!.searchView.clearQuery()
                    viewHolder!!.searchView.clearSearchFocus()
                    presenter!!.onFavoritesMenuClick()
                }
            }
        }
        viewHolder!!.searchView.setOnQueryChangeListener { oldQuery, newQuery -> suggestionsManager!!.suggest(newQuery) }
        viewHolder!!.searchView.setOnFocusChangeListener(object : FloatingSearchView.OnFocusChangeListener {
            override fun onFocus() {
                suggestionsManager!!.suggest(viewHolder!!.searchView.query)
            }

            override fun onFocusCleared() {

            }
        })
        userContainer = AViewContainer(viewHolder!!.viewContainer)
    }

    override fun openSearchView() {
        viewHolder!!.searchImage.setOnClickListener(null)
        viewHolder!!.helloContent.setOnClickListener(null)
        AnimationManager.openSearchView(viewHolder) { viewHolder!!.searchView.setSearchFocused(true) }
    }

    override fun openUserList(presenter: UserListPresenter) {
        UserListAView(AViewContainer(viewHolder!!.userSearchContent), presenter).open()
    }

    override fun openUser(presenter: UserPresenter) {
        UserAView(userContainer, presenter).open()
    }

    override fun openBrowser(url: URL) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url.toString())))
    }

    override fun closeView() {
        finish()
    }

    override fun closeUser() {
        userContainer!!.closeView()
    }

    override fun isUserOpened(): Boolean {
        return userContainer!!.viewOpened()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionManager!!.onPermissionCallback(requestCode, permissions, grantResults)
    }

    override fun onBackPressed() {
        presenter!!.onBackPressed()
    }
}
