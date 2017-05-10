package ru.gsench.githubusers.presentation.view;

/**
 * Created by grish on 01.04.2017.
 */

public interface UserListView {

    public void init();
    public void notifyUsersAdded(int offset, int count);
    public void clearList();
    public void showLoadingError();
    public void showParseError();
    public void showUnexpectedError();
    public void closeView();
}
