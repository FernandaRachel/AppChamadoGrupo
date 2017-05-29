package com.example.arqdsis.chamadoapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arqdsis.chamadoapp.model.Chamado;

import java.util.ArrayList;

public class ChamadoListaAdapter extends ArrayAdapter<Chamado> {

    private Context context;
    private ArrayList<Chamado> lista;

    public ChamadoListaAdapter(Context context, ArrayList<Chamado> lista ) {
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.chamado,null,true);
        }

        Chamado chamadoPosicao = lista.get(position);

        TextView chamado_solicitante = (TextView) convertView.findViewById(R.id.chamado_solicitante);

        chamado_solicitante.setText(chamadoPosicao.getSolicitante().getNome());

        return convertView;
    }

}
