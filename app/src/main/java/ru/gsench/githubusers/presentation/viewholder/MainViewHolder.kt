package ru.gsench.githubusers.presentation.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.arlib.floatingsearchview.FloatingSearchView

import io.codetail.widget.RevealFrameLayout
import ru.gsench.githubusers.R

/**
 * Created by grish on 10.05.2017.
 */

class MainViewHolder(parent: ViewGroup) {

    var background: View
    var helloContent: LinearLayout
    var githubImage: ImageView
    var helloWord: TextView
    var searchImage: ImageView
    var searchView: FloatingSearchView
    var searchViewContainer: RevealFrameLayout
    var userSearchContent: RelativeLayout
    var viewContainer: RelativeLayout
    var backgroundContainer: RelativeLayout
    var backgroundCircle: View
    var backgroundFullContainer: RevealFrameLayout

    init {
        background = parent.findViewById(R.id.background)
        helloWord = parent.findViewById(R.id.hello_word) as TextView
        helloContent = parent.findViewById(R.id.hello_content) as LinearLayout
        githubImage = parent.findViewById(R.id.github_image) as ImageView
        searchImage = parent.findViewById(R.id.search_image) as ImageView
        userSearchContent = parent.findViewById(R.id.user_list_content) as RelativeLayout
        searchViewContainer = parent.findViewById(R.id.floating_search_view_container) as RevealFrameLayout
        searchView = LayoutInflater.from(parent.context).inflate(R.layout.floating_search_view, searchViewContainer, false) as FloatingSearchView
        viewContainer = parent.findViewById(R.id.view_container) as RelativeLayout
        backgroundCircle = parent.findViewById(R.id.background_circle)
        backgroundContainer = parent.findViewById(R.id.background_container) as RelativeLayout
        backgroundFullContainer = parent.findViewById(R.id.background_full_container) as RevealFrameLayout
    }

}
