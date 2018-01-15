package com.example.hp.madose;

/**
 * Created by HP on 12/12/2017.
 */

public class BesoinC {
    int NumBes;
    String LibBes;
    String TypeBes;
    int IdCat;
    int SeuilBes;
    String AmorBes;
    int StockBes;

    void BesoinC(){}

    public int getNumBes() {
        return NumBes;
    }

    public void setNumBes(int numBes) {
        NumBes = numBes;
    }

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

    public int getIdCat() {
        return IdCat;
    }

    public void setIdCat(int idCat) {
        IdCat = idCat;
    }

    public int getSeuilBes() {
        return SeuilBes;
    }

    public void setSeuilBes(int seuilBes) {
        SeuilBes = seuilBes;
    }

    public String getAmorBes() {
        return AmorBes;
    }

    public void setAmorBes(String amorBes) {
        AmorBes = amorBes;
    }

    public int getStockBes() {
        return StockBes;
    }

    public void setStockBes(int stockBes) {
        StockBes = stockBes;
    }

    public BesoinC(int numBes, String libBes, String typeBes, int idCat, int seuilBes, String amorBes,int StockBes) {
        this.NumBes = numBes;
        this.LibBes = libBes;
        this.TypeBes = typeBes;
        this.IdCat = idCat;
        this.SeuilBes = seuilBes;
        this.AmorBes = amorBes;
        this.StockBes=StockBes;
    }

    @Override
    public String toString() {
        return NumBes + " Libellé " + LibBes + "\nType: " + TypeBes +"\nCatégorie: " + IdCat +"\nSeuil: " + SeuilBes +"\nAmor" + AmorBes +"\nStock: " +StockBes+
                "\n";
    }
}
