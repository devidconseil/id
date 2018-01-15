package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class StockC {

    String LibBes;
    String TypeBes;
    int SeuilBes;
    int StockBes;

    public String getLibBes() {
        return LibBes;
    }

    public void setLibBes(String libBes) {
        LibBes = libBes;
    }

    public String getTypeBes() {
        return TypeBes;
    }

    public void setTypeBes(String typeBes) {
        TypeBes = typeBes;
    }

    public int getSeuilBes() {
        return SeuilBes;
    }

    public void setSeuilBes(int seuilBes) {
        SeuilBes = seuilBes;
    }

    public int getStockBes() {
        return StockBes;
    }

    public void setStockBes(int stockBes) {
        StockBes = stockBes;
    }

    public StockC(String libBes, String typeBes, int seuilBes, int stockBes) {
        LibBes = libBes;
        TypeBes = typeBes;
        SeuilBes = seuilBes;
        StockBes = stockBes;
    }

    @Override
    public String toString() {
        return
                "\nLibellé du besoin: " + LibBes +
                "\nType: " + TypeBes +
                "\nSeuil du besoin: " + SeuilBes +
                "\nStock du besoin: " + StockBes ;
    }
}
