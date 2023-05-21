package com.example.topresale.model;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.topresale.R;
import com.example.topresale.ViewModel.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.EventListener.*;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

public class UserManager{
    private FirebaseAuth mAuth;
    private FirebaseFirestore mdB;
    private ArrayList<User> llistaUsuaris;
    private User activeUser;

    private static UserManager userManager;

    private UserManager() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mdB = FirebaseFirestore.getInstance();
        llistaUsuaris = new ArrayList<>();
    }

    public static UserManager getInstance(){
        if(userManager==null){
            userManager = new UserManager();
        }
        return userManager;
    }

    public ArrayList<User> getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public void setLlistaUsuaris(ArrayList<User> llistaUsuaris) {
        this.llistaUsuaris = llistaUsuaris;
    }

    public User getActiveUser() {
        return activeUser;
    }
    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    //Tiene que coincidir el nombre de usuario con la contraseña
    public boolean correctPswd(String username, String pswd){
        User userLogIn = findUsuariByUsername(username);
        if(userLogIn != null){
            if(userLogIn.getPswd().equals(pswd)){
                return true; //Contrasenya correcta
            }
        }
        return false;
    }

    //Afegeix l'ususari a la base de dades
    public void registrarUsuario(String nomUser, String correo, String nomComplet, String pswd) throws Exception{
        Map<String,Object> signedUpUser = new HashMap<>();
        List<String> favs = new ArrayList<>();
        String id = mAuth.getCurrentUser().getUid();
        signedUpUser.put("nomComplet", nomComplet);
        signedUpUser.put("correo",correo);
        signedUpUser.put("nomUser",nomUser);
        signedUpUser.put("pswd",pswd);
        signedUpUser.put("id",id);
        signedUpUser.put("favs",favs);


        mdB.collection("User").document(nomUser).set(signedUpUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Manejar éxito
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejar error
                        try {
                            throw new Exception("Resgistro NO completado");
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });

        User u = new User(nomComplet, correo, nomUser, pswd, id);
        llistaUsuaris.add(u);
    }

    //Afegeix ususari al AUTH
    public void inscriureUsuari(String textCorreo, String textPswd){
        mAuth.createUserWithEmailAndPassword(textCorreo, textPswd);
    }

    //En cas que el username ja existeixi no permetrem el registre del nou usuari pero permetre iniciar sessió si la contraseña és correcta
    public boolean usernameExistent(String username){
        User u = findUsuariByUsername(username);
        if(u != null){
            return true;
        }
        return false;
    }

    //En cas que el correu de l'usuari que es registra ja tingui un compte associat no permetrem el seu registre
    public boolean correuExistent(String correu){
        User u = findUsuariByCorreu(correu);
        if(u != null){
            return true;
        }
        return false;
    }

    //Encontrar un usuario segun su nombre de usuario
    public User findUsuariByUsername(String username){
        for(User u: llistaUsuaris){
            if(u.getNomUser().equals(username)){
                return u;
            }
        }
        return null;
    }

    //Encontrar un usuario segun su correo electrónico
    public User findUsuariByCorreu(String correo){
        for(User u: llistaUsuaris){
            if(u.getCorreo().equals(correo)){
                return u;
            }
        }
        return null;
    }

    public void inicialitzarUsuaris(){
        CollectionReference userRef = mdB.collection("User");
        userRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { //Miro si es diferent a null
                for (QueryDocumentSnapshot document : task.getResult()) { //Recorro tots els documents de la coleccio Producte
                    User u = document.toObject(User.class); //Paso el document a objecte usuaris

                    List<String> favs = (List<String>) document.get("favs");
                    if(favs != null){
                        PerfilUser pu = new PerfilUser();
                        pu.setFavoritos(favs);
                        u.setPerfilUser(pu);

                    }

                    userManager.getLlistaUsuaris().add(u);  //Afegeixo el User a la llista de usuaris
                }
            } else {

            }
        });
    }

    public void iniciarSessio(User u){
        mAuth.signInWithEmailAndPassword(u.getCorreo(),u.getPswd());
        mAuth.getCurrentUser();

    }

    //AFegir a la base de dades les preguntes realitzades per l'usuari en el layout d'ajuda
    public void addQuestion(String ayuda){
        Map<String,Object> userHelp = new HashMap<>();
        String email = mAuth.getCurrentUser().getEmail();

        userHelp.put("Correu", email);
        userHelp.put("Pregunta", ayuda);

        mdB.collection("Ayuda").document(email).set(userHelp).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Manejar éxito
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Manejar error
                        try {
                            throw new Exception("Pregunta NO enviada");
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
    }



}
