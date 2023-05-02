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
import android.widget.TextView;

import com.example.topresale.R;
import com.example.topresale.model.Producte;
import com.example.topresale.model.ProducteEspecific;
import com.example.topresale.model.ProducteManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Producte> listaProductos = new ArrayList<Producte>();
    static final String CURRENTUSER = "Current user is";
    String username;
    private RecyclerView recyclerView;
    private TextView ordenTextView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ListView listaTiposProducto;
    private FirebaseFirestore mdB;
    private FirebaseAuth mAuth;
    private String modosDeOrdenacion[] = {"A -> Z", "Z -> A", "Tendencias"};
    private int cnt = 0;
    private ProducteManager producteManager;

    private void llenaLaLista(){
        Producte spinner = new Producte("Spinner", "https://upload.wikimedia.org/wikipedia/commons/f/f3/Fidget_spinner_red%2C_cropped.jpg", true);
        Producte duck = new Producte("Pato de goma", "https://upload.wikimedia.org/wikipedia/commons/1/14/Rubber_Duck_%288374802487%29.jpg", false);
        Producte rubik = new Producte("Cubo de rubik", "https://upload.wikimedia.org/wikipedia/commons/6/61/Rubiks_cube_solved.jpg", false);
        Producte taza = new Producte("Taza de cafe", "https://upload.wikimedia.org/wikipedia/commons/4/45/A_small_cup_of_coffee.JPG", false);
        Producte airpods = new Producte("Airpods", "https://upload.wikimedia.org/wikipedia/commons/d/d2/AirPods_3rd_generation.jpg", true);

        listaProductos.addAll(Arrays.asList( new Producte[] {spinner, duck, rubik, taza, airpods}));
        listaProductos.addAll(Arrays.asList( new Producte[] {spinner, duck, rubik, taza}));

    }

    private void llenaDeProductosEspecificos(){
        for(Producte producto : listaProductos){
            producto.addProductEspecific("Spinner barato", "Spinner barato", (float) 1.02, (float) 1.03, (float) 1.04, "Spinner bakano", "https://api.time.com/wp-content/uploads/2017/05/fidget-spinner4.jpg",
                    "12", "url", (float) 20.2);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.CURRENTUSER);



        ordenTextView = findViewById(R.id.productoEspecifico_textView);
        ordenTextView.setText("Ordenado por ");

        llenaLaLista();
        llenaDeProductosEspecificos();
        mdb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        producteManager = ProducteManager.getInstance();
        producteManager.inicialitzarProductes();

        recyclerView = findViewById(R.id.productos_recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(listaProductos, this);
        recyclerView.setAdapter(mAdapter);

        producteManager = ProducteManager.getInstance();
        CollectionReference prodRef = mdb.collection("Producte");
        prodRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { //Miro si es diferent a null
                for (QueryDocumentSnapshot docProd : task.getResult()) { //Recorro tots els documents de la coleccio Producte
                    //Paso els valors necessaris del document a parametres per crear un objecte Producte
                    Producte p = new Producte(docProd.getString("name"), docProd.getString("foto"), docProd.getBoolean("tendencia"));
                    producteManager.getLlistaProducte().add(p);  //Afegeixo el Producte a la llista de productes
                    //producteManager.inicialitzarProductesEspecifics(p);
                    //p.calcularMitjanaModa();
                }

            }
            else {
                System.out.println("Hola existo");
            }
        });
        //producteManager.inicialitzarProductes();
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
                intent1.putExtra(CURRENTUSER, username);
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
}