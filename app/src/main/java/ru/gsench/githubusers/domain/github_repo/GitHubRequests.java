package ru.gsench.githubusers.domain.github_repo;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.utils.function;

/**
 * Created by grish on 01.05.2017.
 */

public class GitHubRequests {

    private SystemInterface systemInterface;

    public GitHubRequests(SystemInterface systemInterface){
        this.systemInterface=systemInterface;
    }

    public void userRequest(String username, function callback){

    }

    public void searchUser(String searchFor, int limit, int offset, function callback){

    }

}
