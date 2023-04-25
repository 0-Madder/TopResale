package com.example.topresale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class PantallaDeCargaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pantalla_de_carga);



    }
}