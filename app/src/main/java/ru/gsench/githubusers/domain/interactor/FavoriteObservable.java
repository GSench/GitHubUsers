package ru.gsench.githubusers.domain.interactor;

import java.io.IOException;
import java.util.ArrayList;

import ru.gsench.githubusers.domain.github_repo.ResponseParser;
import ru.gsench.githubusers.domain.utils.Pair;

/**
 * Created by grish on 12.05.2017.
 */

public class FavoriteObservable extends UserListObservable {

    private FavoritesManagement favorites;

    public FavoriteObservable(FavoritesManagement favorites) {
        super(FavoritesManagement.FAVORITES);
        this.favorites=favorites;
    }

    @Override
    public Pair<ArrayList<GitHubUserFavor>, Integer> obtain(int limit, int offset) throws IOException, ResponseParser.ParseException {
        return new Pair<>(favorites.getFavorites(), favorites.getFavorites().size());
    }
}
