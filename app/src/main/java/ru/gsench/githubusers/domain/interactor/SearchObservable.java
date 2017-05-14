package ru.gsench.githubusers.domain.interactor;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubRequests;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;
import ru.gsench.githubusers.domain.github_repo.ResponseParser;
import ru.gsench.githubusers.domain.utils.Pair;

/**
 * Created by grish on 11.05.2017.
 */

public class SearchObservable extends UserListObservable {

    private SystemInterface system;
    private FavoritesManagement favorites;

    public SearchObservable(String query, SystemInterface system, FavoritesManagement favorites) {
        super(query);
        this.system=system;
        this.favorites=favorites;
    }

    @Override
    public Pair<ArrayList<UserModel>, Integer> obtain(int limit, int offset) throws IOException, ResponseParser.ParseException {
        URL url = GitHubRequests.searchUser(query, limit, offset);
        String result = new String(system.httpGet(url, null).t);
        Pair<ArrayList<GitHubUserShort>, Integer> users = ResponseParser.parseSearchResults(result);
        return new Pair<>(
                favorites.sortFavorites(users.t),
                users.u>1000 ? 1000 : users.u);
    }
}
