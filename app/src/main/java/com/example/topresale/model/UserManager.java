package com.example.topresale.model;

import com.google.firebase.auth.FirebaseAuth;

public class UserManager {

    private FirebaseAuth mAuth;
    private User user;

    public void registrarUsuario(String nombre, String nombreUsuario){

    }

    public void inscriureUsuari(String textCorreo, String textPswd){
        mAuth.createUserWithEmailAndPassword(textCorreo, textPswd);
    }
    public boolean usuariExistent (User user){

    }

}
