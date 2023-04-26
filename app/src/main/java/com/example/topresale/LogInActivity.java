package com.example.topresale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

public class LogInActivity extends AppCompatActivity {

    private static final String LOG_TAG = LogInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

    }

    public void iniciarSesion(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

        //Caso en que el usuario no exista (necesitamos base de datos)

        //Caso en que la contraseña no sea correcta (necesitamos la base de datos)
    }

    //Redirecciona al usuario a la página de registro
    public void registrarse(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}