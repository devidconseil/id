package com.example.hp.madose;

/**
 * Created by HP on 16/05/2018.
 */

public class HeureDemandeC {
String nom,prenom,besoin,heure,hour,numero,mail;
int qte;

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

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBesoin() {
        return besoin;
    }

    public void setBesoin(String besoin) {
        this.besoin = besoin;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    public HeureDemandeC(){}
    public HeureDemandeC(String nom,String prenom, String besoin, String heure, String hour){
        this.nom=nom;
        this.prenom=prenom;
        this.besoin=besoin;
        this.heure=heure;
        this.hour=hour;
    }
    public HeureDemandeC(String nom,String prenom, String besoin, String heure, String hour,int qte){
        this.nom=nom;
        this.prenom=prenom;
        this.besoin=besoin;
        this.heure=heure;
        this.hour=hour;
        this.qte=qte;
    }
    public HeureDemandeC(String nom,String prenom, String besoin, String heure){
        this.nom=nom;
        this.prenom=prenom;
        this.besoin=besoin;
        this.heure=heure;

    }
    public HeureDemandeC(String nom,String prenom, String besoin, String heure,int qte){
        this.nom=nom;
        this.prenom=prenom;
        this.besoin=besoin;
        this.heure=heure;
        this.qte=qte;

    }
    public HeureDemandeC(String nom,String prenom, String besoin, String hour,int qte,String numero,String mail){
        this.nom=nom;
        this.prenom=prenom;
        this.besoin=besoin;
        this.hour=hour;
        this.qte=qte;
        this.numero=numero;
        this.mail=mail;

    }
}
