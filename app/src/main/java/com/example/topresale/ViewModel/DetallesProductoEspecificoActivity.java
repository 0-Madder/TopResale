package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.topresale.R;
import com.example.topresale.model.Producte;
import com.example.topresale.model.ProducteEspecific;

public class DetallesProductoEspecificoActivity extends AppCompatActivity {


    private ImageView fotoProducto;
    private TextView nombreProducto;
    private TextView precioB;
    private TextView precioV;
    private TextView precioC;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto_especifico);
        Intent intent = getIntent();
        ProducteEspecific objeto = (ProducteEspecific) intent.getSerializableExtra("producto");

        fotoProducto = findViewById(R.id.productoEspecifico_imageView);
        nombreProducto = findViewById(R.id.tipoProducto_textView);
        precioB = findViewById(R.id.preuB_textView);
        precioV = findViewById(R.id.preuV_textView);
        precioC = findViewById(R.id.preuC_textView);
        descripcion = findViewById(R.id.descripcionProducto_textView);

        Glide.with(this).load(objeto.getFoto()).into(fotoProducto);
        SpannableString content = new SpannableString(objeto.getName().toUpperCase());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        nombreProducto.setText(content);
        precioB.setText('\u2022' + " Beneficio estimado por unidad:   " + Float.toString(objeto.getPreuB()) + "€");
        precioV.setText('\u2022' + " Precio de compra por unidad:   " + Float.toString(objeto.getPreuC()) + "€");
        precioC.setText('\u2022' + " Precio de venta aproximado por unidad:   " + Float.toString(objeto.getPreuV()) + "€");
        descripcion.setText(objeto.getDescripcio());



    }
}