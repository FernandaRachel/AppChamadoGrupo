package com.example.arqdsis.chamadoapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.arqdsis.chamadoapp.model.Fila;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NovoChamadoActivity extends AppCompatActivity implements StringInterface{

    private Handler handler = new Handler();
    private ArrayList<Fila> lista_fila = null;
    private Spinner spin_fila;
    private FilaListaAdapter nAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);

        StringRequester tarefa = new StringRequester(this, this);
        tarefa.execute("http://107.170.41.209:8080/chamado/rest/v1/filas");

    }


    public void depoisDownload(String fila) {
        this.lista_fila = new ArrayList<Fila>();

        try {
            JSONArray root = new JSONArray(fila);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++) {
                item = (JSONObject) root.get(i);
                String descricao = item.getString("descricao");

                Fila fil = new Fila(descricao);
                this.lista_fila.add(fil);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (lista_fila != null) {
            FilaDescAdapter listaAdapterFila = new FilaDescAdapter(this,this.lista_fila);

            //Spinner spin = (Spinner)findViewById(R.id.SpinnerFilas);

            Spinner spin = (Spinner)findViewById(R.id.SpinnerFilas);

            spin.setAdapter(listaAdapterFila);
        }
    }



}
