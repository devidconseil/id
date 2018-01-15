package com.example.hp.madose;

/**
 * Created by HP on 06/12/2017.
 */

public class CategorieC {
    int IdCat;
    String LibCat;

    public CategorieC(){}

    public int getIdCat() {
        return IdCat;
    }

    public void setIdCat(int idCat) {
        IdCat = idCat;
    }

    public String getLibCat() {
        return LibCat;
    }

    public void setLibCat(String libCat) {
        LibCat = libCat;
        }

    @Override
    public String toString() {
        return "Categorie " +IdCat +" "+ LibCat;
    }

    public CategorieC(int idCat, String libCat) {
        this.IdCat = idCat;
        this.LibCat = libCat;
    }


}
