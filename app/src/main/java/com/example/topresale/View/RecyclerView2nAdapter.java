package com.example.topresale.View;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.topresale.R;
import com.example.topresale.model.ProducteEspecific;
import com.example.topresale.model.User;
import com.example.topresale.model.UserManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecyclerView2nAdapter extends RecyclerView.Adapter<RecyclerView2nAdapter.MyViewHolder> {

    List<ProducteEspecific> producteEspecificList;
    List<ProducteEspecific> originalProducteEspecificList;
    private boolean tocado = false;

    Context context;

    private UserManager userManager;

    public RecyclerView2nAdapter(List<ProducteEspecific> producteEspecificList, Context context) {
        this.producteEspecificList = producteEspecificList;
        this.context = context;
        userManager = UserManager.getInstance();
        this.originalProducteEspecificList = new ArrayList<>();
        originalProducteEspecificList.addAll(producteEspecificList);
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
        holder.idProducto = producteEspecificList.get(position).getId();


        holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.billete));
        holder.botonFavoritos.setChecked(false);


        User u = userManager.findUsuariByCorreu(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        if(u.getPerfilUser().getFavoritos() != null){

            if(u.getPerfilUser().getFavoritos().contains(holder.idProducto)){
                holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.billete2));
                holder.botonFavoritos.setChecked(true);
            }

        }

        holder.botonFavoritos.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                tocado = true;
            }

        });

        holder.botonFavoritos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.billete2));
                    userManager.afegirFavs(producteEspecificList.get(holder.getAdapterPosition()).getId());

                }
                else if(!isChecked && tocado){
                    holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.billete));
                    userManager.removeFavs(producteEspecificList.get(holder.getAdapterPosition()).getId());
                    tocado = false;
                }
            }
        });


        holder.botonComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(producteEspecificList.get(holder.getAdapterPosition()).getLink()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetallesProductoEspecificoActivity.class);
                intent.putExtra("producto", producteEspecificList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return producteEspecificList.size();
    }


    public void filter(String busqueda){

        if(busqueda.length() == 0){
            producteEspecificList.clear();
            producteEspecificList.addAll(originalProducteEspecificList);
        }

        else{
            producteEspecificList.clear();
            List<ProducteEspecific> collect = originalProducteEspecificList.stream().filter(i -> i.getDescripcio().toLowerCase().contains(busqueda)).collect(Collectors.toList());
            producteEspecificList.addAll(collect);
        }

        notifyDataSetChanged();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoProducto;
        TextView nombreProducto;
        ConstraintLayout parentLayout;
        Button botonComprar;
        ToggleButton botonFavoritos;
        String idProducto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoProducto = itemView.findViewById(R.id.producto_imageView);
            nombreProducto = itemView.findViewById(R.id.nombreProducto_textView);
            parentLayout = itemView.findViewById(R.id.oneLineProductEspecific);
            botonComprar = itemView.findViewById(R.id.comprar_button);
            botonFavoritos = itemView.findViewById(R.id.favoritosButton);
        }
    }
}
