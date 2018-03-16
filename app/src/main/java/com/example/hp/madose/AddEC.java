package com.example.hp.madose;

/**
 * Created by HP on 22/12/2017.
 */

public class AddEC {
    int numEnt;
    int dateEnt, idFour;
    String datEnt,libFour,heureEnt,user;


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

    public AddEC(String libFour, String datEnt, String heureEnt, String user) {
        this.datEnt = datEnt;
        this.libFour = libFour;
        this.heureEnt = heureEnt;
        this.user = user;

    }

    public void AddEC(){}

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



    public String getHeureEnt() {
        return heureEnt;
    }

    public void setHeureEnt(String heureEnt) {
        this.heureEnt = heureEnt;
    }

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
    public String toString_new() {
        return  "numEnt=" + numEnt +
                ", DateEnt=" + dateEnt +
                ", Four=" + idFour+
                ", Code="+heureEnt+
                ", Effectué par:"+user;
    }
}
