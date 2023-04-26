package com.example.topresale.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {


    private FirebaseAuth mAuth;

    private FirebaseFirestore mdB;
    private ArrayList<User> llistaUsuaris = new ArrayList<>();

    public UserManager(FirebaseAuth mAuth, FirebaseFirestore mdB) {
        this.mAuth = mAuth;
        this.mdB = mdB;

    }

    //Tiene que coincidir el nombre de usuario con la contraseña
    public boolean correctPswd(String username, String pswd) throws Exception {
        User userLogIn = findUsuariByUsername(username);
        if(userLogIn.getPswd().equals(pswd)){
            return true; //Contrasenya correcta
        }
        return false; //Contrasenya incorrecta
    }

    //Afegeix l'ususari a la base de dades
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

    //Afegeix ususari al AUTH
    public void inscriureUsuari(String textCorreo, String textPswd){
        mAuth.createUserWithEmailAndPassword(textCorreo, textPswd);
    }

    //En cas que el username ja existeixi no permetrem el registre del nou usuari pero permetre iniciar sessió si la contraseña és correcta
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

    //Encontrar un usuario segun su correo
    public User findUsuariByCorreu (String correu) throws Exception{
        for(User u : llistaUsuaris){
            if(u.getCorreo().equalsIgnoreCase(correu)){
                return u;
            }
        }
        throw new Exception("Usuari per correu no trobat");

    }

    //Encontrar un usuario segun su nombre de usuario
    public User findUsuariByUsername (String usuari) throws Exception{
        for(User u : llistaUsuaris){
            if(u.getNomUser().equalsIgnoreCase(usuari)){
                return u;
            }

        }
        throw new Exception("Usuari per nom no trobat");

    }

}
