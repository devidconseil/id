package com.example.hp.madose;

/**
 * Created by HP on 06/12/2017.
 */

public class CategorieC {
    int idCat;
    String libCat;

    public CategorieC(){}

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int IdCat) {
        this.idCat = idCat;
    }

    public String getLibCat() {
        return libCat;
    }

    public void setLibCat(String LibCat) {
        this.libCat = libCat;
        }

    @Override
    public String toString() {
        return "Categorie " +idCat +" "+ libCat;
    }

    public CategorieC(int idCat, String libCat) {
        this.idCat = idCat;
        this.libCat = libCat;
    }

    public CategorieC( String libCat) {

        this.libCat = libCat;
    }

}
