package com.TEAM_RT.SMS_PROJECT_CLIENT;

import java.sql.Date;

public class Contacts {
    private String numero_tel;
    private String nom;
    private String prenom;
    private int categorie;
    private Date datenaissance;
    private String email;



public Contacts(String numero_tel, String nom, String prenom, int categorie, Date datenaissance, String email) {
		super();
		this.numero_tel = numero_tel;
		this.nom = nom;
		this.prenom = prenom;
		this.categorie = categorie;
		this.datenaissance = datenaissance;
		this.email = email;
	}


public String getNumero_tel() {
	return numero_tel;
}


public void setNumero_tel(String numero_tel) {
	this.numero_tel = numero_tel;
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


public int getCategorie() {
	return categorie;
}


public void setCategorie(int categorie) {
	this.categorie = categorie;
}


public Date getDatenaissance() {
	return datenaissance;
}


public void setDatenaissance(Date datenaissance) {
	this.datenaissance = datenaissance;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


}