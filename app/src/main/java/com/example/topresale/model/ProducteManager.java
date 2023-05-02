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


public class ProducteManager{

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

    public void inicialitzarProductes(){
        CollectionReference prodRef = mdB.collection("Producte");
        prodRef.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) { //Miro si es diferent a null
                    for (QueryDocumentSnapshot document : task.getResult()) { //Recorro tots els documents de la coleccio Producte
                        Producte p = document.toObject(Producte.class); //Paso el document a objecte Producte
                        llistaProducte.add(p);  //Afegeixo el Producte a la llista de productes

                        List<Map<String, Object>> prodEspecificData = (List<Map<String, Object>>) document.get("ProducteEspecific"); //Faig una llista amb els documents de productes especifics
                        if(prodEspecificData != null) {
                            for (Map<String, Object> pE : prodEspecificData) { //Recorro els documents de prod especifics
                                if (p.getName().equalsIgnoreCase((String) pE.get("nameProd"))) { //Miro si el nom del Producte i el del producteEspecific es el mateix
                                    //Si ho és l'afageixo a la llista de productes especifics del Producte en questio on tambe es creara el prodEspe
                                    p.addProductEspecific((String) pE.get("nameProd"), (String) pE.get("id"), (float) pE.get("preuB"), (float) pE.get("preuC"), (float) pE.get("preuV"), (String) pE.get("descripcio"),
                                            (String) pE.get("foto"), (String) pE.get("estadistica"), (String) pE.get("link"), (float) pE.get("moda"));


                                }
                            }
                            //Un cop ja hagi creat i afegit tots els prodEsp del producte recorro tots els productesEsp
                            for (ProducteEspecific prodE : p.getLlistaProdEspe()) {
                                List<Map<String, Object>> valoracioData = (List<Map<String, Object>>) mdB.collection("Valoracio"); //Faig una llista amb els documents de valoracio
                                if(valoracioData != null) {
                                    for (Map<String, Object> v : valoracioData) { //Recorro tots els documents de la coleccio valoracio
                                        if (prodE.getId().equalsIgnoreCase((String) v.get("idProdEspe"))) { //Comprovo que el id del prodEsp coincideix amb el idProdEspe de la valoracio
                                            //Si ho és, afegeixo la valoracio a l'array del producte especific
                                            prodE.addValoracio((String) v.get("tipusValoracio"), (String) v.get("valorValoracio"), (String) v.get("idProdEspe"));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // Aquí ya tienes la lista de productos convertidos a objetos Product
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
        });
    }


}
