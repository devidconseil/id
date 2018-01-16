package com.example.hp.madose;

/**
 * Created by HP on 27/12/2017.
 */

public class Stock1 {
    String LibBes,TypeBes,date;
    int PU , Qte;

    public Stock1(String libBes, String typeBes, int PU, int qte,String date) {
        this.LibBes = libBes;
        this.TypeBes = typeBes;
        this.PU = PU;
        this.Qte = qte;
        this.date=date;
    }

    public String getLibBes() {
        return LibBes;
    }

    public String getTypeBes() {
        return TypeBes;
    }

    public int getPU() {
        return PU;
    }

    public int getQte() {
        return Qte;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return  "Besoin=" + LibBes +
                "\nType=" + TypeBes +
                "\nP.U=" + PU +
                "\nQuantité=" + Qte +"\nDate=" +date+"\n";
    }
}
