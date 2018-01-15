package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class RuptureC {

    String Bes;
    int Seuil;
    int Stock;

    public RuptureC(String bes, int seuil, int stock) {
        Bes = bes;
        Seuil = seuil;
        Stock = stock;
    }

    public String getBes() {
        return Bes;
    }

    public void setBes(String bes) {
        Bes = bes;
    }

    public int getSeuil() {
        return Seuil;
    }

    public void setSeuil(int seuil) {
        Seuil = seuil;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    @Override
    public String toString() {
        return  "Besoin: " + Bes +
                "\nSeuil: " + Seuil +
                "\nStock: " + Stock +"\n\n";
    }
}
