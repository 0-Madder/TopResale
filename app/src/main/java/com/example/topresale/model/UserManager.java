package com.example.topresale.model;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class UserManager {


    private FirebaseAuth mAuth;
    private ArrayList<User> llistaUsuaris;

    public void registrarUsuario(String nomComplet, String correo, String nomUser, String pswd){
        User user1 = new User(nomComplet,correo,nomUser,pswd);

    }

    public void inscriureUsuari(String textCorreo, String textPswd){
        mAuth.createUserWithEmailAndPassword(textCorreo, textPswd);
    }

    public User findUsuaricorreu (String correu) throws Exception{
        for(User u : llistaUsuaris){
            if(u.getCorreo().equalsIgnoreCase(correu)){
                return u;
            }

        }
        throw new Exception("Usuari per nom no trobat");

    }
    public User findUsuarinom (String usuari) throws Exception{
        for(User u : llistaUsuaris){
            if(u.getNomUser().equalsIgnoreCase(usuari)){
                return u;
            }

        }
        throw new Exception("Usuari per correu no trobat");

    }


}
