package com.novaapps.botler;

import android.os.AsyncTask;
import android.os.Bundle;
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

import is.arontibo.library.ElasticDownloadView;

/**
 * Created by MLH-User on 10/25/2015.
 */
public class LoadingFragment extends Fragment {

    ElasticDownloadView submitButton;

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

        GetJson getJson = new GetJson();
        getJson.execute("http://botler.cloudapp.net/api/stats/1");
    }


    private class GetJson extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            DefaultHttpClient client = new DefaultHttpClient();

            HttpGet getRequest = new HttpGet(params[0]);

            String s = "Fail";
            StringBuilder sb = new StringBuilder();
            try {

                HttpResponse getResponse = client.execute(getRequest);
                final int statusCode = getResponse.getStatusLine().getStatusCode();

                if (statusCode != HttpStatus.SC_OK) {
                    Log.w(getClass().getSimpleName(),
                            "Error " + statusCode + " for URL " + params[0]);
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

            return sb.toString();

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Bundle bundle = new Bundle();
            bundle.putString("json", s);

            LoadingFragment loadingFragment = new LoadingFragment();
            loadingFragment.setArguments(bundle);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.container, loadingFragment)
                    .commit();
                 }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            submitButton.setProgress(values[0]);

        }
    }
}
