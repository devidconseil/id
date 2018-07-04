package com.example.hp.madose.model;

/**
 * Created by erick on 07/03/2018.
 */

public class ItemU {
    private String nom,mail,adress,contact,departement,profil;
    private int Id;
    private boolean deroulante;



    public ItemU(int Id,String nom, String profil, String mail, String contact, String departement,boolean deroulante) {
        this.Id=Id;
        this.nom = nom;
        this.mail = mail;
        this.adress = adress;
        this.contact = contact;
        this.departement = departement;
        this.profil = profil;
        this.deroulante = deroulante;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
