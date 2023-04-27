package com.example.topresale.model;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserManager {


    private FirebaseAuth mAuth;

    private FirebaseFirestore mdB;
    private ArrayList<User> llistaUsuaris = new ArrayList<>();

    private User activeUser;

    public UserManager(FirebaseAuth mAuth, FirebaseFirestore mdB) {
        this.mAuth = mAuth;
        this.mdB = mdB;

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
    public boolean correctPswd(String username, String pswd) throws Exception {
        User userLogIn = findUsuariByUsername(username);
        if(userLogIn.getPswd().equals(pswd)){
            return true; //Contrasenya correcta
        }
        return false; //Contrasenya incorrecta
    }

    //Afegeix l'ususari a la base de dades
    public void registrarUsuario(String nomUser, String correo, String nomComplet, String pswd){
        Map<String,Object> signedUpUser = new HashMap<>();
        signedUpUser.put("nomComplet", nomComplet);
        signedUpUser.put("correo",correo);
        signedUpUser.put("nomUser",nomUser);
        signedUpUser.put("pswd",pswd);
        signedUpUser.put("id",mAuth.getCurrentUser().getUid());
        //mdB.collection("User").document(mAuth.getCurrentUser().getUid()).set(signedUpUser);
        mdB.collection("User").document().set(signedUpUser);

        User u = new User(nomComplet,correo,nomUser,pswd,mAuth.getCurrentUser().getUid());
        llistaUsuaris.add(u);
        for(User user : llistaUsuaris){
            System.out.println(user.getNomUser() + " " + user.getPswd());
        }

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

    public void iniciarSessio(String nameUser, String pswd){
        // Obtener la colección de usuarios
        CollectionReference usersRef = FirebaseFirestore.getInstance().collection("User");
        // Obtener todos los documentos de la colección
        usersRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Iterar sobre todos los documentos de la colección
                            for (QueryDocumentSnapshot d : task.getResult()) {
                                // Obtener los datos del documento
                                User u = d.toObject(User.class);
                                // Hacer algo con los datos del usuario
                                if (u.getNomUser().equals(nameUser) && u.getPswd().equals(pswd)){
                                    activeUser = u;
                                }
                            }
                        } else {
                            // Manejar el error
                        }
                    }
                });
        if (activeUser != null)
            mAuth.signInWithEmailAndPassword(activeUser.getCorreo(), activeUser.getPswd());
        else
            System.out.println("No em iniciat sessio");
    }

    public User findUserById(String id){
        DocumentReference docRef = mdB.collection("User").document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                // Convertir los datos del documento en un objeto "User"
                User user = documentSnapshot.toObject(User.class);
                activeUser = user;

                // Hacer algo con el objeto "User"
                if (user != null) {
                    // El objeto "User" ha sido creado correctamente y puedes acceder a sus atributos con los getters
                    // Hacer algo con los atributos del objeto "User"...
                } else {
                    // El objeto "User" no se ha creado correctamente y debes manejar el caso de error
                    // ...
                }
            }
        });
        return getActiveUser();
    }

}
