package ru.gsench.githubusers.domain.interactor;

import java.util.ArrayList;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;

/**
 * Created by grish on 11.05.2017.
 */

public class FavoritesManagement {

    public static final String FAVORITES = "favorites";
    private static final String divider = "/";

    private ArrayList<UserModel> favorites;
    private SystemInterface system;

    public FavoritesManagement(SystemInterface system){
        this.system=system;
    }

    private void fillFavorites(){
        if(favorites!=null) return;
        String[] fav = system.getSavedStringArray(FAVORITES, new String[0]);
        favorites = new ArrayList<>(fav.length);
        for(String favor: fav){
            String[] div = favor.split(divider);
            favorites.add(new UserModel(
                    Integer.parseInt(div[0]),
                    div[1],
                    true
            ));
        }
    }

    public ArrayList<UserModel> getFavorites(){
        fillFavorites();
        return favorites;
    }

    public boolean isFavorite(GitHubUserShort user){
        fillFavorites();
        for(GitHubUserShort favor: favorites)
            if(favor.getId()==user.getId()) return true;
        return false;
    }

    public ArrayList<UserModel> sortFavorites(ArrayList<GitHubUserShort> users){
        ArrayList<UserModel> ret = new ArrayList<>(users.size());
        for(GitHubUserShort user: users) ret.add(new UserModel(user, isFavorite(user)));
        return ret;
    }

    public void addToFavorites(GitHubUserShort user){
        fillFavorites();
        favorites.add(0, new UserModel(user, true));
        String[] fav = new String[favorites.size()];
        for(int i=0; i<favorites.size(); i++) fav[i]=favorites.get(i).getId()+divider+favorites.get(i).getLogin();
        system.saveStringArray(FAVORITES, fav);
    }

    public void removeFromFavorites(GitHubUserShort user){
        fillFavorites();
        ArrayList<String> fav = new ArrayList<>();
        for(int i=0; i<favorites.size(); i++){
            if(favorites.get(i).getId()!=user.getId())
                fav.add(favorites.get(i).getId()+divider+favorites.get(i).getLogin());
        }
        system.saveStringArray(FAVORITES, fav.toArray(new String[0]));
        for(int i=0; i<favorites.size(); i++) if(favorites.get(i).getId()==user.getId()) favorites.remove(i--);
    }

}
