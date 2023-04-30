package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.topresale.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }

    public void cerrarSesion(View view) {

        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finishAffinity();

    }

    public void mostrarAyuda(View view) {



    }

    public void mostrarConfiguracion(View view) {
    }

    public void mostrarFavoritos(View view) {
    }
}