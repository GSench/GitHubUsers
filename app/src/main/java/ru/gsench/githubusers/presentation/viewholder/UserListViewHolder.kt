package ru.gsench.githubusers.presentation.viewholder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import ru.gsench.githubusers.R

/**
 * Created by grish on 10.05.2017.
 */

class UserListViewHolder(parent: ViewGroup) {

    var recyclerView: RecyclerView

    init {
        recyclerView = LayoutInflater.from(parent.context).inflate(R.layout.user_list_layout, parent, false) as RecyclerView
    }
}
