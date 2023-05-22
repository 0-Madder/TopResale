package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.topresale.R;
import com.example.topresale.model.PerfilUser;
import com.example.topresale.model.Producte;
import com.example.topresale.model.ProducteEspecific;
import com.example.topresale.model.ProducteManager;
import com.example.topresale.model.User;
import com.example.topresale.model.UserManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private List<ProducteEspecific> listaProductosFavoritos = new ArrayList<ProducteEspecific>();
    private RecyclerView recyclerView;
    private SearchView buscador;
    private RecyclerView2nAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore mdB;
    private FirebaseAuth mAuth;
    private ProducteManager producteManager;

    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mdB = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        producteManager = ProducteManager.getInstance();
        producteManager.inicialitzarModes();
        userManager = UserManager.getInstance();
        llenaLaLista();
        recyclerView = findViewById(R.id.productosFavoritos_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView2nAdapter(listaProductosFavoritos, this);
        recyclerView.setAdapter(mAdapter);
    }



    private void llenaLaLista(){
        String email = mAuth.getCurrentUser().getEmail();
        User u = userManager.findUsuariByCorreu(email);
        List<ProducteEspecific> llistaFavs = new ArrayList<>();
        if(!u.getPerfilUser().getFavoritos().isEmpty()){
            for(String id : u.getPerfilUser().getFavoritos()){
                for(Producte p : producteManager.getLlistaProducte()){
                    for(ProducteEspecific pe: p.getLlistaProdEspe()){
                        if(id.equalsIgnoreCase(pe.getId())){
                            llistaFavs.add(pe);
                        }
                    }
                }

            }
        }
        if(!llistaFavs.isEmpty()){
            listaProductosFavoritos.addAll(llistaFavs);
        }









    }

}