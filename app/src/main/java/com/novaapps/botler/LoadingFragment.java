package com.novaapps.botler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Handler;

import is.arontibo.library.ElasticDownloadView;

/**
 * Created by MLH-User on 10/25/2015.
 */
public class LoadingFragment extends Fragment {

    ElasticDownloadView submitButton;
    Handler handler;
    Boolean stop = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        String projectName = b.getString("project_name");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle bundle) {
        super.onCreateView(inflater, null, bundle);
        setHasOptionsMenu(true);

        //Inflate the layout
        return inflater.inflate(R.layout.fragment_load, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        submitButton = (ElasticDownloadView) getActivity().findViewById(R.id.elastic_download_view);

        submitButton.startIntro();
        submitButton.setProgress(100);

        GetJson getJson = new GetJson();
        getJson.execute("http://hu.pythonanywhere.com/api/stats/fake");
    }

    public boolean isConnectedToServer(String url, int timeout) {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(timeout);
            connection.connect();
            return true;
        } catch (Exception e) {
            // Handle your exceptions
            return false;
        }
    }

    public String getJson(String url) {
        String s;

        DefaultHttpClient client = new DefaultHttpClient();
        StringBuilder sb = new StringBuilder();

        HttpGet getRequest = new HttpGet(url);
        try {

            HttpResponse getResponse = client.execute(getRequest);
            final int statusCode = getResponse.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w(getClass().getSimpleName(),
                        "Error " + statusCode + " for URL " + url);
                return null;
            }

            HttpEntity getResponseEntity = getResponse.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(getResponseEntity.getContent()));
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }

        } catch (IOException e) {
            getRequest.abort();
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
        s = sb.toString();

        return s;
    }

    private class GetJson extends AsyncTask<String, Integer, String> {
        String s = "Fail";


        @Override
        protected String doInBackground(String... params) {

            Log.e("DoInBG", "Begin");
            if (isConnectedToServer(params[0], 1000)) {
                Log.e("DoInBG", "connected");

                return getJson(params[0]);


            } else {
                Log.e("DoInBG", "Not connected");

                s = "{\n" +
                        "   \"wins\":3,\n" +
                        "   \"losses\":4,\n" +
                        "   \"ties\":2\n" +
                        "}";
                return s;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Bundle bundle = new Bundle();
            bundle.putString("json", s);

            final StatsActivity statsActivity = new StatsActivity();
            statsActivity.setArguments(bundle);

            new android.os.Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    submitButton.success();
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.container, statsActivity)
                            .commitAllowingStateLoss();
                }

            }, 2000);

        }

    }
}
