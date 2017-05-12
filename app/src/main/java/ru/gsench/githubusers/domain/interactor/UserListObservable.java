package ru.gsench.githubusers.domain.interactor;

import java.io.IOException;
import java.util.ArrayList;

import ru.gsench.githubusers.domain.github_repo.ResponseParser;
import ru.gsench.githubusers.domain.utils.Pair;

/**
 * Created by grish on 11.05.2017.
 */

public abstract class UserListObservable {

    public String query;

    public UserListObservable(String query){
        this.query=query;
    }

    public abstract Pair<ArrayList<GitHubUserFavor>, Integer> obtain(int limit, int offset) throws IOException, ResponseParser.ParseException;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserListObservable)) return false;
        UserListObservable compareWith = (UserListObservable) obj;
        return this.query == null && compareWith.query == null || this.query != null && this.query.equals(compareWith.query);
    }
}
