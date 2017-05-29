package com.example.arqdsis.chamadoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Victor on 29/05/2017.
 */


public class StringRequester extends AsyncTask<String, String, String> {
    private Context context;
    private StringInterface ti;
    private ProgressDialog progress;

    public StringRequester(Context context, StringInterface ti){
        this.context = context;
        this.ti = ti;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Carregando...");
        progress.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String paises = null;

        try{
            publishProgress("Carregando...");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(params[0]).build();
            Response response = client.newCall(request).execute(); //error aqui
            paises = response.body().string();

            publishProgress("Carregado !");
        }
        catch(IOException e){}

        return(paises);
    }

    @Override
    protected void onProgressUpdate(String... params) {
        progress.setMessage(params[0]);
    }

    @Override
    protected void onPostExecute(String params) {
        ti.depoisDownload(params);
        progress.dismiss();
    }
}