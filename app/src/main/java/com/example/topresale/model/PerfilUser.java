package com.example.topresale.model;

import java.util.ArrayList;

public class PerfilUser extends User{

    private ArrayList<ProducteEspecific> favoritos;

    public PerfilUser(String nomComplet, String correo, String nomUser, String pswd, ArrayList<ProducteEspecific> favoritos) {
        super(nomComplet, correo, nomUser, pswd);
        this.favoritos = favoritos;
    }
}
