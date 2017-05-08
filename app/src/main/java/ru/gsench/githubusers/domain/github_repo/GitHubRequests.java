package ru.gsench.githubusers.domain.github_repo;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by grish on 01.05.2017.
 */

public class GitHubRequests {

    public static URL searchUser(String searchFor, int limit, int offset){
        try {
            return new URL("https://api.github.com/search/users?q="+searchFor+"&page="+(offset/limit+1)+"&per_page="+limit);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
