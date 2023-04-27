package com.example.topresale.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.example.topresale.viewmodel.MainActivity;
import com.example.topresale.viewmodel.RegisterActivity;
import com.example.topresale.R;
import com.example.topresale.model.UserManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LogInActivity extends AppCompatActivity {

    private static final String LOG_TAG = LogInActivity.class.getSimpleName();

    private EditText textoPswd;
    private EditText textoUsername;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private FirebaseFirestore mdB = FirebaseFirestore.getInstance();
    private UserManager userManager = new UserManager(mAuth,mdB);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        textoPswd = findViewById(R.id.pswdInicioSesion_EditText);
        textoUsername = findViewById(R.id.usuarioIniciSesio_editText);
    }

    public void iniciarSesion(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        boolean parametrosCorrectos = true;
        String username = textoUsername.getText().toString();
        String pswd = textoPswd.getText().toString();

        // - Caso en el que algún parámetro este vacío
        if(textoUsername.getText().toString().equals("") || textoPswd.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Es obligatorio rellenar todos los campos.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }

        // - Caso en que el usuario no exista
        if(!userManager.usernameExistent(username)){
            Toast toast = Toast.makeText(this, "No existe el usuario.", Toast.LENGTH_SHORT);
            toast.show();
            parametrosCorrectos = false;
        }
        else{
            // - Caso en que la contraseña no sea correcta (necesitamos la base de datos)
            try {
                if(!userManager.correctPswd(username, pswd)){
                    Toast toast = Toast.makeText(this, "Contraseña o usuario incorrectos", Toast.LENGTH_SHORT);
                    toast.show();
                    parametrosCorrectos = false;
                }
            } catch (Exception e) {
                throw new RuntimeException(e); //No saltarà nunca dado que si el usuario no existe esta parte del código no se ejecutará
            }
        }


        //En caso de que los parámetros sean correctos se iniciará la sesión
        if(parametrosCorrectos){
            userManager.iniciarSessio(textoUsername.getText().toString(), textoPswd.getText().toString());
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