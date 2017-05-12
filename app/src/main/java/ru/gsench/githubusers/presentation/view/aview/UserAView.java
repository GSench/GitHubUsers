package ru.gsench.githubusers.presentation.view.aview;

import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.github_repo.GitHubRepository;
import ru.gsench.githubusers.domain.github_repo.GitHubUser;
import ru.gsench.githubusers.domain.interactor.UserModel;
import ru.gsench.githubusers.presentation.utils.AView;
import ru.gsench.githubusers.presentation.utils.AViewContainer;
import ru.gsench.githubusers.presentation.view.UserView;

/**
 * Created by grish on 13.05.2017.
 */

public class UserAView extends AView implements UserView {

    public UserAView(AViewContainer context) {
        super(context);
    }

    @Override
    protected ViewGroup getView() {
        return null;
    }

    @Override
    protected void start() {

    }

    @Override
    public void init() {

    }

    @Override
    public void showWithAnimation() {

    }

    @Override
    public void setUser(GitHubUser param) {

    }

    @Override
    public void setUser(UserModel userShort) {

    }

    @Override
    public void setRepositories(ArrayList<GitHubRepository> param) {

    }

    @Override
    public void closeView() {

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
