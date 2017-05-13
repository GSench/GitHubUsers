package ru.gsench.githubusers.presentation.view.view_etc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.presentation.utils.FooterViewHolder;
import ru.gsench.githubusers.presentation.utils.HeaderFooterAdapter;
import ru.gsench.githubusers.presentation.utils.HeaderViewHolder;
import ru.gsench.githubusers.presentation.utils.NormalViewHolder;
import ru.gsench.githubusers.presentation.view.aview.UserAView;

/**
 * Created by grish on 13.05.2017.
 */

public class UserAdapter extends HeaderFooterAdapter<UserAdapter.UserInfoViewHolder, UserAdapter.RepositoryViewHolder, UserAdapter.RepoFooterViewHolder> {

    private UserAView userAView;

    public ProgressBar userLoading;
    public ProgressBar repositoriesLoading;
    public LinearLayout userPage;

    public UserAdapter(UserAView userAView) {
        this.userAView=userAView;
    }

    @Override
    public void onBindHeaderViewHolder(UserInfoViewHolder viewHolder) {

    }

    @Override
    public void onBindNormalViewHolder(RepositoryViewHolder viewHolder, int i) {

    }

    @Override
    public void onBindFooterViewHolder(RepoFooterViewHolder viewHolder) {

    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new UserInfoViewHolder(LayoutInflater.from(userAView.context).inflate(R.layout.user_page, parent, false));
    }

    @Override
    public NormalViewHolder onCreateNormalViewHolder(ViewGroup parent) {
        return new RepositoryViewHolder(new LinearLayout(userAView.context));
    }

    @Override
    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return new RepoFooterViewHolder(LayoutInflater.from(userAView.context).inflate(R.layout.recycler_view_footer, parent, false));
    }

    @Override
    public int getNormalItemsCount() {
        return 0;
    }

    class RepositoryViewHolder extends NormalViewHolder{

        public RepositoryViewHolder(View itemView) {
            super(itemView);
        }
    }
    public class UserInfoViewHolder extends HeaderViewHolder{
        public UserInfoViewHolder(View itemView) {//is R.layout.user_page
            super(itemView);
            userPage = (LinearLayout) itemView;
            userLoading = (ProgressBar) userPage.findViewById(R.id.user_loading);
        }
    }

    public class RepoFooterViewHolder extends FooterViewHolder{
        public RepoFooterViewHolder(View itemView) {//is R.layout.recycler_view_footer
            super(itemView);
            repositoriesLoading = (ProgressBar) itemView.findViewById(R.id.progress_bar);
        }
    }

}
