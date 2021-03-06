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
    String heureDem;
    String etat;

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

    public String getHeureDem() {
        return heureDem;
    }

    public void setHeureDem(String heureDem) {
        this.heureDem = heureDem;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public DemandeC(int numDem, String nomEmp, String libBes, int qte,String dateDem, String libDpe,String etat) {
        this.numDem = numDem;
        this.nomEmp = nomEmp;
        this.libBes = libBes;
        this.qte = qte;
        this.dateDem = dateDem;
        this.libDpe = libDpe;
        this.etat=etat;
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
    public DemandeC(String nomEmp,String libDpe, String libBes,String dateDem, int qte, String heureDem,String etat) {
        this.nomEmp = nomEmp;
        this.libBes = libBes;
        this.qte = qte;
        this.dateDem = dateDem;
        this.libDpe = libDpe;
        this.heureDem=heureDem;
        this.etat=etat;

    }
    public DemandeC(String dateDem ) {

        this.dateDem = dateDem;

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
    public int toStringNum() {return numDem;}
    public String toStringNomEmp() {return nomEmp;}
    public String toStringLib() {return libBes;}
    public String toStringDepa() {return libDpe;}
    public int toStringQt() {return qte;}
    public String toStringDate() {return dateDem;}
    public String toStringEtat() {return etat;}
    public String dateo() {return dateDem;}
}
