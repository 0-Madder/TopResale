package com.example.topresale.ViewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topresale.R;
import com.example.topresale.model.Producte;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Producte> producteList;
    Context context;

    public RecyclerViewAdapter(List<Producte> producteList, Context context) {
        this.producteList = producteList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nombreProducto.setText(producteList.get(position).getName());
        Glide.with(this.context).load(producteList.get(position).getFoto()).into(holder.fotoProducto);

    }

    @Override
    public int getItemCount() {
        return producteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoProducto;
        TextView nombreProducto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoProducto = itemView.findViewById(R.id.producto_imageView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto_textView);
        }
    }
}
