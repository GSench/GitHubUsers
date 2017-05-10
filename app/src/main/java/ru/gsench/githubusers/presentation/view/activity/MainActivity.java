package ru.gsench.githubusers.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.arlib.floatingsearchview.FloatingSearchView;

import java.net.URL;

import ru.gsench.githubusers.R;
import ru.gsench.githubusers.domain.utils.function;
import ru.gsench.githubusers.presentation.AndroidInterface;
import ru.gsench.githubusers.presentation.presenter.CoordinatorPresenter;
import ru.gsench.githubusers.presentation.presenter.UserListPresenter;
import ru.gsench.githubusers.presentation.presenter.UserPresenter;
import ru.gsench.githubusers.presentation.view.CoordinatorView;
import ru.gsench.githubusers.presentation.view.view_etc.AnimationManager;
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
        View.OnClickListener onStartBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) { presenter.onStartButton(); }
        };
        viewHolder.helloContent.setOnClickListener(onStartBtn);
        viewHolder.searchImage.setOnClickListener(onStartBtn);
        viewHolder.searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                //get suggestions based on newQuery
                //pass them on to the search view
                //viewHolder.searchView.swapSuggestions(newQuery);
            }
        });
    }

    @Override
    public void openSearchView() {
        AnimationManager.openSearchView(viewHolder);
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
