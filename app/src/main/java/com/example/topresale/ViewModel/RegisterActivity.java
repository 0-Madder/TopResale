package com.example.topresale.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topresale.R;
import com.example.topresale.model.UserManager;

public class RegisterActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegisterActivity.class.getSimpleName();
    private EditText textoNombre;
    private EditText textoCorreo;
    private EditText textoUsuario;
    private EditText textoContra;
    private EditText textoRepetirContra;
    private CheckBox checkBoxAceptarCondiciones;
    private UserManager userManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        textoNombre = findViewById(R.id.nombreCompleto_editText);
        textoCorreo = findViewById(R.id.correo_editText);
        textoUsuario = findViewById(R.id.usuarioIniciSesio_editText);
        textoContra = findViewById(R.id.password_editText);
        textoRepetirContra = findViewById(R.id.repetirPassword_editText);
        checkBoxAceptarCondiciones = findViewById(R.id.aceptarTerminos_checkBox);
    }

    public void crearUsuario(View view) {
        //Boolean para comprobar si los parámetros son correctos
        boolean parametrosCorrectos = true;
        String correu = textoCorreo.getText().toString();
        String username = textoUsuario.getText().toString();

        //En caso de que Contraseña != Repite la contraseña
        if(!textoContra.getText().toString().equals(textoRepetirContra.getText().toString())){
            Toast toast = Toast.makeText(this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT);
            toast.show();
            textoContra.setText("");
            textoRepetirContra.setText("");
            parametrosCorrectos = false;
        }

        //En caso de que alguno de los EditText esten vacios
        if(textoNombre.getText().toString().equals("") || textoCorreo.getText().toString().equals("")
                || textoUsuario.getText().toString().equals("") || textoContra.getText().toString().equals("")
                || textoRepetirContra.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Es obligatorio rellenar todos los campos.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }

        //En caso de que el nombre de usuario ya exista
        if(userManager.usernameExistent(username)){
            Toast toast = Toast.makeText(this, "Este nombre de usuario ya esta en uso.", Toast.LENGTH_SHORT);
            toast.show();;
            parametrosCorrectos = false;
        }

        //En caso de que ya exista otra cuenta asociada a este correo
        if(userManager.correuExistent(correu)){
            Toast toast = Toast.makeText(this, "Este correo ya tiene una cuenta asociada.", Toast.LENGTH_SHORT);
            toast.show();;
            parametrosCorrectos = false;
        }


            //En caso de que no se hayan aceptado los terminos y condiciones
        if(!checkBoxAceptarCondiciones.isChecked()){
            Toast toast = Toast.makeText(this, "Acepte los términos y condiciones.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }


        //Terminar actividad en caso de que todos los parámetros sean correcto. Se añadirá el usuario en la base de datos i en AUTH
        if(parametrosCorrectos){
            userManager.inscriureUsuari(textoCorreo.getText().toString(), textoContra.getText().toString());
            userManager.registrarUsuario(textoUsuario.getText().toString(),textoCorreo.getText().toString(),textoNombre.getText().toString(),textoContra.getText().toString());
            finish();
        }

    }

    public void mostrarTerminosYCondiciones(View view) {

        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, TerminosActivity.class);
        startActivity(intent);

    }
}