package com.example.arqdsis.chamadoapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arqdsis.chamadoapp.model.Fila;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Victor on 29/05/2017.
 */

public class FilaDescAdapter extends ArrayAdapter<Fila> {

    NovoChamadoActivity iact = new NovoChamadoActivity();

    private Context context;
    private ArrayList<Fila> lista;
    private Spinner filaSpinner;

    public FilaDescAdapter(Context context, ArrayList<Fila> lista ) {
        super(context,0,lista);
        this.context = context;
        this.lista = lista;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.desc_fila,null,true);
        }

        Fila filaPosicao = lista.get(position);

        TextView fila_descricao = (TextView) convertView.findViewById(R.id.item_fila_desc);

        fila_descricao.setText(filaPosicao.getDescricao());

        return convertView;
    }

}

