package com.example.topresale.model;

import java.util.ArrayList;

public class ProducteEspecific {
    private float preuB;
    private float preuC;
    private float preuV;
    private String descripcio;
    private String foto;
    private String estadistica;
    private ArrayList<Valoracio> llistaValoracio = new ArrayList<>();
    private String link;
    private float moda;



    public ProducteEspecific(float preuB, float preuC, float preuV, String descripcio, String foto, String estadistica, String link, float moda) {
        this.preuB = preuB;
        this.preuC = preuC;
        this.preuV = preuV;
        this.descripcio = descripcio;
        this.foto = foto;
        this.estadistica = estadistica;
        this.link = link;
        this.moda = moda;
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
    public void addValoracio(String tipus, String valor){
        Valoracio valoracio = new Valoracio(tipus,valor);
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

}
