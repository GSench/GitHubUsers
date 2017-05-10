package ru.gsench.githubusers.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import java.net.URL;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.AndroidInterface;
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;
import ru.gsench.githubusers.presentation.presenter.UserPresenter;
import ru.gsench.githubusers.presentation.view.CoordinatorView;
import ru.gsench.githubusers.presentation.view.view_etc.PermissionManager;
import ru.gsench.githubusers.presentation.viewholder.MainViewHolder;

public class MainActivity extends AppCompatActivity implements CoordinatorView {

    private CoordinatorPresenter presenter;
    private PermissionManager permissionManager;
    private MainViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewHolder = new MainViewHolder((ViewGroup) findViewById(R.id.activity_main));
        permissionManager = new PermissionManager(this);
        permissionManager.requestBasePermissions(this, new function<Void>() {
            @Override
            public void run(Void... params) {
                presenter = new CoordinatorPresenter(new AndroidInterface(MainActivity.this));
                presenter.setView(MainActivity.this);
                presenter.start();
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void openSearchView() {

    }

    @Override
    public void openUserList(UserListPresenter presenter) {

    }

    @Override
    public void openUser(UserPresenter presenter) {

    }

    @Override
    public void openBrowser(URL url) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        permissionManager.onPermissionCallback(requestCode, permissions, grantResults);
    }
}
