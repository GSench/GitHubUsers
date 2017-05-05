package ru.gsench.githubusers.domain.github_repo;

import java.net.MalformedURLException;
import java.net.URL;

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

    public void userRequest(URL url, function callback, function error){
        defaultRequest(url, callback, error);
    }

    public void searchUser(String searchFor, int limit, int offset, function callback, function error){
        try {
            int page = offset/limit+1;
            URL request = new URL("https://api.github.com/search/users?q="+searchFor+"&page="+page+"&per_page="+limit);
            defaultRequest(request, callback, error);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void repositoriesRequest(URL url, function callback, function error){
        defaultRequest(url, callback, error);
    }

    private void defaultRequest(final URL url, final function callback, final function error){
        systemInterface.doOnBackground(new function() {
            @Override
            public void run(String... params) {
                function foreground;
                try {
                    final String result = new String(systemInterface.httpGet(url, null).t);
                    foreground = new function() {
                        @Override
                        public void run(String... params) {callback.run(result);}
                    };
                } catch (Exception e) {
                    e.printStackTrace();
                    foreground=error;
                }
                systemInterface.doOnForeground(foreground);
            }
        });
    }

}
