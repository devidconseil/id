package com.example.hp.madose;

/**
 * Created by HP on 27/12/2017.
 */

public class Stock2 {

        String libBes, typeBes, nomEmp,date, libDep,heureSor,validationUser,marqBes,autreP,dateDem;
        int qte,numSor;


    public String getLibBes() {
        return libBes;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setLibBes(String libBes) {
        LibBes = libBes;
    }

    public void setTypeBes(String typeBes) {
        TypeBes = typeBes;
    }

    public void setNomEmp(String nomEmp) {
        NomEmp = nomEmp;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    public int getQte() {
        return qte;
    }

    public String getDateDem() {
        return dateDem;
    }

    public int getNumSor() {
        return numSor;
    }

    public void setNumSor(int numSor) {
        this.numSor = numSor;
    }

    public void setDateDem(String dateDem) {
        this.dateDem = dateDem;
    }

    public String getMarqBes() {
        return marqBes;

    }

    public void setMarqBes(String marqBes) {
        this.marqBes = marqBes;
    }

    public String getAutreP() {
        return autreP;
    }

    public void setAutreP(String autreP) {
        this.autreP = autreP;
    }

    public String getHeureSor() {
        return heureSor;

    }

    public void setHeureSor(String heureSor) {
        this.heureSor = heureSor;
    }

    public String getValidationUser() {
        return validationUser;
    }

    public void setValidationUser(String validationUser) {
        this.validationUser = validationUser;
    }

    public String getTypeBes() {
        return typeBes;
    }

    public String getLibDep() {
        return libDep;
    }

    public void setLibDep(String libDep) {
        this.libDep = libDep;
    }

    public String getDate() {
        return date;
    }

    public Stock2() {
    }

    public Stock2(String libBes, String typeBes, int qte, String nomEmp, String date) {
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.qte = qte;
        this.nomEmp = nomEmp;
        this.date =date;
    }
    public Stock2(int numSor,String libBes, String typeBes, int qte, String nomEmp, String date) {
        this.numSor= numSor;
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.qte = qte;
        this.nomEmp = nomEmp;
        this.date =date;
    }
    public Stock2(String libBes, String typeBes, int qte, String nomEmp,String date,String heureSor, String validationUser) {
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.qte = qte;
        this.nomEmp = nomEmp;
        this.date =date;
        this.heureSor=heureSor;
        this.validationUser=validationUser;
    }
    public Stock2(String libBes, String typeBes,String libDep,int qte,String date) {
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.qte = qte;
        this.libDep = libDep;
        this.date =date;
    }
    public Stock2(String libBes, String typeBes,String libDep,int qte,String date,String heureSor, String validationUser) {
        this.libBes = libBes;
        this.typeBes = typeBes;
        this.qte = qte;
        this.libDep = libDep;
        this.date =date;
        this.heureSor=heureSor;
        this.validationUser=validationUser;
    }

    public Stock2(String libBes, String marqBes, String autreP, String dateDem, String nomEmp, String date, String libDep, String heureSor, String validationUser, int qte) {
        this.libBes = libBes;
        this.marqBes=marqBes;
        this.autreP=autreP;
        this.dateDem=dateDem;
        this.nomEmp = nomEmp;
        this.date = date;
        this.libDep = libDep;
        this.heureSor = heureSor;
        this.validationUser = validationUser;
        this.qte = qte;
    }

    @Override
    public String toString() {
        return numSor+
                "\nBesoin: " + libBes +
                "\nType: " + typeBes +
                "\nQuantité: " + qte +
                "\nReçu par: " + nomEmp +
                "\nLe:  "+date+"\n";
    }
    public String toString2() {
        return  "Besoin: " + libBes +
                "\nType: " + typeBes +
                "\nQuantité: " + qte +
                "\nReçu par: " + nomEmp +
                "\nLe:  "+date+
                "\nEt delivré par:  "+validationUser+
                "\nPour la demande du:  "+dateDem+"\n";
    }
    public String toString1() {
        return  "Besoin: " + libBes +
                "\nType: " + typeBes +
                "\nQuantité: " + qte +
                "\nReçu par le departement " + libDep +
                "\nLe:  "+date+"\n";
    }
    public String toString3() {
        return  "Besoin: " + libBes +
                "\nType: " + typeBes +
                "\nQuantité: " + qte +
                "\nReçu par le departement " + libDep +
                "\nLe:  "+date+
                "\nEt delivré par:  "+validationUser+
                "\nPour la demande du:  "+dateDem+"\n";
    }
    /* public Stock2(String libBes, String typeBes, int qte) {
            this.libBes = libBes;
            this.typeBes = typeBes;
            this.qte = qte;
        }

        public String getLibBes() {
            return libBes;
        }

        public String getTypeBes() {
            return typeBes;
        }



        public int getQte() {
            return qte;
        }

        @Override
        public String toString() {
            return  "Besoin=" + libBes +
                    "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tType=" + typeBes +
                    "\nQuantité=" + qte;
        }*/


}
