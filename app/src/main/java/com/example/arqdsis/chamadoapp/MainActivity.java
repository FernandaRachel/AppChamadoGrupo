package com.example.arqdsis.chamadoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements RequesterInterface {
    private String TAG = MainActivity.class.getSimpleName();
    EditText editLogin,editSenha;
    Button btnLogar;
    CheckBox ckLogado;
    String login;

    public static final String USUARIO = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        editLogin = (EditText)findViewById(R.id.editLogin);
        editSenha = (EditText)findViewById(R.id.editSenha);
        btnLogar = (Button)findViewById(R.id.btnLogar);
    }



    public void logar(View view) {
        LoginRequester requester = new LoginRequester(this, this);
        login = editLogin.getText().toString();
        String senha = editSenha.getText().toString();
        if (login.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Login ou senha vazio", Toast.LENGTH_SHORT).show();
        } else {
            requester.execute("http://107.170.41.209:8080/chamado/rest/v1/logar?login=" + login+"&senha="+senha);
        }
    }

    @Override
    public void depoisRequester(String jsonStr) {

        Log.e(TAG, "Response from url:" + jsonStr +":");
        if (!jsonStr.isEmpty()) {
            try {
                JSONObject reader = new JSONObject(jsonStr);

                String login = reader.getString("login");
                String senha = reader.getString("senha");
                Toast.makeText(this, "Login valido", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(this, MenuActivity.class);
                it.putExtra(USUARIO,login); //
                startActivity(it);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Login ou senha invalido", Toast.LENGTH_SHORT).show();
        }
    }


    protected void onPause() {
        super.onPause();
        finish();
    }

}
