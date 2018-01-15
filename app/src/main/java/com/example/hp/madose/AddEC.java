package com.example.hp.madose;

/**
 * Created by HP on 22/12/2017.
 */

public class AddEC {
    int NumEnt;
    int DateEnt, IdFour;

    public AddEC(int numEnt, int idFour, int dateEnt) {
        NumEnt = numEnt;
        IdFour = idFour;
        DateEnt = dateEnt;
    }
    public void AddEC(){}

    public int getNumEnt() {
        return NumEnt;
    }

    public void setNumEnt(int numEnt) {
        NumEnt = numEnt;
    }

    public int getDateEnt() {
        return DateEnt;
    }

    public void setDateEnt(int dateEnt) {
        DateEnt = dateEnt;
    }

    public int getIdFour() {
        return IdFour;
    }

    public void setIdFour(int idFour) {
        IdFour = idFour;
    }

    @Override
    public String toString() {
        return  "NumEnt=" + NumEnt +
                ", DateEnt=" + DateEnt +
                ", IdFour=" + IdFour;
    }
}
