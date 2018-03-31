package fr.rt.sms;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rt.sms.model.Contact;
import fr.rt.sms.utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Annuaire {
	private ObservableList<Contact> contactListe = FXCollections.observableArrayList();
	
	public Annuaire() {
		//contactData.add(new Contact("Ladorme", "Guillaume", "guillaume.ladorme@etu.unice.fr", "325 chemin de l'IUT", "Valbonne", "21/02/1998", 0, "0682209302"));
		
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ResultSet contact = connexion.query("SELECT * FROM Contacts");
        try {
			while (contact.next()) {
				contactListe.add(new Contact(contact.getString("nom"), contact.getString("prenom"), contact.getString("email"), contact.getString("adresse"), contact.getString("ville"), contact.getString("naissance"), contact.getInt("pro"), contact.getString("tel")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
        
    	connexion.close();
		
	}
	public Annuaire(String groupe) {
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ResultSet contact = connexion.getContacts(groupe);
        try {
			while (contact.next()) {
				contactListe.add(new Contact(contact.getString("nom"), contact.getString("prenom"), contact.getString("email"), contact.getString("adresse"), contact.getString("ville"), contact.getString("naissance"), contact.getInt("pro"), contact.getString("tel")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
        
    	connexion.close();
	}
	public ObservableList<Contact> getContactData() {
		return contactListe;
	}
}
