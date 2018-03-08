package com.example.hp.madose;

/**
 * Created by HP on 22/12/2017.
 */

public class AddEC {
    int numEnt;
    int dateEnt, idFour;
    String datEnt,libFour;

    public AddEC(int numEnt, int idFour, int dateEnt) {
        this.numEnt = numEnt;
        this.idFour = idFour;
        this.dateEnt = dateEnt;
    }
    public AddEC( String libFour, String datEnt) {
        this.libFour = libFour;
        this.datEnt = datEnt;
    }

    public AddEC() {
    }

    public void AddEC(){}

    public int getNumEnt() {
        return numEnt;
    }

    public void setNumEnt(int numEnt) {
        this.numEnt = numEnt;
    }

    public int getDateEnt() {
        return dateEnt;
    }

    public String getDatEnt() {
        return datEnt;
    }

    public void setDatEnt(String datEnt) {
        this.datEnt = datEnt;
    }

    public String getLibFour() {
        return libFour;
    }

    public void setLibFour(String libFour) {
        this.libFour = libFour;
    }

    public void setDateEnt(int dateEnt) {
        this.dateEnt = dateEnt;
    }

    public int getIdFour() {
        return idFour;
    }

    public void setIdFour(int idFour) {
        this.idFour = idFour;
    }

    @Override
    public String toString() {
        return  "numEnt=" + numEnt +
                ", DateEnt=" + dateEnt +
                ", IdFour=" + idFour;
    }
}
