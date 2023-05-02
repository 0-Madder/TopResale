package com.example.topresale.model;

import java.util.ArrayList;

public class PerfilUser{

    private ArrayList<ProducteEspecific> favoritos;

    public PerfilUser() {
        this.favoritos = new ArrayList<ProducteEspecific>();
    }

    public ArrayList<ProducteEspecific> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<ProducteEspecific> favoritos) {
        this.favoritos = favoritos;
    }

    public void listFavorite(){
        for(ProducteEspecific pe : favoritos){

        }
    }

}
