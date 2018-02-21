package fr.rt.sms.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import fr.rt.sms.Annuaire;
import fr.rt.sms.MainApp;
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
    @FXML
    private Label proLabel;
    @FXML
    private Label telLabel;
    

    	
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
    		proLabel.setText(contact.getProString());
    		telLabel.setText(contact.getTel());
    	}else {
    		nomLabel.setText("");
    		prenomLabel.setText("");
    		naissanceLabel.setText("");
    		emailLabel.setText("");
    		adresseLabel.setText("");
    		villeLabel.setText("");
    		proLabel.setText("");
    		telLabel.setText("");
    	}
    }
    
    @FXML
    private void handleDeleteContact() {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	Contact selectedPerson = contactTable.getSelectionModel().getSelectedItem();
        	contactTable.getItems().remove(selectedIndex);
        	selectedPerson.deleteSQL();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur: Vous n'avez pas sélectionné de contact");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez séléctionner un contact pour le supprimer");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewContact() {
        Contact tempContact = new Contact();
        boolean validerClicked = MainApp.showContactAddDialog(tempContact);
        if (validerClicked) {
        	Annuaire.getContactData().add(tempContact);
        }
    }

    @FXML
    private void handleEditContact() {
    	Contact selectedPerson = contactTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean validerClicked = MainApp.showContactEditDialog(selectedPerson);
            if (validerClicked) {
                showContactDetails(selectedPerson);
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(MainApp.getPrimaryStage());
            alert.setTitle("Erreur: Vous n'avez pas sélectionné de contact");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez séléctionner un contact pour l'éditer");

            alert.showAndWait();
        }
    }
}
