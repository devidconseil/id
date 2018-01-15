package com.example.hp.madose;

/**
 * Created by HP on 22/12/2017.
 */

public class AddBEC {
    int NumBes, NumEnt, PU, Qte;
    String MarqueBes, Autre_Précision;

    public AddBEC(int numBes, int numEnt, int PU, int qte, String marqueBes, String autre_Précision) {
        NumBes = numBes;
        NumEnt = numEnt;
        this.PU = PU;
        Qte = qte;
        MarqueBes = marqueBes;
        Autre_Précision = autre_Précision;
    }

    public int getNumBes() {
        return NumBes;
    }

    public void setNumBes(int numBes) {
        NumBes = numBes;
    }

    public int getNumEnt() {
        return NumEnt;
    }

    public void setNumEnt(int numEnt) {
        NumEnt = numEnt;
    }

    public int getPU() {
        return PU;
    }

    public void setPU(int PU) {
        this.PU = PU;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int qte) {
        Qte = qte;
    }

    public String getMarqueBes() {
        return MarqueBes;
    }

    public void setMarqueBes(String marqueBes) {
        MarqueBes = marqueBes;
    }

    public String getAutre_Précision() {
        return Autre_Précision;
    }

    public void setAutre_Précision(String autre_Précision) {
        Autre_Précision = autre_Précision;
    }

    @Override
    public String toString() {
        return  "NumBes=" + NumBes +
                ", NumEnt=" + NumEnt +
                ", PU=" + PU +
                ", Qte=" + Qte +
                ", MarqueBes='" + MarqueBes + '\'' +
                ", Autre_Précision='" + Autre_Précision + '\'' +
                '}';
    }
}
