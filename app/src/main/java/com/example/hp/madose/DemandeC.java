package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class DemandeC {
    int NumDem;
    String NomEmp;
    String LibBes;
    int Qte;
    String  dateDem;
    String libDpe;

    public int getNumDem() {
        return NumDem;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public String getLibBes() {
        return LibBes;
    }

    public int getQte() {
        return Qte;
    }

    public String getDateDem() {
        return dateDem;
    }

    public String getLibDpe() {
        return libDpe;
    }

    public DemandeC(int numDem, String nomEmp, String libBes, int qte,String dateDem, String libDpe) {
        NumDem = numDem;
        NomEmp = nomEmp;
        LibBes = libBes;
        Qte = qte;
        this.dateDem = dateDem;
        this.libDpe = libDpe;
    }

    @Override
    public String toString() {
        return   NumDem +
                "\nNom de l'employé: " + NomEmp +
                "\nLibellé : " + LibBes +
                "\nQuantité: " + Qte +
                "\nDate: " + dateDem +
                "\nDépartement: " + libDpe +"\n\n";
    }
}
