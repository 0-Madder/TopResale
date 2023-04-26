package com.example.topresale.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {


    private FirebaseAuth mAuth;

    private FirebaseFirestore mdB;
    private ArrayList<User> llistaUsuaris;

    public UserManager(FirebaseAuth mAuth, FirebaseFirestore mdB) {
        this.mAuth = FirebaseAuth.getInstance();
        this.mdB = FirebaseFirestore.getInstance();
    }

    public void registrarUsuario(String nomComplet, String correo, String nomUser, String pswd){
        User u = new User(nomComplet,correo,nomUser,pswd);
        llistaUsuaris.add(u);
        Map<String,Object> signedUpUser = new HashMap<>();
        signedUpUser.put("Nom", nomComplet);
        signedUpUser.put("Correu",correo);
        signedUpUser.put("Usuari",nomUser);
        signedUpUser.put("Contrasenya",pswd);
        mdB.collection("Usuaris").document().set(signedUpUser);

    }

    public void inscriureUsuari(String textCorreo, String textPswd){
        mAuth.createUserWithEmailAndPassword(textCorreo, textPswd);
    }

    //En cas que el username ja existeixi no permetrem el registre el nou usuari
    public boolean usernameExistent(String username){
        for(User u: llistaUsuaris){
            if(u.getNomUser().equalsIgnoreCase(username)){
                return true; //En caso que ya exista devuelve TRUE
            }
        }
        return false; //EN caso de que no exista devuelve FALSE
    }

    //En cas que el correu de l'usuari que es registra ja tingui un compte associat no permetrem el seu registre
    public boolean correuExistent(String correu){
        for(User u:llistaUsuaris){
            if(u.getCorreo().equalsIgnoreCase(correu)){
                return true;
            }
        }
        return false;
    }

    /*public User findUsuaricorreu (String correu) throws Exception{
        for(User u : llistaUsuaris){
            if(u.getCorreo().equalsIgnoreCase(correu)){
                return u;
            }

        }
        throw new Exception("Usuari per correu no trobat");

    }
    public User findUsuarinom (String usuari) throws Exception{
        for(User u : llistaUsuaris){
            if(u.getNomUser().equalsIgnoreCase(usuari)){
                return u;
            }

        }
        throw new Exception("Usuari per nom no trobat");

    }*/


}
