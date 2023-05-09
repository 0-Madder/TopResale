package com.example.topresale.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class ProducteEspecific implements Serializable {

    private String id;
    private String nameProducte;
    private float preuB;
    private float preuC;
    private float preuV;
    private String descripcio;
    private String foto;
    private String estadistica;
    private ArrayList<Valoracio> llistaValoracio = new ArrayList<>();
    private String link;
    private float moda;





    public ProducteEspecific(String id, String nameProducte, float preuB, float preuC, float preuV, String descripcio, String foto, String estadistica, String link, float moda) {

        this.nameProducte = nameProducte;
        this.id = id;
        this.preuB = preuB;
        this.preuC = preuC;
        this.preuV = preuV;
        this.descripcio = descripcio;
        this.foto = foto;
        this.estadistica = estadistica;
        this.link = link;
        this.moda = moda;
    }

    public String getName() {
        return nameProducte;
    }
    public void setNameProdcucte(String nameProdcucte) {
        this.nameProducte = nameProdcucte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPreuB() {
        return preuB;
    }

    public void setPreuB(float preuB) {
        this.preuB = preuB;
    }

    public float getPreuC() {
        return preuC;
    }

    public void setPreuC(float preuC) {
        this.preuC = preuC;
    }

    public float getPreuV() {
        return preuV;
    }

    public void setPreuV(float preuV) {
        this.preuV = preuV;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(String estadistica) {
        this.estadistica = estadistica;
    }

    public ArrayList<Valoracio> getLlistaValoracio() {
        return llistaValoracio;
    }

    public void setLlistaValoracio(ArrayList<Valoracio> llistaValoracio) {
        this.llistaValoracio = llistaValoracio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public float getModa() {
        return moda;
    }

    public void setModa(float moda) {
        this.moda = moda;
    }
    public void addValoracio(String tipus, String valor, String idProdEsp){
        Valoracio valoracio = new Valoracio(tipus,valor, idProdEsp);
        llistaValoracio.add(valoracio);
    }
    public float mitjana(){
        float mitjan = 0.0f;
        float contador = 0.0f;
        for(Valoracio v: llistaValoracio){
            if(v.getTipusValoracio().equalsIgnoreCase("Estrella")){
                mitjan += v.cambiador(v.getValorValoracio());
                contador++;
            }
        }
        if (contador == 0.0f){
            return 0.0f;
        }
        return mitjan / contador;
    }


    public static Comparator<ProducteEspecific> ProducteTrendingComparator = new Comparator<ProducteEspecific>() {
        @Override
        public int compare(ProducteEspecific p1, ProducteEspecific p2) {
            return (int) (p2.getModa() - p1.getModa());
        }
    };

    public static Comparator<ProducteEspecific> ProductePrecioComparator = new Comparator<ProducteEspecific>() {
        @Override
        public int compare(ProducteEspecific p1, ProducteEspecific p2) {
            if(p2.getPreuC() - p1.getPreuC() < 0){
                return 1;
            }
            if(p1.getPreuC() - p2.getPreuC() < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
    };

    public static Comparator<ProducteEspecific> ProducteBeneficioComparator = new Comparator<ProducteEspecific>() {
        @Override
        public int compare(ProducteEspecific p1, ProducteEspecific p2) {
            if(p2.getPreuB() - p1.getPreuB() < 0){
                return -1;
            }
            if(p1.getPreuB() - p2.getPreuB() < 0){
                return 1;
            }
            else{
                return 0;
            }
        }
    };

}
