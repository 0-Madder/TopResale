package com.example.topresale.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Producte implements Serializable {
    private ArrayList<ProducteEspecific> llistaProdEspe;
    private String name;
    private String foto;
    private float mitjanaModa;
    private boolean tendencia;

    public Producte(String name, String foto, boolean tendencia) {
        this.llistaProdEspe = new ArrayList<ProducteEspecific>();
        this.name = name;
        this.foto = foto;
        //calcularMitjanaModa();
        //this.mitjanaModa = getMitjanaModa();
        this.mitjanaModa = 0;
        this.tendencia = tendencia;
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

    public void setMitjanaModa(float mitjanaModa) {
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

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isTendencia() {
        return tendencia;
    }

    public void setTendencia(boolean tendencia) {
        this.tendencia = tendencia;
    }

    public void addProductEspecific(String nameProd, String id, float preuB, float preuC, float preuV, String descripcio, String foto, String estadistica, String link, float moda){
        ProducteEspecific producteEspecific = new ProducteEspecific(nameProd, id, preuB,preuC,preuV,descripcio,foto,estadistica,link,moda);
        llistaProdEspe.add(producteEspecific);
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
        this.setMitjanaModa(mitjana/cont);
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
            int p1trending = p1.isTendencia() ? 1:0;
            int p2trending = p2.isTendencia() ? 1:0;
            return p2trending - p1trending;
        }
    };
}
