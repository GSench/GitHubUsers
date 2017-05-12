package ru.gsench.githubusers.presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.utils.HttpParams;
import ru.gsench.githubusers.domain.utils.Pair;
import ru.gsench.githubusers.domain.utils.function;

/**
 * Created by grish on 08.05.2017.
 */

public class FakeSystem implements SystemInterface {

    private Queue<String> requests = new ArrayDeque<>();

    public void addRequest(String req){
        requests.add(req);
    }

    @Override
    public void doOnBackground(final function<Void> background) {
        new Thread(new Runnable() {
            @Override
            public void run() { background.run(); }
        }).start();
    }

    @Override
    public void doOnForeground(final function<Void> function) {
        new Thread(new Runnable() {
            @Override
            public void run() { function.run(); }
        }).start();
    }

    @Override
    public Pair<byte[], HttpParams> httpGet(URL url, HttpParams params) throws IOException {
        if(requests==null) return null;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {}
        return new Pair<>(requests.poll().getBytes(), null);
    }

    @Override
    public String[] getSavedStringArray(String title, String[] def) {
        return new String[0];
    }

    @Override
    public void saveStringArray(String title, String[] array) {

    }

    @Override
    public void removeSaved(String str) {

    }

}
