package com.example.hp.madose;

/**
 * Created by HP on 01/02/2018.
 */

public class ListAchatC {
String LibBes;
int Qte;

    public ListAchatC(String libBes, int qte) {
        LibBes = libBes;
        Qte = qte;
    }

    public String getLibBes() {
        return LibBes;
    }

    public void setLibBes(String libBes) {
        LibBes = libBes;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    @Override
    public String toString() {
        return  LibBes + "\n"+
                "Qte=" + Qte;
    }
}
