package com.example.topresale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }

    public void iniciarSesion(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, PantallaDeCargaActivity.class);
        startActivity(intent);
    }

    public void registrarse(View view) {

        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    //AAAAAA
}