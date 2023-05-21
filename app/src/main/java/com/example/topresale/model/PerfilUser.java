package com.example.topresale.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PerfilUser{

    private List<String> favoritos;
    private FirebaseFirestore mdB;

    private static PerfilUser perfilUser;

    public static PerfilUser getInstance() {
        if (perfilUser == null) {
            perfilUser = new PerfilUser();
        }
        return perfilUser;
    }

    public PerfilUser() {
        this.favoritos = new ArrayList<String>();
        this.mdB = FirebaseFirestore.getInstance();
    }

    public List<String> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<String> favoritos) {
        this.favoritos = favoritos;
    }

    public void listFavorite(){
        for(String pe : favoritos){

        }
    }
    public void addToFavorite(String producteEspecific){
        favoritos.add(producteEspecific);
    }
    public void removeFromFavorite(String producteEspecific){
        favoritos.remove(producteEspecific);
    }
    /*public void inicialitzarFavorite(){
        CollectionReference prodRef = mdB.collection("User");
        prodRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { //Miro si es diferent a null
                for (QueryDocumentSnapshot docProd : task.getResult()) { //Recorro tots els documents de la coleccio User
                    //Si la lista no esta vacia, la recorremos y añadimos cada producto especifico al usuario.
                    List<String> favs = (List<String>) docProd.get("favs");
                    if(favs.size() > 0){
                        for(String prodId : favs){
                            favoritos.add(prodId);
                        }
                    }





                }

            }
            else {
                System.out.println("Hola existo");
            }
        });
    }*/



}
