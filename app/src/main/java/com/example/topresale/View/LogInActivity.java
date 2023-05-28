package com.example.topresale.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topresale.R;
import com.example.topresale.model.ProducteManager;
import com.example.topresale.model.User;
import com.example.topresale.model.UserManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LogInActivity extends AppCompatActivity {

    private static final String LOG_TAG = LogInActivity.class.getSimpleName();

    private EditText textoPswd;
    private EditText textoUsername;
    private UserManager userManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        textoPswd = findViewById(R.id.pswdInicioSesion_EditText);
        textoUsername = findViewById(R.id.usuarioIniciSesio_editText);
        userManager = UserManager.getInstance();


    }

    public void iniciarSesion(View view) {

        boolean parametrosCorrectos = true;
        String username = textoUsername.getText().toString();
        String pswd = textoPswd.getText().toString();
        User u = userManager.findUsuariByUsername(username);


        // - Caso en el que algún parámetro este vacío
        if(username.isEmpty()|| pswd.isEmpty()){
            Toast toast = Toast.makeText(this, "Es obligatorio rellenar todos los campos.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }
        // - Caso en que el usuario no exista
        else if(u == null){
            Toast toast = Toast.makeText(this, "No existe el usuario.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }
        else if (!u.getPswd().equals(pswd)){
            Toast toast = Toast.makeText(this, "Contraseña incorrecta.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }

        //En caso de que los parámetros sean correctos se iniciará la sesión
        if(parametrosCorrectos){
            Log.d(LOG_TAG, "button_clicked");
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("usuario", username);
            startActivity(intent);
            userManager.iniciarSessio(u);
            userManager.setActiveUser(u);
            //userManager.setActiveUser(u);
            Toast toast = Toast.makeText(this, "Sesión iniciada", Toast.LENGTH_SHORT);
            toast.show();
            finish();
        }

    }

    //Redirecciona al usuario a la página de registro
    public void registrarse(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}