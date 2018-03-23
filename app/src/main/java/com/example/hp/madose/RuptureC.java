package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class RuptureC {

    String Bes;
    int Seuil;
    int Stock;
    String Count;

    public RuptureC() {
    }

    public RuptureC(String bes, int seuil, int stock) {
        Bes = bes;
        Seuil = seuil;
        Stock = stock;
    }
    public RuptureC( String count) {
        Count=count;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
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
    public String toStringBesoin(){return Bes;}
    public int toStringSeuil(){return Seuil;}
    public int toStringStock(){return Stock;}
    public String toStringCount(){return Count;}
}
