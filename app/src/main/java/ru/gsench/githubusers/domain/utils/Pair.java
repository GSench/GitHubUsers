package ru.gsench.githubusers.domain.utils;

/**
 * Created by grish on 18.04.2017.
 */

public class Pair<T, U> {

    public final T t;
    public final U u;

    public Pair(T t, U u) {
        this.t=t;
        this.u=u;
    }
}