package com.example.topresale.ViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.topresale.R;
import com.example.topresale.model.Producte;
import com.example.topresale.model.User;
import com.example.topresale.model.UserManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


public class LogInActivity extends AppCompatActivity {

    private static final String LOG_TAG = LogInActivity.class.getSimpleName();

    private EditText textoPswd;
    private EditText textoUsername;

    private String psw_real;

    private FirebaseAuth mAuth;


    private FirebaseFirestore mdB;
    private UserManager userManager;

    private Activity a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        textoPswd = findViewById(R.id.pswdInicioSesion_EditText);
        textoUsername = findViewById(R.id.usuarioIniciSesio_editText);
        mAuth = FirebaseAuth.getInstance();
        mdB = FirebaseFirestore.getInstance();
        CollectionReference prodRef = mdB.collection("User");
        userManager = new UserManager(mAuth,mdB);
        prodRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { //Miro si es diferent a null
                for (QueryDocumentSnapshot document : task.getResult()) { //Recorro tots els documents de la coleccio Producte
                    User u = document.toObject(User.class); //Paso el document a objecte Producte
                    userManager.getLlistaUsuaris().add(u);  //Afegeixo el Producte a la llista de productes
                }
            } else {

            }
        });
    }

    public void iniciarSesion(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        boolean parametrosCorrectos = true;


        String username = textoUsername.getText().toString();
        String pswd = textoPswd.getText().toString();




        if(psw_real.equals(pswd)){
            System.out.println("kaka");
        }
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

        }


        finish();

    }

    //Redirecciona al usuario a la página de registro
    public void registrarse(View view) {
        Log.d(LOG_TAG, "button_clicked");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void setPsw_real(String psw_real) {
        this.psw_real = psw_real;
    }

}