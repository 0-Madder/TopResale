package com.example.topresale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void crearUsuario(View view) {



        finish();

    }

    public void mostrarTerminosYCondiciones(View view) {

        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, TerminosActivity.class);
        startActivity(intent);

    }
}