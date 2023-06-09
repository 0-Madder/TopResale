package com.example.topresale.model;

import com.example.topresale.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Producte implements Serializable {
    private ArrayList<ProducteEspecific> llistaProdEspe;
    private String name;
    private String foto;
    private int fotoTendencia;
    private int mitjanaModa;
    private boolean tendencia;


    public Producte(String name, String foto, boolean tendencia) {
        this.llistaProdEspe = new ArrayList<ProducteEspecific>();
        this.name = name;
        this.foto = foto;
        //calcularMitjanaModa();
        //this.mitjanaModa = getMitjanaModa();
        this.mitjanaModa = 0;
        this.tendencia = tendencia;
        setFotoTendencia();
    }


    public ArrayList<ProducteEspecific> getLlistaProdEspe() {
        return llistaProdEspe;
    }

    public void setLlistaProdEspe(ArrayList<ProducteEspecific> llistaProdEspe) {
        this.llistaProdEspe = llistaProdEspe;
    }

    public float getMitjanaModa() {
        return mitjanaModa;
    }

    public void setMitjanaModa(int mitjanaModa) {
        this.mitjanaModa = mitjanaModa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoto() {
        return foto;
    }

    public int getFotoTendencia() {
        return fotoTendencia;
    }

    public void setFotoTendencia(){

        if(this.tendencia){
            this.fotoTendencia = R.drawable.captura;
        }
        else{
            this.fotoTendencia = R.drawable.captu1ra;
        }
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isTendencia() {
        return tendencia;
    }

    public void setTendencia(boolean tendencia) {
        this.tendencia = tendencia;
    }

    public void addProductEspecific(ProducteEspecific pE){
        llistaProdEspe.add(pE);
    }

    public void calcularMitjanaModa(){
        float mitjana = 0;
        float cont = 0;
        for(ProducteEspecific pe : llistaProdEspe){
            mitjana += pe.getModa();
            cont++;
        }

        int media = Math.round(mitjana/cont);
        this.setMitjanaModa(media);
    }

    public static Comparator<Producte> ProducteAZComparator = new Comparator<Producte>() {
        @Override
        public int compare(Producte p1, Producte p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };

    public static Comparator<Producte> ProducteZAComparator = new Comparator<Producte>() {
        @Override
        public int compare(Producte p1, Producte p2) {
            return p2.getName().compareTo(p1.getName());
        }
    };

    public static Comparator<Producte> ProducteTrendingComparator = new Comparator<Producte>() {
        @Override
        public int compare(Producte p1, Producte p2) {
            return (int) (p2.getMitjanaModa() - p1.getMitjanaModa());
        }
    };



}
