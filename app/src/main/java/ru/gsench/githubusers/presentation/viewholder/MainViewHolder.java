package ru.gsench.githubusers.presentation.viewholder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;

import io.codetail.widget.RevealFrameLayout;
import ru.gsench.githubusers.R;

/**
 * Created by grish on 10.05.2017.
 */

public class MainViewHolder {

    public View background;
    public LinearLayout helloContent;
    public ImageView githubImage;
    public TextView helloWord;
    public ImageView searchImage;
    public FloatingSearchView searchView;
    public RevealFrameLayout searchViewContainer;
    public RelativeLayout userSearchContent;
    public RelativeLayout viewContainer;
    public RelativeLayout backgroundContainer;
    public View backgroundCircle;
    public RevealFrameLayout backgroundFullContainer;

    public MainViewHolder(ViewGroup parent){
        background = parent.findViewById(R.id.background);
        helloWord = (TextView) parent.findViewById(R.id.hello_word);
        helloContent = (LinearLayout) parent.findViewById(R.id.hello_content);
        githubImage = (ImageView) parent.findViewById(R.id.github_image);
        searchImage = (ImageView) parent.findViewById(R.id.search_image);
        userSearchContent = (RelativeLayout) parent.findViewById(R.id.user_list_content);
        searchViewContainer = (RevealFrameLayout) parent.findViewById(R.id.floating_search_view_container);
        searchView = (FloatingSearchView) LayoutInflater.from(parent.getContext()).inflate(R.layout.floating_search_view, searchViewContainer, false);
        viewContainer = (RelativeLayout) parent.findViewById(R.id.view_container);
        backgroundCircle = parent.findViewById(R.id.background_circle);
        backgroundContainer = (RelativeLayout) parent.findViewById(R.id.background_container);
        backgroundFullContainer = (RevealFrameLayout) parent.findViewById(R.id.background_full_container);
    }

}