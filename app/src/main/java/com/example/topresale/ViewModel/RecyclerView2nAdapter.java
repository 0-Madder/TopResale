package com.example.topresale.ViewModel;

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
import com.example.topresale.model.UserManager;

import java.util.List;

public class RecyclerView2nAdapter extends RecyclerView.Adapter<RecyclerView2nAdapter.MyViewHolder> {

    List<ProducteEspecific> producteEspecificList;
    Context context;

    private UserManager userManager;

    public RecyclerView2nAdapter(List<ProducteEspecific> producteEspecificList, Context context) {
        this.producteEspecificList = producteEspecificList;
        this.context = context;
        userManager = UserManager.getInstance();
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


        holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.billete));
        holder.botonFavoritos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context,R.drawable.billete2));
                    userManager.afegirFavs(producteEspecificList.get(holder.getAdapterPosition()).getId());

                }
                else{
                    holder.botonFavoritos.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.billete));
                    userManager.removeFavs(producteEspecificList.get(holder.getAdapterPosition()).getId());
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

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView fotoProducto;
        TextView nombreProducto;
        ConstraintLayout parentLayout;
        Button botonComprar;
        ToggleButton botonFavoritos;

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
