package com.example.topresale.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ProducteManager {

    private FirebaseAuth mAuth;

    private FirebaseFirestore mdB;
    private ArrayList<Producte> llistaProducte = new ArrayList<>();

    public ProducteManager(FirebaseAuth mAuth, FirebaseFirestore mdB) {
        this.mAuth = mAuth;
        this.mdB = mdB;
    }

    public ArrayList<Producte> getLlistaProducte() {
        return llistaProducte;
    }

    public void setLlistaProducte(ArrayList<Producte> llistaProducte) {
        this.llistaProducte = llistaProducte;
    }

    public Producte findProdByName(String name){
        for (Producte p : llistaProducte){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }


}
