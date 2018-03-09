package com.example.hp.madose;

import android.util.Log;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by HP on 05/12/2017.
 */

public class DepartementC{
    int idDep;
    String libDep;

    public DepartementC() {}


    public int getIdDep() {
        return idDep;
    }

    public void setIdDep(int idDep) {
        this.idDep = idDep;
    }

    public String getLibDep() {
        return libDep;
    }

    public void setLibDep(String libDep) {
        this.libDep = libDep;
    }


    public DepartementC(int idDep, String libDep) {
        this.idDep = idDep;
        this.libDep = libDep;
    }
    public DepartementC( String libDep) {
        this.libDep = libDep;
    }
    @Override
    public String toString() {
        return   idDep + " Departement " + libDep ;
    }
    public String libDep() {
        return  libDep ;
    }
}
