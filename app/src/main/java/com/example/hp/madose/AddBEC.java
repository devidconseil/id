package com.example.hp.madose;

/**
 * Created by HP on 22/12/2017.
 */

public class AddBEC {
    int numBes, numEnt, pu, qte;
    String marqueBes, autrePrécision,datEnt,libBes,heureEnt;

    public String getLibBes() {
        return libBes;
    }

    public void setLibBes(String libBes) {
        this.libBes = libBes;
    }

    public AddBEC() {

    }

    public String getHeureEnt() {
        return heureEnt;
    }

    public void setHeureEnt(String heureEnt) {
        this.heureEnt = heureEnt;
    }

    public AddBEC(int numBes, int numEnt, int pu, int qte, String marqueBes, String autrePrécision) {
        this.numBes = numBes;
        this.numEnt = numEnt;
        this.pu = pu;

        this.qte = qte;
        this.marqueBes = marqueBes;
        this.autrePrécision = autrePrécision;

    }
    public AddBEC(String heureEnt,String libBes, String datEnt, int pu, int qte, String marqueBes, String autrePrécision) {
        this.heureEnt=heureEnt;
        this.libBes=libBes;
        this.datEnt=datEnt;
        this.pu = pu;
        this.qte = qte;
        this.marqueBes = marqueBes;
        this.autrePrécision = autrePrécision;

    }

    public int getNumBes() {
        return numBes;
    }

    public String getDatEnt() {
        return datEnt;
    }

    public void setDatEnt(String datEnt) {
        this.datEnt = datEnt;
    }

    public void setNumBes(int numBes) {
        numBes = numBes;
    }

    public int getNumEnt() {
        return numEnt;
    }

    public void setNumEnt(int numEnt) {
        this.numEnt = numEnt;
    }

    public int getPU() {
        return pu;
    }

    public void setPU(int pU) {
        this.pu = pU;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getMarqueBes() {
        return marqueBes;
    }

    public void setMarqueBes(String marqueBes) {
        this.marqueBes = marqueBes;
    }

    public String getAutrePrécision() {
        return autrePrécision;
    }

    public void setAutrePrécision(String autrePrécision) {
        this.autrePrécision = autrePrécision;
    }

    @Override
    public String toString() {
        return  "NumBes=" + numBes +
                ", numEnt=" + numEnt +
                ", pu=" + pu +
                ", qte=" + qte +
                ", marqueBes='" + marqueBes + '\'' +
                ", autrePrécision='" + autrePrécision + '\'' +
                '}';
    }
}
