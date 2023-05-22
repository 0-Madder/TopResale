package com.example.topresale.model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class PerfilUser{

    private List<String> favoritos;

    private List<ProducteEspecific> producteEspecifics;
    private FirebaseFirestore mdB;

    private static PerfilUser perfilUser;

    private ProducteManager producteManager;

    public static PerfilUser getInstance() {
        if (perfilUser == null) {
            perfilUser = new PerfilUser();
        }
        return perfilUser;
    }

    public PerfilUser() {
        this.favoritos = new ArrayList<String>();
        this.mdB = FirebaseFirestore.getInstance();
        this.producteManager = ProducteManager.getInstance();
        this.producteEspecifics = new ArrayList<>();
        conversor();
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

    public List<ProducteEspecific> getProducteEspecifics() {
        return producteEspecifics;
    }

    public void setProducteEspecifics(List<ProducteEspecific> producteEspecifics) {
        this.producteEspecifics = producteEspecifics;
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
    public void conversor(){
        List<ProducteEspecific> llista_favs = new ArrayList<>();
        for(String id : favoritos){
            if(producteManager.llistaProductesEspecifics(id) != null){
                llista_favs.add(producteManager.llistaProductesEspecifics(id));


            }
        }
        if(!llista_favs.isEmpty()){
            setProducteEspecifics(llista_favs);
        }



    }




}
