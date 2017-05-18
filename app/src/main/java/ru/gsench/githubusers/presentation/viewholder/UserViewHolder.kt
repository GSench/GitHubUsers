package ru.gsench.githubusers.presentation.viewholder

import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import ru.gsench.githubusers.R

/**
 * Created by grish on 13.05.2017.
 */

class UserViewHolder(parent: ViewGroup) {

    var main: ViewGroup
    var content: RecyclerView
    var header: ImageView
    var collapsingToolbarLayout: CollapsingToolbarLayout
    var toolbar: Toolbar
    var favorite: View
    var openInBrowser: View
    var back: Button
    var login: TextView

    var userInfo: ViewGroup
    var emailLayout: LinearLayout
    var email: TextView
    var bioLayout: LinearLayout
    var bio: TextView
    var companyLayout: LinearLayout
    var company: TextView
    var locationLayout: LinearLayout
    var location: TextView
    var createdTime: TextView
    var updatedTime: TextView

    init {
        main = LayoutInflater.from(parent.context).inflate(R.layout.user_layout, parent, false) as ViewGroup
        content = main.findViewById(R.id.content) as RecyclerView
        header = main.findViewById(R.id.header) as ImageView
        collapsingToolbarLayout = main.findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        toolbar = main.findViewById(R.id.anim_toolbar) as Toolbar
        favorite = main.findViewById(R.id.favorite_button)
        openInBrowser = main.findViewById(R.id.open_in_browser)
        back = main.findViewById(R.id.back_btn) as Button

        userInfo = LayoutInflater.from(parent.context).inflate(R.layout.user_info, parent, false) as ViewGroup
        login = userInfo.findViewById(R.id.login) as TextView
        emailLayout = userInfo.findViewById(R.id.email_layout) as LinearLayout
        email = emailLayout.findViewById(R.id.email) as TextView
        bioLayout = userInfo.findViewById(R.id.bio_layout) as LinearLayout
        bio = bioLayout.findViewById(R.id.bio) as TextView
        companyLayout = userInfo.findViewById(R.id.company_layout) as LinearLayout
        company = companyLayout.findViewById(R.id.company) as TextView
        locationLayout = userInfo.findViewById(R.id.location_layout) as LinearLayout
        location = locationLayout.findViewById(R.id.location) as TextView
        createdTime = userInfo.findViewById(R.id.created_time) as TextView
        updatedTime = userInfo.findViewById(R.id.updated_time) as TextView
    }

}
