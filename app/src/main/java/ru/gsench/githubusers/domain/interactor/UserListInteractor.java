package ru.gsench.githubusers.domain.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubRequests;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.domain.github_repo.ResponseParser;
import ru.gsench.githubusers.domain.usecase.UserListUseCase;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;

/**
 * Created by grish on 08.05.2017.
 */

public class UserListInteractor implements UserListUseCase {

    private SystemInterface system;
    private UserListPresenter presenter;
    private function<GitHubUserShort> onOpenUser;
    private String query;
    private boolean loading = false;

    public UserListInteractor(SystemInterface system, function<GitHubUserShort> onOpenUser){
        this.system=system;
        this.onOpenUser=onOpenUser;
        loading=false;
    }

    //initializing search query
    public void searchFor(String query){
        this.query=query;
        subscribe();
    }

    //subscribe presenter to interactor for updating if search query is initialized
    @Override
    public void subscribe(UserListPresenter presenter) {
        this.presenter=presenter;
        subscribe();
    }

    private void subscribe(){
        if(presenter==null||query==null) return; //checking search query & subscription to be initialized
        loading=false; //dropping previous query if it's exist
        presenter.clearList(); //clearing presenter if it's filled
        presenter.scrolled(0, 0, 0); //then notifying presenter that query is initialized
    }

    @Override
    public void updateList(final int limit, final int offset) {
        if(query==null||presenter==null||loading) return;
        loading=true; //protect for multiply queries
        final String queryTest = query;
        system.doOnBackground(new function<Void>() { //execution query on new thread
            @Override
            public void run(Void... params) {
                function<Void> callback;
                try {
                    URL url = GitHubRequests.searchUser(queryTest, limit, offset);
                    String result = new String(system.httpGet(url, null).t);
                    final ArrayList<GitHubUserShort> users = ResponseParser.parseSearchResults(result);
                    callback = new function<Void>() {
                        @Override
                        public void run(Void... params) { presenter.addUsers(users); }
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
                loading=false;
                present(callback, queryTest);
            }
        });
    }

    private void present(function<Void> result, String forRequest){
        if(query==null||!query.equals(forRequest)) return; //checking that results are up to date
        system.doOnForeground(result); //performing results
    }

    @Override
    public void openUser(GitHubUserShort user) {
        onOpenUser.run(user);
    }

    @Override
    public void addToFavor(GitHubUserShort user) {
        //TODO
    }
}
