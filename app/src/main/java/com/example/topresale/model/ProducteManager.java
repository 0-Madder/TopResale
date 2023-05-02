package com.example.topresale.model;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProducteManager {

    private FirebaseAuth mAuth;

    private FirebaseFirestore mdB;
    private ArrayList<Producte> llistaProducte = new ArrayList<>();
    private static ProducteManager producteManager;

    private ProducteManager() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mdB = FirebaseFirestore.getInstance();
        llistaProducte = new ArrayList<>();
    }

    public static ProducteManager getInstance() {
        if (producteManager == null) {
            producteManager = new ProducteManager();
        }
        return producteManager;
    }

    public ArrayList<Producte> getLlistaProducte() {
        return llistaProducte;
    }

    public void setLlistaProducte(ArrayList<Producte> llistaProducte) {
        this.llistaProducte = llistaProducte;
    }

    public Producte findProdByName(String name) {
        for (Producte p : llistaProducte) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void inicialitzarValoracions(ProducteEspecific pE){
        CollectionReference valoracioRef = mdB.collection("Valoracio");
        valoracioRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) { //Miro si es diferent a null
                    for (QueryDocumentSnapshot docVal : task.getResult()) {
                        if (pE.getId().equals(docVal.getString("idProdEspe"))) {
                            pE.addValoracio(docVal.getString("tipusValoracio"), docVal.getString("valorValoracio"), docVal.getString("idProdEspe"));
                        }
                    }
                }
                else {
                }
            });
    }
    public void inicialitzarProductesEspecifics(Producte p){
        CollectionReference prodEspeRef = mdB.collection("ProducteEspecific");
        prodEspeRef.whereEqualTo("nameProd", p.getName()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { //Miro si es diferent a null
                for (QueryDocumentSnapshot docProdEspe : task.getResult()) {
                    if (p.getName().equalsIgnoreCase(docProdEspe.getString("nameProd"))) {
                        ProducteEspecific pE = new ProducteEspecific(docProdEspe.getString("id"), docProdEspe.getString("nameProd"), Float.parseFloat(docProdEspe.getString("preuB")),
                                Float.parseFloat(docProdEspe.getString("preuC")), Float.parseFloat(docProdEspe.getString("preuV")), docProdEspe.getString("descripcio"), docProdEspe.getString("foto"),
                                docProdEspe.getString("estadistica"), docProdEspe.getString("link"), Float.parseFloat(docProdEspe.getString("moda")));
                        p.addProductEspecific(pE);
                        inicialitzarValoracions(pE);
                    }
                }
            } else {

            }
        });
    }
    public void inicialitzarProductes() {
        CollectionReference prodRef = mdB.collection("Producte");
        prodRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { //Miro si es diferent a null
                for (QueryDocumentSnapshot docProd : task.getResult()) { //Recorro tots els documents de la coleccio Producte
                    //Paso els valors necessaris del document a parametres per crear un objecte Producte
                    Producte p = new Producte(docProd.getString("name"), docProd.getString("foto"), docProd.getBoolean("tendencia"));
                    llistaProducte.add(p);  //Afegeixo el Producte a la llista de productes
                    inicialitzarProductesEspecifics(p);


                }

            }
            else {
                System.out.println("Hola existo");
            }
        });
    }
    public void inicialitzarModes(){
        for(Producte p: llistaProducte){
            p.calcularMitjanaModa();
        }
    }

}

