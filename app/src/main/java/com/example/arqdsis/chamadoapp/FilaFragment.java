package com.example.arqdsis.chamadoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.arqdsis.chamadoapp.model.Fila;
import com.example.arqdsis.chamadoapp.model.TipoUsuario;
import com.example.arqdsis.chamadoapp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilaFragment extends Fragment implements RequesterInterface {
    private String TAG = FilaFragment.class.getSimpleName();
    private ArrayList<Fila> lista = null;
    View v;

    public FilaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fila, container, false);
        FilaRequester tarefa = new FilaRequester(getContext() ,this);
        tarefa.execute("http://107.170.41.209:8080/chamado/rest/v1/filas");

        return v;
    }

    @Override
    public void depoisRequester(String filas) {
        lista = new ArrayList<Fila>();
        Log.e(TAG, "Response from url:" + filas +":");

        try {
            JSONArray root = new JSONArray(filas);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++) {
                item = (JSONObject) root.get(i);

                Long idFila = item.getLong("id");
                String descricao = item.getString("descricao");
                Usuario gerente = null;
                String nomeSolucionador = item.getString("nomeSolucionador");
                String JSONObject = item.getString("gerente");

                if(JSONObject != "null" && !JSONObject.isEmpty())
                {
                    Log.e(TAG, "Response from url:" + JSONObject +":");
                    JSONObject reader = new JSONObject(JSONObject);

                    Long idGerente = reader.getLong("id");
                    String nome = reader.getString("nome");
                    String celular = reader.getString("celular");
                    String email = reader.getString("email");
                    TipoUsuario tipoUsuario = TipoUsuario.valueOf(reader.getString("tipoUsuario"));

                    gerente = new Usuario(idGerente, nome, celular, email, tipoUsuario);

                }

                Fila fila = new Fila(idFila,descricao,gerente,nomeSolucionador);
                lista.add(fila);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (lista != null) {
            FilaListaAdapter listaAdapterFila = new FilaListaAdapter(getContext(),lista);

            ListView listView = (ListView) v.findViewById(R.id.listarFilas);
            listView.setAdapter(listaAdapterFila);
        }
    }
}
