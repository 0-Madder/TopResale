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
    private ProducteManager producteManager;


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

    public void conversor(){
        for(String id : favoritos){
            ProducteEspecific pEspe = producteManager.findByIdProductesEspecifics(id);
            if(pEspe != null){
                producteEspecifics.add(pEspe);
            }
        }
    }


}
