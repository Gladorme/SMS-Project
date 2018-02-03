package fr.rt.sms;

import java.sql.ResultSet;

import fr.rt.sms.model.Contact;
import fr.rt.sms.utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Annuaire {
	private ObservableList<Contact> contactData = FXCollections.observableArrayList();
	
	public Annuaire() {
		contactData.add(new Contact("Ladorme", "Guillaume", "guillaume.ladorme@etu.unice.fr", "325 chemin de l'IUT", "Valbonne", "21/02/1998", 0, "0682209302"));
		//contactData.add(new Contact("Ducreux", "Aldric"));
		//contactData.add(new Contact("Aboukora", "Ahmed"));
		//contactData.add(new Contact("Mendes", "Danny"));
		//contactData.add(new Contact("Choquard", "Thomas"));
		
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ResultSet contact = connexion.query("SELECT * FROM Contacts");
        System.out.println(contact);      
        
        
    	connexion.close();
		
	}
	public ObservableList<Contact> getContactData() {
		return contactData;
	}
}
