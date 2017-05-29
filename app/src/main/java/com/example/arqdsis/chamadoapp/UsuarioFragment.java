package com.example.arqdsis.chamadoapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.arqdsis.chamadoapp.model.TipoUsuario;
import com.example.arqdsis.chamadoapp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsuarioFragment extends Fragment implements RequesterInterface {
    private String TAG = FilaFragment.class.getSimpleName();
    private ArrayList<Usuario> lista = null;
    View v;

    public UsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_usuario, container, false);
        UsuarioRequester tarefa = new UsuarioRequester(getContext() ,this);
        tarefa.execute("http://107.170.41.209:8080/chamado/rest/v1/usuarios");

        return v;
    }

    @Override
    public void depoisRequester(String usuarios) {
        lista = new ArrayList<Usuario>();
        Log.e(TAG, "Response from url:" + usuarios +":");

        try {
            JSONArray root = new JSONArray(usuarios);
            JSONObject item = null;
            for (int i = 0; i < root.length(); i++) {
                item = (JSONObject) root.get(i);

                Long idUsuario = item.getLong("id");
                String nome = item.getString("nome");
                String celular = item.getString("celular");
                String email = item.getString("email");
                TipoUsuario tipoUsuario = TipoUsuario.valueOf(item.getString("tipoUsuario"));
                Usuario usuario = new Usuario(idUsuario,nome,celular,email,tipoUsuario);
                lista.add(usuario);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (lista != null) {
            UsuarioListaAdapter listaAdapterUsuario = new UsuarioListaAdapter(getContext(),lista);

            ListView listView = (ListView) v.findViewById(R.id.listarUsuarios);
            listView.setAdapter(listaAdapterUsuario);
        }
    }
}
