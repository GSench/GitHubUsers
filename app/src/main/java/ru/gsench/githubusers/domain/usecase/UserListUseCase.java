package ru.gsench.githubusers.domain.usecase;

import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;

/**
 * Created by grish on 06.05.2017.
 */

public interface UserListUseCase {
    public void subscribe(UserListPresenter presenter);
    public void updateList(int limit, int offset);
    public void openUser(GitHubUserShort user);
}
