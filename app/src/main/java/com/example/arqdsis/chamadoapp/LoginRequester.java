package com.example.arqdsis.chamadoapp;

import java.io.IOException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginRequester extends AsyncTask<String, String, String> {
    private Context context;
    private RequesterInterface loginInterface;
    private ProgressDialog progress;

    public LoginRequester(Context context, RequesterInterface loginInterface){
        this.context        = context;
        this.loginInterface = loginInterface;
    }

    @Override
    protected String doInBackground(String... params) {
        String login = null;

        try{
            publishProgress("Carregando...");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(params[0]).build();
            Response response = client.newCall(request).execute(); //error aqui
            login = response.body().string();

        }
        catch(IOException e){}

        return(login);
    }

    @Override
    protected void onPostExecute(String params) {
        loginInterface.depoisRequester(params);
    }
}

