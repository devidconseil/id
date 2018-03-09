package com.example.hp.madose;

/**
 * Created by HP on 12/12/2017.
 */

public class BesoinC {
    int numBes;
    String libBes;
    String typeBes;
    int idCat;
    int seuilBes;
    String amorBes;
    int stockBes;
    int imageBes;
    String libCat;

    public BesoinC(){}

    public String getLibCat() {
        return libCat;
    }

    public void setLibCat(String libCat) {
        this.libCat = libCat;
    }

    public int getNumBes() {
        return numBes;
    }

    public void setNumBes(int numBes) {
        this.numBes = numBes;
    }

    public String getLibBes() {
        return libBes;
    }

    public void setLibBes(String libBes) {
        this.libBes = libBes;
    }

    public String getTypeBes() {
        return typeBes;
    }

    public void setTypeBes(String typeBes) {
        this.typeBes = typeBes;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public int getImageBes() {
        return imageBes;
    }

    public void setImageBes(int imageBes) {
        this.imageBes = imageBes;
    }

    public int getSeuilBes() {
        return seuilBes;
    }

    public void setSeuilBes(int seuilBes) {
        this.seuilBes = seuilBes;
    }

    public String getAmorBes() {
        return amorBes;
    }

    public void setAmorBes(String amorBes) {
        this.amorBes = amorBes;
    }

    public int getStockBes() {
        return stockBes;
    }

    public void setStockBes(int stockBes) {
        this.stockBes = stockBes;
    }


    public BesoinC(int numBes, String libBes, String typeBes, int idCat, int seuilBes, String amorBes,int StockBes) {
        this.numBes = numBes;
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.idCat = idCat;
        this.seuilBes = seuilBes;
        this.amorBes = amorBes;
        this.stockBes=StockBes;
    }

    public BesoinC(int numBes, String libBes, String typeBes, int idCat, int seuilBes, String amorBes,int StockBes,int imageBes) {
        this.numBes = numBes;
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.idCat = idCat;
        this.seuilBes = seuilBes;
        this.amorBes = amorBes;
        this.stockBes=StockBes;
        this.imageBes=imageBes;
    }
    public BesoinC( String libBes, String typeBes, String libCat, int seuilBes, String amorBes,int StockBes,int imageBes) {
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.libCat=libCat;
        this.seuilBes = seuilBes;
        this.amorBes = amorBes;
        this.stockBes=StockBes;
        this.imageBes=imageBes;
    }

    public BesoinC(String libBes) {
        this.libBes = libBes;
    }

    @Override
    public String toString() {
        return numBes + " Libellé " + libBes + "\nType: " + typeBes +"\nCatégorie: " + idCat +"\nSeuil: " + seuilBes +"\nAmor" + amorBes +"\nStock: " +stockBes+
                "\n";
    }
    public String toString1() {
        return numBes + " Libellé " + libBes + "\nType: " + typeBes +"\nCatégorie: " + libCat +"\nSeuil: " + seuilBes +"\nAmor" + amorBes +"\nStock: " +stockBes+
                "\n";
    }

    public String libelleBesoin() {
        return libBes ;
    }




}
