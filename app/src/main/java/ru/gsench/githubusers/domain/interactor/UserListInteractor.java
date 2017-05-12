package ru.gsench.githubusers.domain.interactor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.ResponseParser;
import ru.gsench.githubusers.domain.usecase.UserListUseCase;
import ru.gsench.githubusers.domain.utils.Pair;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;

/**
 * Created by grish on 08.05.2017.
 */

public class UserListInteractor implements UserListUseCase {

    private SystemInterface system;
    private UserListPresenter presenter;
    private function<UserModel> onOpenUser;
    private function<UserModel> onFavoriteChanged;
    private UserListObservable observable;
    private boolean loading = false;

    public UserListInteractor(SystemInterface system, function<UserModel> onOpenUser, function<UserModel> onFavoriteChanged){
        this.system=system;
        this.onOpenUser=onOpenUser;
        this.onFavoriteChanged=onFavoriteChanged;
        loading=false;
    }

    //initializing search observable
    public void setObservable(UserListObservable observable){
        this.observable = observable;
        subscribe();
    }

    //subscribe presenter to interactor for updating if search observable is initialized
    @Override
    public void subscribe(UserListPresenter presenter) {
        this.presenter=presenter;
        subscribe();
    }

    private void subscribe(){
        if(presenter==null|| observable ==null) return; //checking search observable & subscription to be initialized
        loading=false; //dropping previous observable if it's exist
        presenter.clearList(); //clearing presenter if it's filled
        presenter.updateList(); //then notifying presenter that observable is initialized
    }

    @Override
    public void updateList(final int limit, final int offset) {
        updateList(limit, offset, false);
    }

    private void updateList(final int limit, final int offset, final boolean isRetry) {
        if(observable ==null||presenter==null||loading) return;
        loading=true; //protect for multiply queries
        final UserListObservable queryTest = observable;
        system.doOnBackground(new function<Void>() { //execution observable on new thread
            @Override
            public void run(Void... params) {
                function<Void> callback;
                try {
                    final Pair<ArrayList<UserModel>, Integer> users = observable.obtain(limit, offset);
                    callback = new function<Void>() {
                        @Override
                        public void run(Void... params) { presenter.addUsers(users.t, users.u); }
                    };
                } catch (IOException e){
                    e.printStackTrace();
                    callback = new function<Void>() {
                        @Override
                        public void run(Void... params) {
                            if(!isRetry) presenter.showLoadingError();
                            system.doOnBackground(new function<Void>() {
                                @Override
                                public void run(Void... params) {
                                    try {
                                        TimeUnit.SECONDS.sleep(1);
                                    } catch (InterruptedException e1) {}
                                    system.doOnForeground(new function<Void>() {
                                        @Override
                                        public void run(Void... params) { updateList(limit, offset, true); }
                                    });
                                }
                            });
                        }
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

    private void present(function<Void> result, UserListObservable forRequest){
        if(observable ==null||!observable.equals(forRequest)) return; //checking that results are up to date
        system.doOnForeground(result); //performing results
    }

    @Override
    public void openUser(UserModel user) {
        onOpenUser.run(user);
    }

    @Override
    public void pushFavorite(UserModel user) {
        onFavoriteChanged.run(user);
    }
}
