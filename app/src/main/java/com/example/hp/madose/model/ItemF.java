package com.example.hp.madose.model;

/**
 * Created by erick on 24/02/2018.
 */

public class ItemF {
    private String nom,contact,adresse;

    public ItemF(String nom, String contact,  String adresse) {
        this.nom = nom;
        this.contact = contact;
        this.adresse=adresse;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
