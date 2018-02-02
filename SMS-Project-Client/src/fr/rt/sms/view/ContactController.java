package fr.rt.sms.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import fr.rt.sms.Annuaire;
import fr.rt.sms.model.Contact;

public class ContactController {
	
	@SuppressWarnings("unused")
	private Annuaire Annuaire;
	@FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, String> nomColumn;
    @FXML
    private TableColumn<Contact, String> prenomColumn;

    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label naissanceLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label villeLabel;

    	
    public ContactController(){
    	
    }
	@FXML
	private void initialize() {
		nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
		
		showContactDetails(null);
		
		contactTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showContactDetails(newValue));
	}
    public void setAnnuaire(Annuaire Annuaire) {
        this.Annuaire = Annuaire;
        contactTable.setItems(Annuaire.getContactData());
    }
    private void showContactDetails(Contact contact){
    	if (contact != null) {
    		nomLabel.setText(contact.getNom());
    		prenomLabel.setText(contact.getPrenom());
    		naissanceLabel.setText(contact.getNaissance());
    		emailLabel.setText(contact.getEmail());
    		adresseLabel.setText(contact.getAdresse());
    		villeLabel.setText(contact.getVille());
    	}else {
    		nomLabel.setText("");
    		prenomLabel.setText("");
    		naissanceLabel.setText("");
    		emailLabel.setText("");
    		adresseLabel.setText("");
    		villeLabel.setText("");
    	}
    }
}
