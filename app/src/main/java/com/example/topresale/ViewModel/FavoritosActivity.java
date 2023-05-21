package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.topresale.R;
import com.example.topresale.model.ProducteEspecific;
import com.example.topresale.model.ProducteManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private List<ProducteEspecific> listaProductosFavoritos = new ArrayList<ProducteEspecific>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore mdB;
    private FirebaseAuth mAuth;
    private ProducteManager producteManager;

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
        llenaLaLista();
        recyclerView = findViewById(R.id.productosFavoritos_recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerView2nAdapter(listaProductosFavoritos, this);
        recyclerView.setAdapter(mAdapter);
    }



    private void llenaLaLista(){




    }
}