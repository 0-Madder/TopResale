package com.example.topresale.model;

import java.util.ArrayList;

public class Producte {
    private ArrayList<ProducteEspecific> llistaProdEspe;
    private String name;
    private String foto;
    private float mitjanaModa;
    private boolean tendencia;

    public Producte(ArrayList<ProducteEspecific> llistaProdEspe, String name, String foto, boolean tendencia) {
        this.llistaProdEspe = llistaProdEspe;
        this.name = name;
        this.foto = foto;
        calcularMitjanaModa();
        this.mitjanaModa = getMitjanaModa();
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

    public void addProductEspecific(float preuB, float preuC, float preuV, String descripcio, String foto, String estadistica, String link, int moda){
        ProducteEspecific producteEspecific = new ProducteEspecific(preuB,preuC,preuV,descripcio,foto,estadistica,link,moda);
        llistaProdEspe.add(producteEspecific);
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
}
