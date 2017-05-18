package ru.gsench.githubusers.presentation.view.aview

import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget

import java.text.DateFormat

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import ru.gsench.githubusers.R
import ru.gsench.githubusers.domain.github_repo.GitHubRepository
import ru.gsench.githubusers.domain.github_repo.GitHubUser
import ru.gsench.githubusers.domain.interactor.UserModel
import ru.gsench.githubusers.presentation.presenter.UserPresenter
import ru.gsench.githubusers.presentation.utils.AView
import ru.gsench.githubusers.presentation.utils.AViewContainer
import ru.gsench.githubusers.presentation.view.UserView
import ru.gsench.githubusers.presentation.view.view_etc.UserAdapter
import ru.gsench.githubusers.presentation.view.view_etc.ViewTools
import ru.gsench.githubusers.presentation.viewholder.UserViewHolder

/**
 * Created by grish on 13.05.2017.
 */

class UserAView(container: AViewContainer, private val presenter: UserPresenter) : AView(container), UserView {

    private val viewHolder: UserViewHolder
    private var adapter: UserAdapter? = null

    init {
        viewHolder = UserViewHolder(parent)
        presenter.setView(this)
    }

    override fun getView(): ViewGroup {
        return viewHolder.main
    }

    override fun start() {
        presenter.start()
    }

    override fun init() {
        viewHolder.back.setOnClickListener { presenter.onBackPressed() }
        adapter = UserAdapter(this)
        val mLayoutManager = LinearLayoutManager(context)
        viewHolder.content.layoutManager = mLayoutManager
        viewHolder.content.adapter = ScaleInAnimationAdapter(adapter)
    }

    override fun showWithAnimation() {

    }

    override fun setUser(param: GitHubUser) {
        viewHolder.collapsingToolbarLayout.title = param.name

        viewHolder.userInfo.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        adapter!!.userPage.addView(viewHolder.userInfo, 0)

        viewHolder.login.text = param.login

        if (param.bio != "")
            viewHolder.bio.text = param.bio
        else
            viewHolder.bioLayout.visibility = View.GONE

        if (param.email != "")
            viewHolder.email.text = param.email
        else
            viewHolder.emailLayout.visibility = View.GONE

        if (param.company != "")
            viewHolder.company.text = param.company
        else
            viewHolder.companyLayout.visibility = View.GONE

        if (param.location != "")
            viewHolder.location.text = param.location
        else
            viewHolder.locationLayout.visibility = View.GONE

        viewHolder.createdTime.text = context.getString(R.string.created_at, DateFormat.getDateInstance().format(param.createdAt))
        viewHolder.updatedTime.text = context.getString(R.string.updated_at, DateFormat.getDateInstance().format(param.updatedAt))
    }

    override fun setUser(userShort: UserModel) {
        viewHolder.openInBrowser.setOnClickListener { presenter.openInBrowser() }
        val width = context.resources.displayMetrics.widthPixels
        val height = ViewTools.dpToPx(320, context)
        Glide
                .with(context)
                .load(userShort.avatar.toString())
                .asBitmap()
                .centerCrop()
                .into(object : SimpleTarget<Bitmap>(width, height) {
                    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
                        viewHolder.header.setImageBitmap(resource)
                    }
                })
        viewHolder.collapsingToolbarLayout.title = userShort.login
        viewHolder.favorite.setOnClickListener {
            presenter.onFavorClick(userShort)
            if (userShort.isFavorite())
                viewHolder.favorite.background = ContextCompat.getDrawable(context, R.drawable.menu_is_favorite)
            else
                viewHolder.favorite.background = ContextCompat.getDrawable(context, R.drawable.menu_favorite)
        }
        if (userShort.isFavorite())
            viewHolder.favorite.background = ContextCompat.getDrawable(context, R.drawable.menu_is_favorite)
        else
            viewHolder.favorite.background = ContextCompat.getDrawable(context, R.drawable.menu_favorite)
    }

    override fun notifyReposUpdate(offset: Int, count: Int) {
        adapter!!.notifyItemsAdded(offset, count)
    }

    override fun closeView() {
        closeSelf()
    }

    override fun showLoadingError() {
        Toast.makeText(context, R.string.loading_error, Toast.LENGTH_SHORT).show()
    }

    override fun showParseError() {
        Toast.makeText(context, R.string.parse_error, Toast.LENGTH_LONG).show()
    }

    override fun showUnexpectedError() {
        Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_LONG).show()
    }

    override fun showUserLoading() {
        adapter!!.userLoading.visibility = View.VISIBLE
    }

    override fun hideUserLoading() {
        adapter!!.userLoading.visibility = View.GONE
    }

    override fun showRepoLoading() {
        adapter!!.repositoriesLoading.visibility = View.VISIBLE
    }

    override fun hideRepoLoading() {
        adapter!!.repositoriesLoading.visibility = View.INVISIBLE
    }

    fun getRepositoryAt(i: Int): GitHubRepository {
        return presenter.getRepositoryAt(i)
    }

    fun onRepoClick(repository: GitHubRepository) {
        presenter.onRepoClick(repository)
    }

    val reposCount: Int
        get() = presenter.reposCount
}
