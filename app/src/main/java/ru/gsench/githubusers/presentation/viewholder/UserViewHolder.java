package ru.gsench.githubusers.presentation.viewholder;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.gsench.githubusers.R;

/**
 * Created by grish on 13.05.2017.
 */

public class UserViewHolder {

    public ViewGroup main;
    public RecyclerView content;
    public ImageView header;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public Toolbar toolbar;
    public View favorite;
    public View openInBrowser;

    public ViewGroup userInfo;
    public LinearLayout emailLayout;
    public TextView email;
    public LinearLayout bioLayout;
    public TextView bio;
    public LinearLayout companyLayout;
    public TextView company;
    public LinearLayout locationLayout;
    public TextView location;
    public TextView createdTime, updatedTime;

    public UserViewHolder(ViewGroup parent){
        main = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        content = (RecyclerView) main.findViewById(R.id.content);
        header = (ImageView) main.findViewById(R.id.header);
        collapsingToolbarLayout = (CollapsingToolbarLayout) main.findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) main.findViewById(R.id.anim_toolbar);
        favorite = main.findViewById(R.id.favorite_button);
        openInBrowser = main.findViewById(R.id.open_in_browser);

        userInfo = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info, parent, false);
        emailLayout = (LinearLayout) userInfo.findViewById(R.id.email_layout);
        email = (TextView) emailLayout.findViewById(R.id.email);
        bioLayout = (LinearLayout) userInfo.findViewById(R.id.bio_layout);
        bio = (TextView) bioLayout.findViewById(R.id.bio);
        companyLayout = (LinearLayout) userInfo.findViewById(R.id.company_layout);
        company = (TextView) companyLayout.findViewById(R.id.company);
        locationLayout = (LinearLayout) userInfo.findViewById(R.id.location_layout);
        location = (TextView) locationLayout.findViewById(R.id.location);
        createdTime = (TextView) userInfo.findViewById(R.id.created_time);
        updatedTime = (TextView) userInfo.findViewById(R.id.updated_time);
    }

}
