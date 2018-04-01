package fr.rt.sms.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rt.sms.utils.Connexion;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Groupe {
    private final StringProperty nom;
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    
    public Groupe () {
    	this(null);
    }
    public Groupe (String nom) {
    	this.nom = new SimpleStringProperty(nom);
    }
    public Groupe (String nom, ObservableList<Contact> contacts) {
    	this.nom = new SimpleStringProperty(nom);
    	this.contacts = contacts;
    }
    
    
    
    public String getNom () {
    	return this.nom.get();
    }
    public void setNom (String nom) {
    	this.nom.set(nom);
    }
    public StringProperty nomProperty () {
    	return this.nom;
    }
    
    public int getId () {
    	Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        int id = connexion.getId(this);
        connexion.close();
        
        return id;
    }
    
    
    public ObservableList<Contact> getContacts () {
    	return this.contacts;
    }
    public void setContacts (ObservableList<Contact> contacts) {
    	this.contacts = contacts;
    }
    public void addContact (Contact contact) {
    	this.contacts.add(contact);
    }
    
    public void insertSQL() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.addGroupe(this);
        connexion.close();
    }
    public void deleteSQL() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.deleteGroupe(this);
        connexion.close();
    }
    public void addAppartenance(Contact contact) {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.addAppartenance(this, contact);
        connexion.close();
    }
    
}
