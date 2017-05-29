package com.example.arqdsis.chamadoapp;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FilaRequester extends AsyncTask<String, String, String> {
    private Context context;
    private RequesterInterface chamadoInterface;

    public FilaRequester(Context context, RequesterInterface chamadoInterface){
        this.context = context;
        this.chamadoInterface = chamadoInterface;
    }

    @Override
    protected String doInBackground(String... params) {
        String paises = null;

        try{
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(params[0]).build();
            Response response = client.newCall(request).execute();
            paises = response.body().string();
        }
        catch(IOException e){}

        return(paises);
    }

    @Override
    protected void onPostExecute(String params) {
        chamadoInterface.depoisRequester(params);
    }
}

