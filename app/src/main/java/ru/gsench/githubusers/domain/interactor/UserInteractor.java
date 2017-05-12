package ru.gsench.githubusers.domain.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubRepository;
import ru.gsench.githubusers.domain.github_repo.GitHubUser;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.domain.github_repo.ResponseParser;
import ru.gsench.githubusers.domain.usecase.UserUseCase;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.presenter.UserPresenter;

/**
 * Created by grish on 09.05.2017.
 */

public class UserInteractor implements UserUseCase {

    private SystemInterface system;
    private GitHubUserFavor user;
    private function<GitHubUserShort> openInBrowser;

    public UserInteractor(SystemInterface system, GitHubUserFavor user, function<GitHubUserShort> openInBrowser) {
        this.system = system;
        this.user = user;
        this.openInBrowser=openInBrowser;
    }

    @Override
    public void getUser(final UserPresenter presenter) {
        system.doOnBackground(
                new function<Void>() {
                    @Override
                    public void run(Void... params) {
                        function<Void> callback;
                        try {
                            URL url = user.getUrl();
                            String result = new String(system.httpGet(url, null).t);
                            final GitHubUser user = ResponseParser.parseUser(result);
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.onUserReceived(user); }
                            };
                        } catch (IOException e){
                            e.printStackTrace();
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.showLoadingError(); }
                            };
                        } catch (ResponseParser.ParseException e) {
                            e.printStackTrace();
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.showParseError(); }
                            };
                        } catch (Exception e){
                            e.printStackTrace();
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.showUnexpectedError(); }
                            };
                        }
                        system.doOnForeground(callback);
                    }
                }
        );
    }

    @Override
    public GitHubUserFavor getUser() {
        return user;
    }

    @Override
    public void getRepositories(final UserPresenter presenter) {
        system.doOnBackground(
                new function<Void>() {
                    @Override
                    public void run(Void... params) {
                        function<Void> callback;
                        try {
                            URL url = user.getUrl();
                            String result = new String(system.httpGet(url, null).t);
                            final ArrayList<GitHubRepository> repos = ResponseParser.parseRepositories(result);
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.onReposReceived(repos); }
                            };
                        } catch (IOException e){
                            e.printStackTrace();
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.showLoadingError(); }
                            };
                        } catch (ResponseParser.ParseException e) {
                            e.printStackTrace();
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.showParseError(); }
                            };
                        } catch (Exception e){
                            e.printStackTrace();
                            callback = new function<Void>() {
                                @Override
                                public void run(Void... params) { presenter.showUnexpectedError(); }
                            };
                        }
                        system.doOnForeground(callback);
                    }
                }
        );
    }

    @Override
    public void getPinnedRepositories(final UserPresenter presenter) {
        //TODO getPinnedRepositories
    }

    @Override
    public void openInBrowser() {
        openInBrowser.run(user);
    }
}
