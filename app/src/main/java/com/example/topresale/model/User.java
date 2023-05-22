package com.example.topresale.model;

public class User {

    //Atributs -> nomComplet, correo, nomUser, pswd
    private String id;
    private String nomComplet;
    private String correo;
    private String nomUser;
    private String pswd;
    private PerfilUser perfilUser;

    public User(String nomComplet, String correo, String nomUser, String pswd, String id) {
        this.nomComplet = nomComplet;
        this.correo = correo;
        this.nomUser = nomUser;
        this.pswd = pswd;
        this.id = id;
        this.perfilUser = new PerfilUser();
    }

    public User(){

    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PerfilUser getPerfilUser() {
        return perfilUser;
    }

    public void setPerfilUser(PerfilUser perfilUser) {
        this.perfilUser = perfilUser;
    }
}
