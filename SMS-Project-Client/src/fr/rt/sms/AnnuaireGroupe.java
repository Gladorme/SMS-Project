package fr.rt.sms;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rt.sms.model.Contact;
import fr.rt.sms.model.Groupe;
import fr.rt.sms.utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnnuaireGroupe {
	private ObservableList<Groupe> groupeListe = FXCollections.observableArrayList();
	
	public AnnuaireGroupe() {		
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ResultSet groupe = connexion.query("SELECT * FROM Groupes");
        ;
        try {
			while (groupe.next()) {
				ResultSet contacts = connexion.getContacts(new Groupe(groupe.getString("nom_groupe")));
				groupeListe.add(new Groupe(groupe.getString("nom_groupe"), getContacts(contacts)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
    	connexion.close();
		
	}
	
	public AnnuaireGroupe(Contact contact) {		
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ResultSet groupe = connexion.getGroupeNonAppartenu(contact);
        ;
        try {
			while (groupe.next()) {
				groupeListe.add(new Groupe(groupe.getString("nom_groupe")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
    	connexion.close();
		
	}
	
	public ObservableList<Contact> getContacts(ResultSet contacts) {
		ObservableList<Contact> contactListe = FXCollections.observableArrayList();
        try {
			while (contacts.next()) {
				contactListe.add(new Contact(contacts.getString("nom"), contacts.getString("prenom"), contacts.getString("email"), contacts.getString("adresse"), contacts.getString("ville"), contacts.getString("naissance"), contacts.getInt("pro"), contacts.getString("tel")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        return contactListe;
	}
	
	public ObservableList<Groupe> getGroupeListe() {
		return groupeListe;
	}
}
