package fr.rt.sms;

import fr.rt.sms.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Annuaire {
	private ObservableList<Contact> contactData = FXCollections.observableArrayList();
	
	public Annuaire() {
		contactData.add(new Contact("Ladorme", "Guillaume"));
		contactData.add(new Contact("Ducreux", "Aldric"));
		contactData.add(new Contact("Aboukora", "Ahmed"));
		contactData.add(new Contact("Mendes", "Danny"));
		contactData.add(new Contact("Choquard", "Thomas"));
	}
	public ObservableList<Contact> getContactData() {
		return contactData;
	}
}
