package ru.gsench.githubusers.presentation.view.aview;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.github_repo.GitHubRepository;
import ru.gsench.githubusers.domain.github_repo.GitHubUser;
import ru.gsench.githubusers.domain.interactor.UserModel;
import ru.gsench.githubusers.presentation.presenter.UserPresenter;
import ru.gsench.githubusers.presentation.utils.AView;
import ru.gsench.githubusers.presentation.utils.AViewContainer;
import ru.gsench.githubusers.presentation.view.UserView;
import ru.gsench.githubusers.presentation.view.view_etc.UserAdapter;
import ru.gsench.githubusers.presentation.viewholder.UserViewHolder;

/**
 * Created by grish on 13.05.2017.
 */

public class UserAView extends AView implements UserView {

    private UserViewHolder viewHolder;
    private UserPresenter presenter;
    private UserAdapter adapter;

    public UserAView(AViewContainer container, UserPresenter presenter) {
        super(container);
        viewHolder = new UserViewHolder(parent);
        this.presenter=presenter;
        presenter.setView(this);
    }

    @Override
    protected ViewGroup getView() {
        return viewHolder.main;
    }

    @Override
    protected void start() {
        presenter.start();
    }

    @Override
    public void init() {
        viewHolder.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onBackPressed();
            }
        });
        adapter = new UserAdapter(this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        viewHolder.content.setLayoutManager(mLayoutManager);
        viewHolder.content.setAdapter(new ScaleInAnimationAdapter(adapter));
    }

    @Override
    public void showWithAnimation() {

    }

    @Override
    public void setUser(GitHubUser param) {
        viewHolder.collapsingToolbarLayout.setTitle(param.getName());

        adapter.userPage.addView(viewHolder.userInfo, 0);

        if(param.getBio()!=null) viewHolder.bio.setText(param.getBio());
        else viewHolder.bioLayout.setVisibility(View.GONE);

        if(param.getEmail()!=null) viewHolder.email.setText(param.getEmail());
        else viewHolder.emailLayout.setVisibility(View.GONE);

        if(param.getCompany()!=null) viewHolder.company.setText(param.getCompany());
        else viewHolder.companyLayout.setVisibility(View.GONE);

        if(param.getLocation()!=null) viewHolder.location.setText(param.getLocation());
        else viewHolder.locationLayout.setVisibility(View.GONE);

        viewHolder.createdTime.setText(context.getString(R.string.created_at, DateFormat.getDateInstance().format(param.getCreatedAt())));
        viewHolder.updatedTime.setText(context.getString(R.string.updated_at, DateFormat.getDateInstance().format(param.getUpdatedAt())));
    }

    @Override
    public void setUser(final UserModel userShort) {
        viewHolder.openInBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openInBrowser();
            }
        });
        Glide
                .with(context)
                .load(userShort.getAvatar().toString())
                .centerCrop()
                .crossFade()
                .placeholder(R.drawable.user_big)
                .into(viewHolder.header);
        viewHolder.collapsingToolbarLayout.setTitle(userShort.getLogin());
        viewHolder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onFavorClick(userShort);
                if(userShort.isFavorite()) viewHolder.favorite.setBackground(ContextCompat.getDrawable(context, R.drawable.menu_is_favorite));
                else viewHolder.favorite.setBackground(ContextCompat.getDrawable(context, R.drawable.menu_favorite));
            }
        });
        if(userShort.isFavorite()) viewHolder.favorite.setBackground(ContextCompat.getDrawable(context, R.drawable.menu_is_favorite));
        else viewHolder.favorite.setBackground(ContextCompat.getDrawable(context, R.drawable.menu_favorite));
    }

    @Override
    public void setRepositories(ArrayList<GitHubRepository> param) {

    }

    @Override
    public void closeView() {
        closeSelf();
    }

    @Override
    public void showLoadingError() {
        Toast.makeText(context, R.string.loading_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showParseError() {
        Toast.makeText(context, R.string.parse_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUnexpectedError() {
        Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showUserLoading() {
        adapter.userLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideUserLoading() {
        adapter.userLoading.setVisibility(View.GONE);
    }

    @Override
    public void showRepoLoading() {
        adapter.repositoriesLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRepoLoading() {
        adapter.repositoriesLoading.setVisibility(View.INVISIBLE);
    }

}