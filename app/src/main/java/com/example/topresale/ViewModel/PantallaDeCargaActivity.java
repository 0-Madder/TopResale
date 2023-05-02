package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.WindowManager;

import com.example.topresale.R;
import com.example.topresale.model.ProducteManager;
import com.example.topresale.model.UserManager;


public class PantallaDeCargaActivity extends AppCompatActivity {

    private static final String LOG_TAG = LogInActivity.class.getSimpleName();
    private UserManager userManager;
    private ProducteManager producteManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pantalla_de_carga);
        userManager = UserManager.getInstance();
        producteManager = ProducteManager.getInstance();
        userManager.inicialitzarUsuaris();
        producteManager.inicialitzarProductes();


        new CountDownTimer(5000, 1000) {
            public void onFinish() {
                Log.d(LOG_TAG, "button_clicked");
                Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(intent);
                finish();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }
        }.start();



    }
}