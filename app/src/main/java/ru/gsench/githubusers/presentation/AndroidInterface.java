package ru.gsench.githubusers.presentation;

import android.app.Activity;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.gsench.githubusers.domain.SystemInterface;
import ru.gsench.githubusers.domain.utils.HttpParams;
import ru.gsench.githubusers.domain.utils.Pair;
import ru.gsench.githubusers.domain.utils.function;

/**
 * Created by grish on 01.05.2017.
 */

public class AndroidInterface implements SystemInterface {

    private Activity activity;

    public AndroidInterface(Activity activity){
        this.activity=activity;
    }

    @Override
    public void doOnBackground(final function<Void> background) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                background.run();
            }
        }).start();

    }

    @Override
    public void doOnForeground(final function<Void> function) {
        if(activity!=null)
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    function.run();
                }
            });
    }

    @Override
    public Pair<byte[], HttpParams> httpGet(URL url, HttpParams params) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        if(params!=null){
            for(Pair<String, String> header: params.getHeaders()){
                urlConnection.setRequestProperty(header.t, header.u);
            }
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            int responseCode = urlConnection.getResponseCode();
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            int available = in.available();
            if(available==0)
                return new Pair<>(readByByte(in), null);
            else
                return new Pair<>(IOUtils.readFully(in, in.available()), null);
        } finally {
            urlConnection.disconnect();
        }
    }

    private byte[] readByByte(InputStream in) throws IOException {
        ArrayList<Byte> byteChain = new ArrayList<>();
        while (true){
            try {
                byteChain.add(IOUtils.readFully(in, 1)[0]);
            } catch (EOFException e){
                break;
            }
        }
        byte[] ret = new byte[byteChain.size()];
        for(int i=0; i<byteChain.size(); i++) ret[i]=byteChain.get(i);
        return ret;
    }
}
