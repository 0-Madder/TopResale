package com.example.topresale.ViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import android.widget.ListView;

import com.example.topresale.R;
import com.example.topresale.model.Producte;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Producte> listaProductos = new ArrayList<Producte>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ListView listaTiposProducto;
    private FirebaseFirestore mdb;
    private FirebaseAuth mAuth;

    private void llenaLaLista(){
        Producte spinner = new Producte("Spinner", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Fidget_spinner_red%2C_cropped.jpg", false);
        Producte duck = new Producte("Pato de goma", "https://upload.wikimedia.org/wikipedia/commons/1/14/Rubber_Duck_%288374802487%29.jpg", false);
        Producte rubik = new Producte("Cubo de rubik", "https://upload.wikimedia.org/wikipedia/commons/6/61/Rubiks_cube_solved.jpg", false);
        Producte taza = new Producte("Taza de cafe", "https://upload.wikimedia.org/wikipedia/commons/4/45/A_small_cup_of_coffee.JPG", false);
        Producte airpods = new Producte("Airpods", "https://upload.wikimedia.org/wikipedia/commons/d/d2/AirPods_3rd_generation.jpg", false);

        listaProductos.addAll(Arrays.asList( new Producte[] {spinner, duck, rubik, taza, airpods}));
        listaProductos.addAll(Arrays.asList( new Producte[] {spinner, duck, rubik, taza, airpods}));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        llenaLaLista();
        mdb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.productos_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(listaProductos, this);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Cerrar la instancia de FirebaseFirestore
        if (mdb != null) {
            mdb.terminate();
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
                startActivity(intent1);
                break;
            case R.id.favoritos:
                //Intent de productes especifics i cridem al mètode que mostra els preferits
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


}