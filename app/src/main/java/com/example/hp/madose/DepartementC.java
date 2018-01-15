package com.example.hp.madose;

import android.util.Log;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by HP on 05/12/2017.
 */

public class DepartementC{
    int idDep;
    String LibDep;

    public DepartementC() {}


    public int getIdDep() {
        return idDep;
    }

    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    public String getLibDep() {
        return LibDep;
    }

    public void setLibDep(String libDep) {
        LibDep = libDep;
    }

    @Override
    public String toString() {
        return   idDep + " Departement " + LibDep ;
    }


    public DepartementC(int idDep, String libDep) {
        this.idDep = idDep;
        this.LibDep = libDep;
    }
}
