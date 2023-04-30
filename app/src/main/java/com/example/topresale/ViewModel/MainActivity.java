package com.example.topresale.ViewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.topresale.R;
import com.example.topresale.model.CustomAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listaTiposProducto;
    String tipos[] = {"Spinner", "Cubo de Rubik", "Sillas", "Smartwatch", "Mousepads", "RubberDuck", "Spinner", "Cubo de Rubik", "Sillas", "Smartwatch", "Mousepads", "RubberDuck,"};
    int flags[] = {R.drawable.spinner, R.drawable.rubik, R.drawable.sillas, R.drawable.smartwatch, R.drawable.alfombrilla, R.drawable.pato,R.drawable.spinner, R.drawable.rubik, R.drawable.sillas, R.drawable.smartwatch, R.drawable.alfombrilla, R.drawable.pato};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaTiposProducto = findViewById(R.id.tiposDeProducto_listView);


        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), tipos, flags);

        listaTiposProducto.setAdapter(customAdapter);

        listaTiposProducto.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        System.out.println(position);
                        System.out.println(id);
                    }
                }
        );

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
                break;
            case R.id.ayuda:
                break;
            case R.id.cerrarSesion:
                Intent intent = new Intent(this, LogInActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}