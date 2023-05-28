package com.example.topresale.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.topresale.R;
import com.example.topresale.model.ProducteEspecific;
import com.example.topresale.model.ProducteManager;
import com.example.topresale.model.UserManager;
import com.example.topresale.model.Valoracio;

public class DetallesProductoEspecificoActivity extends AppCompatActivity {


    private ImageView fotoProducto;
    private TextView nombreProducto;
    private TextView precioB;
    private TextView precioV;
    private TextView precioC;
    private TextView descripcion;
    private TextView opinions;
    private TextView url;
    private RatingBar estrellesIndicador;
    private TextView estrellesText;
    private EditText myOpinion;
    private RatingBar myEstrelles;
    private UserManager userManager;
    private ProducteManager producteManager;
    private ProducteEspecific objeto;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_producto_especifico);
        Intent intent = getIntent();
        userManager = UserManager.getInstance();
        producteManager = ProducteManager.getInstance();
        objeto = (ProducteEspecific) intent.getSerializableExtra("producto");

        myEstrelles = findViewById(R.id.valoraEstrelles_ratingBar);
        myEstrelles.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                valoracioEstrelles(ratingBar, rating, fromUser);
            }
        });


        estrellesText = findViewById((R.id.estrelles_textView));
        estrellesIndicador = findViewById(R.id.ratingBar);
        fotoProducto = findViewById(R.id.productoEspecifico_imageView);
        nombreProducto = findViewById(R.id.tipoProducto_textView);
        precioB = findViewById(R.id.preuB_textView);
        precioV = findViewById(R.id.preuV_textView);
        precioC = findViewById(R.id.preuC_textView);
        descripcion = findViewById(R.id.descripcionProducto_textView);
        opinions = findViewById(R.id.opinions_textView);
        url = findViewById(R.id.urlCompra_textView);


        Glide.with(this).load(objeto.getFoto()).into(fotoProducto);
        SpannableString content = new SpannableString(objeto.getName().toUpperCase());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        notifyEstrelles();
        nombreProducto.setText(content);
        precioB.setText('\u2022' + " Beneficio estimado por unidad:   " + Float.toString(objeto.getPreuB()) + "€");
        precioV.setText('\u2022' + " Precio de compra por unidad:   " + Float.toString(objeto.getPreuC()) + "€");
        precioC.setText('\u2022' + " Precio de venta aproximado por unidad:   " + Float.toString(objeto.getPreuV()) + "€");
        descripcion.setText(objeto.getDescripcio());
        notifyOpinions();
        /*if(allOpinions.equals(new SpannableStringBuilder())){
            opinions.setText("\n\n - No hay opiniones de este producto - \n\n");
        }else{
            opinions.setText(allOpinions);
        }

         */

        url.setText("Link de compra del producto: " + objeto.getLink());

        url.setClickable(true);
        url.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a ";
        text += "href='";
        text += objeto.getLink();
        text += "'> Link de compra </a>";
        url.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));

    }

    public void valoracioOpinio(View view){
        myOpinion = findViewById(R.id.opinino_editText);
        producteManager.addValoracio("Opinio",myOpinion.getText().toString(), objeto.getId(), userManager.getActiveUser().getNomUser());
        objeto.addValoracio("Opinio",myOpinion.getText().toString(), objeto.getId(), userManager.getActiveUser().getNomUser());
        Toast toast = Toast.makeText(this, "Opinión añadida", Toast.LENGTH_SHORT);
        toast.show();
        myOpinion.setText("");
        notifyOpinions();

    }
    public void notifyOpinions(){
        SpannableStringBuilder allOpinions = new SpannableStringBuilder();
        String op = "";
        for (Valoracio v: objeto.getLlistaValoracio()){
            if (v.getTipusValoracio().equals("Opinio")){
                SpannableStringBuilder nameUser = new SpannableStringBuilder(" " + v.getNameUser());
                nameUser.setSpan(new StyleSpan(Typeface.BOLD), 0, v.getNameUser().length() + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                allOpinions.append(nameUser);
                op = ": " + v.getValorValoracio() + "\n";
                allOpinions.append(op);
            }
        }
        if(allOpinions.equals(new SpannableStringBuilder())){
            opinions.setText("\n\n - No hay opiniones de este producto - \n\n");
        }else{
            System.out.println(allOpinions);
            opinions.setText(allOpinions);
        }
        //a

    }

    public void valoracioEstrelles(RatingBar ratingBar, float rating, boolean fromUser){
        producteManager.addValoracio("Estrelles",Float.toString(rating), objeto.getId(), userManager.getActiveUser().getNomUser());
        objeto.addValoracio("Estrelles",Float.toString(rating), objeto.getId(), userManager.getActiveUser().getNomUser());
        Toast toast = Toast.makeText(this, "Valoración realitzada", Toast.LENGTH_SHORT);
        toast.show();
        notifyEstrelles();
    }

    public void notifyEstrelles(){
        String media = String.format("%.2f", objeto.mitjana());
        estrellesIndicador.setRating(Float.parseFloat(media));
        estrellesText.setText(media);
    }


}