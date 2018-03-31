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
        ResultSet id = connexion.query("SELECT * FROM Groupe WHERE nom =" + this.getNom());
        connexion.close();
        
        try {
			return id.getInt("id_groupe");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
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
    
}