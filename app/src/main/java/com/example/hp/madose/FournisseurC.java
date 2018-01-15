package com.example.hp.madose;

/**
 * Created by HP on 08/12/2017.
 */

public class FournisseurC {
    int IdFour;
    String NomFour;
    String AdrFour;
    int TelFour;

    public FournisseurC(){}

    public int getIdFour() {
        return IdFour;
    }

    public void setIdFour(int idFour) {
        IdFour = idFour;
    }

    public String getNomFour() {
        return NomFour;
    }

    public void setNomFour(String nomFour) {
        NomFour = nomFour;
    }

    public String getAdrFour() {
        return AdrFour;
    }

    public void setAdrFour(String adrFour) {
        AdrFour = adrFour;
    }

    public int getTelFour() {
        return TelFour;
    }

    public void setTelFour(int telFour) {
        TelFour = telFour;
    }

    public FournisseurC(int idFour, String nomFour, String adrFour, int telFour) {
        IdFour = idFour;
        NomFour = nomFour;
        AdrFour = adrFour;
        TelFour = telFour;
    }

    @Override
    public String toString() {
        return "Fournisseur "+ IdFour +": " + NomFour +"\nAdresse: " + AdrFour + "\nContact: " +TelFour ;
    }
}
