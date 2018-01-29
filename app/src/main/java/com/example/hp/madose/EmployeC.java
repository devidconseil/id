package com.example.hp.madose;

/**
 * Created by HP on 07/12/2017.
 */

public class EmployeC {
    int IdEmp;
    String NomEmp;
    String LibDep;
    String ProEmp;

    public EmployeC(){}
    public int getIdEmp() {
        return IdEmp;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public String getLibDep() {
        return LibDep;
    }

    public String getProEmp() {
        return ProEmp;
    }

    public EmployeC(int idEmp, String nomEmp, String libDep, String proEmp) {
        IdEmp = idEmp;
        NomEmp = nomEmp;
        LibDep = libDep;
        ProEmp = proEmp;
    }

    public EmployeC(String nomEmp, String libDep, String proEmp) {
        NomEmp = nomEmp;
        LibDep = libDep;
        ProEmp = proEmp;
    }

    @Override
    public String toString() {
        return "\nNom: " + NomEmp + "\nDepartement: " + LibDep+ "\nProfil: " + ProEmp ;
    }
}
