package com.example.hp.madose;

/**
 * Created by HP on 07/12/2017.
 */

public class UtilisateurC {
    int idEmp;
    String nomEmp;
    String libDep;
    String proEmp;
    String prenEmp;
    String mailEmp;
    String telEmp;


    public UtilisateurC(){}

    public UtilisateurC(String nomEmp, String prenEmp) {
        this.nomEmp = nomEmp;
        this.prenEmp=prenEmp;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public String getPrenEmp() {
        return prenEmp;
    }

    public void setPrenEmp(String prenEmp) {
        this.prenEmp = prenEmp;
    }

    public String getMailEmp() {
        return mailEmp;
    }

    public void setMailEmp(String mailEmp) {
        this.mailEmp = mailEmp;
    }

    public String getTelEmp() {
        return telEmp;
    }

    public void setTelEmp(String telEmp) {
        this.telEmp = telEmp;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public String getLibDep() {
        return libDep;
    }

    public String getProEmp() {
        return proEmp;
    }

  /*  public UtilisateurC(int idEmp, String nomEmp, String prenEmp, String mailEmp, String telEmp, String libDep, String proEmp) {
        IdEmp = idEmp;
        nomEmp = nomEmp;
        libDep = libDep;
        ProEmp = proEmp;
        PrenEmp=prenEmp;
        MailEmp=mailEmp;
        TelEmp=telEmp;
    } */

    public UtilisateurC(String nomEmp, String prenEmp, String mailEmp, String telEmp, String libDep, String proEmp) {
        this.nomEmp = nomEmp;
        this.libDep = libDep;
        this.proEmp = proEmp;
        this.prenEmp=prenEmp;
        this.mailEmp=mailEmp;
        this.telEmp=telEmp;
    }



    @Override
    public String toString() {
        return  "Nom: "+nomEmp + "\nPrénoms: " + prenEmp + "\nMail: " + mailEmp + "\nTel: " + telEmp + "\nDepartement: " + libDep+ "\nProfil: " + proEmp ;
    }
    public String nomEtprenom() {
        return  nomEmp + " " + prenEmp ;
    }

    public String toStringNom() {
        return nomEmp;
    }
    public String toStringDepart()
    {
        return libDep;
    }
    public String toStringProf()
    {
        return proEmp;
    }
    public String toStringPren(){return prenEmp;}
    public String toStringMail(){return mailEmp;}
    public String toStringTel(){return telEmp;}
    public String toStringNomPre(){return  prenEmp+" "+nomEmp;}

}
