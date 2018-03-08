package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class DemandeC {
    int numDem;
    String nomEmp;
    String libBes;
    int qte;
    String  dateDem;
    String libDpe;

    public DemandeC() {
    }

    public int getNumDem() {
        return numDem;
    }

    public void setNumDem(int numDem) {
        this.numDem = numDem;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    public void setLibBes(String libBes) {
        this.libBes = libBes;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public void setDateDem(String dateDem) {
        this.dateDem = dateDem;
    }

    public void setLibDpe(String libDpe) {
        this.libDpe = libDpe;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public String getLibBes() {
        return libBes;
    }

    public int getQte() {
        return qte;
    }

    public String getDateDem() {
        return dateDem;
    }

    public String getLibDpe() {
        return libDpe;
    }

    public DemandeC(int numDem, String nomEmp, String libBes, int qte,String dateDem, String libDpe) {
        this.numDem = numDem;
        this.nomEmp = nomEmp;
        this.libBes = libBes;
        this.qte = qte;
        this.dateDem = dateDem;
        this.libDpe = libDpe;
    }

    public DemandeC(int numDem, String libDpe, String libBes, int qte,String dateDem ) {
        this.numDem = numDem;
        this.libBes = libBes;
        this.qte = qte;
        this.dateDem = dateDem;
        this.libDpe = libDpe;
    }
    public DemandeC(String nomEmp,String libDpe, String libBes,String dateDem, int qte ) {
        this.nomEmp = nomEmp;
        this.libBes = libBes;
        this.qte = qte;
        this.dateDem = dateDem;
        this.libDpe = libDpe;
    }

    @Override
    public String toString() {
        return   numDem +
                "\nNom de l'employé: " + nomEmp +
                "\nLibellé : " + libBes +
                "\nQuantité: " + qte +
                "\nDate: " + dateDem +
                "\nDépartement: " + libDpe +"\n\n";
    }

    public String toString1() {
        return   numDem +
                " \nDépartement bénéficiaire: " + libDpe +
                "\nLibellé : " + libBes +
                "\nQuantité: " + qte +
                "\nDate: " + dateDem +
                "\n\n";
    }
}
