package com.example.topresale.model;

import java.util.ArrayList;

public class Producte {
    private ArrayList<ProducteEspecific> llistaProdEspe;
    private String name;
    private String foto;
    private boolean tendencia;

    public Producte(ArrayList<ProducteEspecific> llistaProdEspe, String name, String foto, boolean tendencia) {
        this.llistaProdEspe = llistaProdEspe;
        this.name = name;
        this.foto = foto;
        this.tendencia = tendencia;
    }

    public ArrayList<ProducteEspecific> getLlistaProdEspe() {
        return llistaProdEspe;
    }

    public void setLlistaProdEspe(ArrayList<ProducteEspecific> llistaProdEspe) {
        this.llistaProdEspe = llistaProdEspe;
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
}
