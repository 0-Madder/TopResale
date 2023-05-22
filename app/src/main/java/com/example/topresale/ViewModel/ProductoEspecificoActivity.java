package com.example.topresale.ViewModel;

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
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.topresale.R;
import com.example.topresale.model.Producte;
import com.example.topresale.model.ProducteEspecific;

import java.util.Collections;
import java.util.List;

public class ProductoEspecificoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, SearchView.OnQueryTextListener{

    private TextView producto;
    private RecyclerView recyclerView;
    private SearchView buscador;
    private RecyclerView2nAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private  List<ProducteEspecific> listaProductosEspecificos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_especifico);
        Intent intent = getIntent();
        Producte objeto = (Producte) intent.getSerializableExtra("producto");
        producto = findViewById(R.id.productoEspecifico_textView);
        producto.setText("Producto: " + objeto.getName());
        listaProductosEspecificos = objeto.getLlistaProdEspe();
        System.out.println(listaProductosEspecificos);

        buscador = findViewById(R.id.busquedaEspecifica_searchBar);
        buscador.setOnQueryTextListener(this);
        recyclerView = findViewById(R.id.productosFavoritos_recyclerView);
        recyclerView = findViewById(R.id.productosFavoritos_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerView2nAdapter(listaProductosEspecificos, this);
        recyclerView.setAdapter(mAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ordenacion, menu);

        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.topTrending:
                Collections.sort(listaProductosEspecificos, ProducteEspecific.ProducteTrendingComparator);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.topBeneficion:
                Collections.sort(listaProductosEspecificos, ProducteEspecific.ProducteBeneficioComparator);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.masBaratos:
                Collections.sort(listaProductosEspecificos, ProducteEspecific.ProductePrecioComparator);
                mAdapter.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
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