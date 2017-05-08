package ru.gsench.githubusers.domain;

import java.io.IOException;
import java.net.URL;

import ru.gsench.githubusers.domain.utils.HttpParams;
import ru.gsench.githubusers.domain.utils.Pair;
import ru.gsench.githubusers.domain.utils.function;

/**
 * Created by grish on 01.05.2017.
 */

public interface SystemInterface {

    public void doOnBackground(function<Void> background);
    public void doOnForeground(function<Void> function);
    public Pair<byte[], HttpParams> httpGet(URL url, HttpParams params) throws IOException;

}
