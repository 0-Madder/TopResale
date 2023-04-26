package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.topresale.R;

public class MainActivity extends AppCompatActivity {

    private ListView listaTiposProducto;
    String tipos[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};
    int flags[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaTiposProducto = findViewById(R.id.tiposDeProducto_listView);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), countryList, flags);

    }
}