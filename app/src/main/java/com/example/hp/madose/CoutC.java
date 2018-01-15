package com.example.hp.madose;

/**
 * Created by HP on 28/12/2017.
 */

public class CoutC {
    int num;
    String lib;
    int cout;
    int qut;

    public int getNum() {
        return num;
    }

    public String getLib() {
        return lib;
    }

    public int getCout() {
        return cout;
    }

    public int getQut() {
        return qut;
    }

    public CoutC(int num, String lib, int cout, int qut) {
        this.num = num;
        this.lib = lib;
        this.cout = cout;
        this.qut = qut;
    }

    @Override
    public String toString() {
        return  "Numéro de l\'entrée: " + num +
                "\nNom du besoin: " + lib +
                "\nCoût du besoin: " + cout +
                "\nQuantité: " + qut ;
    }
}
