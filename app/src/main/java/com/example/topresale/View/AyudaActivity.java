package com.example.topresale.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.Toast;

import com.example.topresale.R;
import com.example.topresale.model.UserManager;

public class AyudaActivity extends AppCompatActivity {

    private EditText pregunta;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        userManager = UserManager.getInstance();
        pregunta = findViewById(R.id.duda_editText);
        pregunta.setScroller(new Scroller(this));
        pregunta.setMaxLines(5);
        pregunta.setVerticalScrollBarEnabled(true);
        pregunta.setMovementMethod(new ScrollingMovementMethod());
    }

    public void enviarDudas(View view) {

        String question = pregunta.getText().toString();

        //Caso en el que el edit text esté vacio
        if(question.isEmpty()){
            Toast toast = Toast.makeText(this, "Rellene el campo para mandar su pregunta.", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            //Añadir pregunta a la base de datos + el usuario que la ha formulado
            userManager.addQuestion(question);
            Toast toast = Toast.makeText(this, "Enviado. Contactaremos con usted en breves.", Toast.LENGTH_SHORT);
            toast.show();
            pregunta.setText("");
            finish();
        }


    }
}