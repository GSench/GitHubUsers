package ru.gsench.githubusers.presentation.view;

import java.util.ArrayList;

import ru.gsench.githubusers.domain.github_repo.GitHubUserShort;

/**
 * Created by grish on 08.05.2017.
 */

public class FakeUserListView implements UserListView {

    public ArrayList<GitHubUserShort> getUsers() {
        return users;
    }

    private ArrayList<GitHubUserShort> users;

    @Override
    public void init() {
        System.out.println("Init view");
        users = new ArrayList<>();
    }

    @Override
    public void addUsers(ArrayList<GitHubUserShort> usersToAdd) {
        users.addAll(usersToAdd);
        System.out.println("Added users:");
        for(int i=users.size()-usersToAdd.size(); i<users.size(); i++){
            System.out.println((i+1)+". "+users.get(i).getLogin());
        }
    }

    @Override
    public void clearList() {
        users.clear();
        System.out.println("List cleared");
    }

    @Override
    public void showLoadingError() {
        System.out.println("Loading Error");
    }

    @Override
    public void showParseError() {
        System.out.println("Parse Error");
    }

    @Override
    public void showUnexpectedError() {
        System.out.println("Unexpected Error");
    }

    @Override
    public void closeView() {

    }
}
