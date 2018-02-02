package fr.rt.sms.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {

    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty email;
    private final StringProperty adresse;
    private final StringProperty ville;
    private final StringProperty naissance;

    public Contact() {
    	this(null,null);
    }
    
    public Contact(String nom, String prenom) {
        this.nom =  new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty("Inconnue ...");
        this.adresse = new SimpleStringProperty("Inconnue ...");
        this.ville = new SimpleStringProperty("Inconnue ...");
        this.naissance = new SimpleStringProperty("Inconnue ...");
    }

    public String getNom () {
    	return this.nom.get();
    }
    
    public void setNom (String nom) {
    	this.nom.set(nom);
    }
    public StringProperty nomProperty() {
    	return this.nom;
    }
    
    
    public String getPrenom () {
    	return this.prenom.get();
    }
    public void setPrenom (String prenom) {
    	this.prenom.set(prenom);
    }
    public StringProperty prenomProperty() {
    	return this.prenom;
    }
    
    
    public String getEmail () {
    	return this.email.get();
    }
    public void setEmail (String email) {
    	this.email.set(email);
    }
    public StringProperty emailProperty() {
    	return this.email;
    }
    
    
    public String getAdresse () {
    	return this.adresse.get();
    }
    public void setAdresse (String adresse) {
    	this.adresse.set(adresse);
    }
    public StringProperty adresseProperty() {
    	return this.adresse;
    }
    
    
    public String getVille () {
    	return this.ville.get();
    }
    public void setVille (String ville) {
    	this.ville.set(ville);
    }
    public StringProperty villeProperty() {
    	return this.ville;
    }
    
    
    public String getNaissance () {
    	return this.naissance.get();
    }
    
    public void setNaissance (String naissance) {
    	this.naissance.set(naissance);
    }
    public StringProperty naissanceProperty() {
    	return this.naissance;
    }
}
