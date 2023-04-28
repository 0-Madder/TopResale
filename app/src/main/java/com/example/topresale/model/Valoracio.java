package com.example.topresale.model;

public class Valoracio {
    /*Hemos hecho una clase valoracion, cada producto especifico tendra un arraylist de todas las valoraciones
    de este producto, hay 2 tipos: de estrellas, o de opinion, esto lo decidira el string tipusValoracio. Si votamos con
    estrellas pasara a ser un valor float.
     */

    private String tipusValoracio;
    private String valorValoracio;
    private String idProdEspecific;

    public Valoracio(String tipusValoracio, String valorValoracio, String idProdEspecific) {
        this.tipusValoracio = tipusValoracio;
        this.valorValoracio = valorValoracio;
        this.idProdEspecific = idProdEspecific;
    }

    public String getIdProdEspecific() {
        return idProdEspecific;
    }

    public void setIdProdEspecific(String idProdEspecific) {
        this.idProdEspecific = idProdEspecific;
    }

    public String getTipusValoracio() {
        return tipusValoracio;
    }

    public void setTipusValoracio(String tipusValoracio) {
        this.tipusValoracio = tipusValoracio;
    }

    public String getValorValoracio() {
        return valorValoracio;
    }

    public void setValorValoracio(String valorValoracio) {
        this.valorValoracio = valorValoracio;
    }
    public float cambiador(String valorValoracio){
        return Float.valueOf(valorValoracio);
    }


}
