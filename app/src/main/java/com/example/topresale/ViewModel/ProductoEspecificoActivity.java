package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.example.topresale.R;

public class ProductoEspecificoActivity extends AppCompatActivity {

    private TextView producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_especifico);
        Intent intent = getIntent();

        producto = findViewById(R.id.productoEspecifico_textView);

        producto.setText("Producto " + intent.getStringExtra("nombre"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ordenacion, menu);

        return true;
    }
}