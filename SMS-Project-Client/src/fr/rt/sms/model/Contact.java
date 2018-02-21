package fr.rt.sms.model;

import fr.rt.sms.utils.Connexion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {

    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty email;
    private final StringProperty adresse;
    private final StringProperty ville;
    private final StringProperty naissance;
    private final IntegerProperty pro;
    private final StringProperty tel;

    public Contact () {
    	this(null,null, null, null, null, null, 0, null);
    }
    
    public Contact (String nom, String prenom, String email, String adresse, String ville, String naissance, int pro, String tel) {
        this.nom =  new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.email = new SimpleStringProperty(email);
        this.adresse = new SimpleStringProperty(adresse);
        this.ville = new SimpleStringProperty(ville);
        this.naissance = new SimpleStringProperty(naissance);
        this.pro = new SimpleIntegerProperty(pro);
        this.tel = new SimpleStringProperty(tel);
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
    
    
    public int getPro () {
    	return this.pro.get();
    }
    public String getProString () {
    	if (this.pro.get() == 1) {
    		return "Oui";
    	}else {
    		return "Non";
    	}
    }
    
    public void setPro (int pro) {
    	this.pro.set(pro);
    }
    public IntegerProperty proProperty() {
    	return this.pro;
    }
    
    
    public String getTel () {
    	return this.tel.get();
    }
    
    public void setTel (String tel) {
    	this.tel.set(tel);
    }
    public StringProperty telProperty() {
    	return this.tel;
    }
    public void insertSQL() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.addContact(this);
        connexion.close();
    }
    public void updateSQL(String tel) {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.changeContact(this, tel);
        connexion.close();
    }
    public void deleteSQL() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.deleteContact(this);
        connexion.close();
    }
}
