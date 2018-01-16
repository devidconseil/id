package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class StockC {

    String LibBes;
    String TypeBes;
    String DateAmor;
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

    public String getDateAmor() {
        return DateAmor;
    }

    public void setDateAmor(String dateAmor) {
        DateAmor = dateAmor;
    }


    public StockC(String libBes, String typeBes, int seuilBes, int stockBes) {
        LibBes = libBes;
        TypeBes = typeBes;
        SeuilBes = seuilBes;
        StockBes = stockBes;
    }

    public StockC(String libBes, String typeBes, int stockBes, String dateAmor) {
        LibBes = libBes;
        TypeBes = typeBes;
        StockBes = stockBes;
        DateAmor = dateAmor;
    }

    @Override
    public String toString() {
        return "LibBes=" + LibBes  +
                "\nTypeBes=" + TypeBes  +
                "\nSeuilBes=" + SeuilBes +
                "\nStockBes=" + StockBes + "\n";
    }


    public String toString1() {
        return
                "\nLibellé du besoin: " + LibBes +
                        "\nType: " + TypeBes +
                        "\nStock du besoin: " + StockBes +
                        "\nDate d\'amortissement: " + DateAmor + "\n";
    }
}
