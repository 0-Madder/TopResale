package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.topresale.R;
import com.example.topresale.model.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView listaTiposProducto;
    String tipos[] = {"Spinner", "Cubo de Rubik", "Sillas", "Smartwatch", "Transmisor FM", "RubberDuck"};
    int flags[] = {R.drawable.spinner, R.drawable.rubik, R.drawable.sillas, R.drawable.smartwatch, R.drawable.radio, R.drawable.pato};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaTiposProducto = findViewById(R.id.tiposDeProducto_listView);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), tipos, flags);

        listaTiposProducto.setAdapter(customAdapter);

    }



}