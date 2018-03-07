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
    int ImageBes;

    public String getLibBes() {
        return LibBes;
    }

    public int getImageBes() {
        return ImageBes;
    }

    public void setImageBes(int imageBes) {
        ImageBes = imageBes;
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
    public StockC(String libBes, String typeBes, int seuilBes, int stockBes,int imageBes) {
        LibBes = libBes;
        TypeBes = typeBes;
        SeuilBes = seuilBes;
        StockBes = stockBes;
        ImageBes= imageBes;
    }

    public StockC(String libBes, String typeBes, int stockBes, String dateAmor) {
        LibBes = libBes;
        TypeBes = typeBes;
        StockBes = stockBes;
        DateAmor = dateAmor;
    }

    public StockC(String libBes, String typeBes, int stockBes, String dateAmor,int imageBes) {
        LibBes = libBes;
        TypeBes = typeBes;
        StockBes = stockBes;
        DateAmor = dateAmor;
        ImageBes= imageBes;
    }
    public StockC(String libBes, String typeBes, String dateAmor,int imageBes) {
        LibBes = libBes;
        TypeBes = typeBes;
        DateAmor = dateAmor;
        ImageBes= imageBes;
    }

  /*  @Override
    public String toString() {
        return "LibBes=" + LibBes  +
                "\nTypeBes=" + TypeBes  +
                "\nSeuilBes=" + SeuilBes +
                "\nStockBes=" + StockBes + "\n";
    }  */
  @Override
  public String toString() {
      return  LibBes  +
              "\n" + TypeBes  +
              "\nSeuil d'alerte: " + SeuilBes +
              "\nEn Stock: " + StockBes + "\n"+ImageBes;
  }

    public String toString1() {
        return
                LibBes +
                        "\n " + TypeBes +
                        "\nDate d\'amortissement: " + DateAmor + "\n";
    }
}
