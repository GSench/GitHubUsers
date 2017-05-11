package ru.gsench.githubusers.presentation.view.aview;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;
import ru.gsench.githubusers.presentation.utils.AView;
import ru.gsench.githubusers.presentation.utils.AViewContainer;
import ru.gsench.githubusers.presentation.view.UserListView;
import ru.gsench.githubusers.presentation.view.view_etc.UserListAdapter;
import ru.gsench.githubusers.presentation.viewholder.UserListViewHolder;

/**
 * Created by grish on 10.05.2017.
 */

public class UserListAView extends AView implements UserListView {

    private UserListPresenter presenter;
    private UserListViewHolder viewHolder;
    private UserListAdapter adapter;

    public UserListAView(AViewContainer container, UserListPresenter presenter) {
        super(container);
        viewHolder = new UserListViewHolder(parent);
        this.presenter=presenter;
        presenter.setView(this);
    }

    @Override
    protected ViewGroup getView() {
        return viewHolder.recyclerView;
    }

    @Override
    protected void start() {
        presenter.start();
    }

    @Override
    public void init() {
        viewHolder.recyclerView.setItemAnimator(new LandingAnimator());
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        viewHolder.recyclerView.setLayoutManager(mLayoutManager);
        viewHolder.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    int visibleItemCount = mLayoutManager.getChildCount();
                    int totalItemCount = mLayoutManager.getItemCount();
                    int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                    presenter.scrolled(visibleItemCount, totalItemCount, pastVisibleItems);
                }
            }
        });
        adapter = new UserListAdapter(this);
        viewHolder.recyclerView.setAdapter(new ScaleInAnimationAdapter(adapter));
    }

    @Override
    public void notifyUsersAdded(int offset, int count) {
        adapter.notifyItemAdded(offset, count);
    }

    @Override
    public void clearList() {
        adapter.clearList();
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
    public void closeView() {

    }

    @Override
    public void hideLoading() {
        adapter.hideLoading();
    }

    @Override
    public void showLoading() {
        adapter.showLoading();
    }

    public GitHubUserShort getUserAt(int i) {
        return presenter.getUserAt(i);
    }

    public int getUserCount() {
        return presenter.getUserCount();
    }

    public void addToFavor(GitHubUserShort user) {
        presenter.addToFavor(user);
    }

    public void onUserClicked(GitHubUserShort user) {
        presenter.onUserClicked(user);
    }
}
