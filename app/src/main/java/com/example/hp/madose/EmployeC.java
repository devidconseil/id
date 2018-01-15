package com.example.hp.madose;

/**
 * Created by HP on 07/12/2017.
 */

public class EmployeC {
    int IdEmp;
    String NomEmp;
    int IdDep;
    String ProEmp;

    public EmployeC(){}

    public int getIdEmp() {
        return IdEmp;
    }

    public void setIdEmp(int idEmp) {
        IdEmp = idEmp;
    }

    public String getProEmp() {
        return ProEmp;
    }

    public void setProEmp(String proEmp) {
        ProEmp = proEmp;
    }

    public int getIdDep() {
        return IdDep;
    }

    public void setIdDep(int idDep) {
        IdDep = idDep;
    }

    public String getNomEmp() {
        return NomEmp;
    }

    public void setNomEmp(String nomEmp) {
        NomEmp = nomEmp;
    }

    public EmployeC(int idEmp, String proEmp, int idDep, String nomEmp ) {
        this.IdEmp = idEmp;
        this.ProEmp = proEmp;
        this.IdDep = idDep;
        this.NomEmp = nomEmp;
    }

    @Override
    public String toString() {
        return  "Nom et prénom: " + NomEmp +"\nDepartement: " + IdDep + "\nProfil utilisateur: " + ProEmp;
    }


}
