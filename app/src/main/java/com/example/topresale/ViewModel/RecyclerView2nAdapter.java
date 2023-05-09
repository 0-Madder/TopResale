package com.example.topresale.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topresale.R;
import com.example.topresale.model.ProducteEspecific;

import java.util.List;

public class RecyclerView2nAdapter extends RecyclerView.Adapter<RecyclerView2nAdapter.MyViewHolder> {

    List<ProducteEspecific> producteEspecificList;
    Context context;

    public RecyclerView2nAdapter(List<ProducteEspecific> producteEspecificList, Context context) {
        this.producteEspecificList = producteEspecificList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_espe, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.nombreProducto.setText(producteEspecificList.get(position).getName());
        Glide.with(this.context).load(producteEspecificList.get(position).getFoto()).into(holder.fotoProducto);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(producteEspecificList.get(holder.getAdapterPosition()).getDescripcio());
            }
        });

    }

    @Override
    public int getItemCount() {
        return producteEspecificList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoProducto;
        TextView nombreProducto;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoProducto = itemView.findViewById(R.id.productoEspecifico_imageView);
            nombreProducto = itemView.findViewById(R.id.nombreProductoEspecifico_textView);
            parentLayout = itemView.findViewById(R.id.oneLineProductEspecific);
        }
    }
}