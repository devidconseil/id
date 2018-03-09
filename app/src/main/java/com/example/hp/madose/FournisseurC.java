package com.example.hp.madose;

/**
 * Created by HP on 08/12/2017.
 */

public class FournisseurC {
    int idFour;
    String nomFour;
    String adrFour;
    String telFour;

    public FournisseurC(){}

    public int getIdFour() {
        return idFour;
    }

    public void setIdFour(int idFour) {
        this.idFour = idFour;
    }

    public String getNomFour() {
        return nomFour;
    }

    public void setNomFour(String nomFour) {
        this.nomFour = nomFour;
    }

    public String getAdrFour() {
        return adrFour;
    }

    public void setAdrFour(String adrFour) {
        this.adrFour = adrFour;
    }

    public String getTelFour() {
        return telFour;
    }

    public void setTelFour(String telFour) {
        this.telFour = telFour;
    }

    public FournisseurC(int idFour, String nomFour, String adrFour, String telFour) {
        this.idFour = idFour;
        this.nomFour = nomFour;
        this.adrFour = adrFour;
        this.telFour = telFour;
    }
    public FournisseurC(String nomFour){ this.nomFour = nomFour;}
    public FournisseurC( String nomFour, String adrFour, String telFour) {

        this.nomFour = nomFour;
        this.adrFour = adrFour;
        this.telFour = telFour;
    }

    @Override
    public String toString() {
        return "Fournisseur "+ idFour +": " + nomFour +"\nAdresse: " + adrFour + "\nContact: " +telFour ;
    }
    public String listeFour() {
        return  nomFour;
    }
}
