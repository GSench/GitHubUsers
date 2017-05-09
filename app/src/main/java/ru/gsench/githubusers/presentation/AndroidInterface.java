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
import java.util.Iterator;
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
            //Opening input stream
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            //getting response parameters
            int responseCode = urlConnection.getResponseCode();
            Iterator<Map.Entry<String, List<String>>> headers = urlConnection.getHeaderFields().entrySet().iterator();

            //proceeding response parameters
            HttpParams response = new HttpParams();
            ArrayList<Pair<String, String>> headersParam = new ArrayList<>();
            while (headers.hasNext()){
                Map.Entry<String, List<String>> entry = headers.next();
                headersParam.add(new Pair<>(entry.getKey(), entry.getValue().get(0)));
            }
            response.setHeaders(headersParam);
            response.setResultCode(responseCode);

            //reading input stream
            int available = in.available();
            if(available==0)
                return new Pair<>(readByByte(in), response);
            else
                return new Pair<>(IOUtils.readFully(in, in.available()), response);
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
