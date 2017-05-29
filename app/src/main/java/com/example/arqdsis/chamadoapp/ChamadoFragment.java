package com.example.arqdsis.chamadoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.arqdsis.chamadoapp.model.Chamado;
import com.example.arqdsis.chamadoapp.model.TipoUsuario;
import com.example.arqdsis.chamadoapp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChamadoFragment extends Fragment implements RequesterInterface {
    private String TAG = FilaFragment.class.getSimpleName();
    private ArrayList<Chamado> lista = null;
    private Spinner status;
    View v;
    String usuario;

    public ChamadoFragment(String login) {
        // Required empty public constructor
        this.usuario = login;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_chamado, container, false);

        status = (Spinner) v.findViewById(R.id.status);

        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.Status,android.R.layout.simple_spinner_item);
        status.setAdapter(arrayAdapter);
        ChamadoRequester tarefa = new ChamadoRequester(getContext() ,this);
        tarefa.execute("http://107.170.41.209:8080/chamado/rest/v1/chamados?login="+usuario+"&status=TODOS");

        return v;
    }

    @Override
    public void depoisRequester(String chamados) {
        lista = new ArrayList<Chamado>();
        Log.e(TAG, "Response from url:" + chamados +":");

        try {
            JSONArray root = new JSONArray(chamados);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++) {
                item = (JSONObject) root.get(i);

                Long idChamado = item.getLong("id");
                Usuario solicitante = null;

                String JSONObject = item.getString("solicitante");

                if(JSONObject != "null" && !JSONObject.isEmpty()) {

                    Log.e(TAG, "Response from url:" + JSONObject +":");
                    JSONObject reader = new JSONObject(JSONObject);

                    Long idGerente = reader.getLong("id");
                    String nome = reader.getString("nome");
                    String celular = reader.getString("celular");
                    String email = reader.getString("email");
                    TipoUsuario tipoUsuario = TipoUsuario.valueOf(reader.getString("tipoUsuario"));

                    solicitante = new Usuario(idGerente, nome, celular, email, tipoUsuario);
                }

                Chamado chamado = new Chamado(idChamado, solicitante);
                lista.add(chamado);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (lista != null) {
            ChamadoListaAdapter listaAdapterChamado = new ChamadoListaAdapter(getContext(),lista);

            ListView listView = (ListView) v.findViewById(R.id.listarChamados);
            listView.setAdapter(listaAdapterChamado);
        }
    }
}
