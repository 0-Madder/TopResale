package com.example.topresale.model;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.topresale.R;

import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String listaTipos[];
    int fotos[];
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, String[] listaTipos, int[] fotos) {
        this.context = context;
        this.listaTipos = listaTipos;
        this.fotos = fotos;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return listaTipos.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_aux, null);
        TextView tipo = (TextView) view.findViewById(R.id.textView);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        tipo.setText(listaTipos[i]);
        icon.setImageResource(fotos[i]);
        return view;
    }
}

