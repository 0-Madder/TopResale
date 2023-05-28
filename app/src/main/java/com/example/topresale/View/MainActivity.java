package com.example.topresale.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.topresale.R;
import com.example.topresale.model.Producte;
import com.example.topresale.model.ProducteManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener {

    private List<Producte> listaProductos = new ArrayList<Producte>();
    private String username;
    private RecyclerView recyclerView;
    private SearchView buscador;
    private TextView ordenTextView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseFirestore mdB;
    private String modosDeOrdenacion[] = {"A -> Z", "Z -> A", "Tendencias"};
    private int cnt = 0;
    private ProducteManager producteManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        username = intent.getStringExtra("usuario");
        mdB = FirebaseFirestore.getInstance();


        producteManager = ProducteManager.getInstance();
        producteManager.inicialitzarModes();
        ordenTextView = findViewById(R.id.productoEspecifico_textView);
        ordenTextView.setText("Ordenado por ");
        llenaLaLista();
        //llenaDeProductosEspecificos();
        recyclerView = findViewById(R.id.productosFavoritos_recyclerView);
        buscador = findViewById(R.id.busqueda_searchBar);
        buscador.setOnQueryTextListener(this);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerViewAdapter(listaProductos, this);
        recyclerView.setAdapter(mAdapter);




    }
    private void llenaLaLista(){
        listaProductos.addAll(producteManager.getLlistaProducte());
        listaProductos.addAll(producteManager.getLlistaProducte());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cerrar la instancia de FirebaseFirestore
        if (mdB != null) {
            //mdB.terminate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_desplegable, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.perfil:
                Intent intent1 = new Intent(this, PerfilActivity.class);
                intent1.putExtra("usuario", username);
                startActivity(intent1);
                break;
            case R.id.favoritos:
                Intent intent2 = new Intent(this, FavoritosActivity.class);
                startActivity(intent2);
                break;
            case R.id.ayuda:
                Intent intent3 = new Intent(this, AyudaActivity.class);
                startActivity(intent3);
                break;
            case R.id.cerrarSesion:
                Intent intent4 = new Intent(this, LogInActivity.class);
                startActivity(intent4);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public void cambiarOrden(View view) {

        if(cnt + 1 > modosDeOrdenacion.length - 1){
            cnt = 0;
        }
        else{
            cnt +=1;
        }

        ordenTextView.setText("Ordenado por " + modosDeOrdenacion[cnt]);

        switch (cnt){
            case 0:
                Collections.sort(listaProductos, Producte.ProducteAZComparator);
                mAdapter.notifyDataSetChanged();
                break;
            case 1:
                Collections.sort(listaProductos, Producte.ProducteZAComparator);
                mAdapter.notifyDataSetChanged();
                break;
            case 2:
                Collections.sort(listaProductos, Producte.ProducteTrendingComparator);
                mAdapter.notifyDataSetChanged();
                break;

        }



    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        mAdapter.filter(s);
        return false;
    }
}