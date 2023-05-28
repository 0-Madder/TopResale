package com.example.topresale.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.topresale.R;
import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {


    private TextView bienvenido_textView;
    private String usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        Intent intent = getIntent();
        if(intent.getStringExtra("usuario") != null){
            usuarioActual = intent.getStringExtra("usuario");
        }

        bienvenido_textView = findViewById(R.id.bienvenido_textView);
        String bienvenida = "Bienvenido a tu perfil " + usuarioActual;
        bienvenido_textView.setText(bienvenida);
    }

    public void cerrarSesion(View view) {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finishAffinity();

    }

    public void mostrarAyuda(View view) {
        Intent intent = new Intent(this, AyudaActivity.class);
        startActivity(intent);

    }

    public void mostrarConfiguracion(View view) {
        Intent intent = new Intent(this, ConfiguracionActivity.class);
        startActivity(intent);
    }

    public void mostrarFavoritos(View view) {
        Intent intent = new Intent(this, FavoritosActivity.class);
        startActivity(intent);
    }
}