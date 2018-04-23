package com.example.hp.madose;

/**
 * Created by HP on 27/12/2017.
 */

public class Stock1 {
    String LibBes,TypeBes,date,Marque,Autre;
    int PU , Qte,numEnt;

    public Stock1(int numEnt,String libBes, String typeBes, int PU, int qte,String date,String Marque,String Autre) {
        this.numEnt=numEnt;
        this.LibBes = libBes;
        this.TypeBes = typeBes;
        this.PU = PU;
        this.Qte = qte;
        this.date=date;
        this.Marque=Marque;
        this.Autre=Autre;
    }
    public Stock1(String libBes, String typeBes, int PU, int qte,String date) {
        this.LibBes = libBes;
        this.TypeBes = typeBes;
        this.PU = PU;
        this.Qte = qte;
        this.date=date;
        this.Marque=Marque;
        this.Autre=Autre;
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

    public int getNumEnt() {
        return numEnt;
    }

    public String getDate() {
        return date;
    }

    public String getMarque() {
        return Marque;
    }

    public String getAutre() {
        return Autre;
    }

    @Override
    public String toString() {
        return  "Besoin=" + LibBes +
                "\nType=" + TypeBes +
                "\nP.U=" + PU +
                "\nQuantité=" + Qte +"\nDate=" +date+"\n";
    }
    public String toStringDate(){ return date;}
    public String toStringNom(){ return LibBes;}
    public String toStringType(){ return TypeBes;}
    public int toStringPU(){ return PU;}
    public int toStringQuantite(){ return Qte;}
    public String toStringMarque(){ return Marque;}
    public String toStringAutre(){ return Autre;}

}
