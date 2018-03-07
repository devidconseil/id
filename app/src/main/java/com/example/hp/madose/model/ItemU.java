package com.example.hp.madose.model;

/**
 * Created by erick on 07/03/2018.
 */

public class ItemU {
    private String nom,prenom,mail,adress,contact,departement,profil;
    private boolean deroulante;



    public ItemU(String nom, String prenom, String mail, String adress, String contact, String departement, String profil,boolean deroulante) {
        this.nom = nom;

        this.prenom = prenom;
        this.mail = mail;
        this.adress = adress;
        this.contact = contact;
        this.departement = departement;
        this.profil = profil;
        this.deroulante = deroulante;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public boolean isDeroulante() {
        return deroulante;
    }

    public void setDeroulante(boolean deroulante) {
        this.deroulante = deroulante;
    }
}
