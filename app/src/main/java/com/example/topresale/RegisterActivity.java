package com.example.topresale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegisterActivity.class.getSimpleName();
    private EditText textoNombre;
    private EditText textoCorreo;
    private EditText textoUsuario;
    private EditText textoContra;
    private EditText textoRepetirContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        textoNombre = findViewById(R.id.nombreCompleto_editText);
        textoCorreo = findViewById(R.id.correo_editText);
        textoUsuario = findViewById(R.id.usuario_editText);
        textoContra = findViewById(R.id.password_editText);
        textoRepetirContra = findViewById(R.id.repetirPassword_editText);
    }

    public void crearUsuario(View view) {

        if(!textoContra.getText().toString().equals(textoRepetirContra.getText().toString())){
            Toast toast = Toast.makeText(this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT);
            toast.show();
        }

        else{
            finish();
        }

    }

    public void mostrarTerminosYCondiciones(View view) {

        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, TerminosActivity.class);
        startActivity(intent);

    }
}