package ru.gsench.githubusers.presentation.view.view_etc;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DateFormat;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.github_repo.GitHubRepository;
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
        final GitHubRepository repository = userAView.getRepositoryAt(i);
        viewHolder.title.setText(repository.getName());
        viewHolder.description.setText(repository.getDescription());
        viewHolder.language.setText(repository.getLanguage());
        viewHolder.update.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(repository.getUpdatedAt()));
        viewHolder.forks.setText(String.valueOf(repository.getForks()));
        viewHolder.stars.setText(String.valueOf(repository.getStars()));
        viewHolder.repoClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAView.onRepoClick(repository);
            }
        });
        if(repository.isFork()) viewHolder.fork.setImageDrawable(ContextCompat.getDrawable(userAView.context, R.drawable.ic_share));
        else viewHolder.fork.setImageDrawable(null);
        if(repository.isPrivateRepo()) viewHolder.privateRepo.setImageDrawable(ContextCompat.getDrawable(userAView.context, R.drawable.ic_lock));
        else viewHolder.privateRepo.setImageDrawable(ContextCompat.getDrawable(userAView.context, R.drawable.ic_lock_open));
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
        return new RepositoryViewHolder(LayoutInflater.from(userAView.context).inflate(R.layout.repository, parent, false));
    }

    @Override
    public FooterViewHolder onCreateFooterViewHolder(ViewGroup parent) {
        return new RepoFooterViewHolder(LayoutInflater.from(userAView.context).inflate(R.layout.recycler_view_footer, parent, false));
    }

    @Override
    public int getNormalItemsCount() {
        return userAView.getReposCount();
    }

    class RepositoryViewHolder extends NormalViewHolder{
        private View repoClick;
        private TextView title;
        private ImageView fork;
        private ImageView privateRepo;
        private TextView description;
        private TextView language;
        private TextView update;
        private TextView forks;
        private TextView stars;
        public RepositoryViewHolder(View itemView) {
            super(itemView);
            repoClick = itemView.findViewById(R.id.repo_click);
            title = (TextView) itemView.findViewById(R.id.title);
            fork = (ImageView) itemView.findViewById(R.id.fork);
            privateRepo = (ImageView) itemView.findViewById(R.id.private_repo);
            description = (TextView) itemView.findViewById(R.id.description);
            language = (TextView) itemView.findViewById(R.id.language);
            update = (TextView) itemView.findViewById(R.id.updated_time);
            forks = (TextView) itemView.findViewById(R.id.forks);
            stars = (TextView) itemView.findViewById(R.id.stars);
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
