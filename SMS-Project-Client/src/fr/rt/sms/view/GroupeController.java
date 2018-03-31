package fr.rt.sms.view;

import fr.rt.sms.Annuaire;
import fr.rt.sms.AnnuaireGroupe;
import fr.rt.sms.MainApp;
import fr.rt.sms.model.Contact;
import fr.rt.sms.model.Groupe;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class GroupeController {
	
	private Annuaire annuaire;
	private AnnuaireGroupe annuaireGroupe;
	@FXML
    private TableView<Groupe> groupeTable;
    @FXML
    private TableColumn<Groupe, String> groupeColumn;
	@FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, String> nomColumn;
    @FXML
    private TableColumn<Contact, String> prenomColumn;

	@FXML
	private void initialize() {
		groupeColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
		showContactDetails(null);

		groupeTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showContactDetails(newValue));
	}
	
    public void setAnnuaireGroupe(AnnuaireGroupe annuaireGroupe) {
        this.annuaireGroupe = annuaireGroupe;
        groupeTable.setItems(annuaireGroupe.getGroupeListe());
    }
    
    private void showContactDetails(Groupe groupe){
    	if (groupe != null) {
            this.annuaire = new Annuaire(groupe.getNom());
            contactTable.setItems(annuaire.getContactData());
            
    		nomColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
    		prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
    	}else {

    	}
    }
    
    @FXML
    private void handleDeleteGroupe() {
        int selectedIndex = groupeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	Groupe selectedGroupe = groupeTable.getSelectionModel().getSelectedItem();
        	groupeTable.getItems().remove(selectedIndex);
        	selectedGroupe.deleteSQL();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur: Vous n'avez pas sélectionné de groupe");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez séléctionner un groupe pour le supprimer");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleNewGroupe() {
    	Groupe groupe = new Groupe();
        boolean validerClicked = MainApp.showGroupeAddDialog(groupe);
        if (validerClicked) {
        	annuaireGroupe.getGroupeListe().add(groupe);
        }
    }
    
    @FXML
    private void handleSMS() {
    	Groupe selectedGroupe = groupeTable.getSelectionModel().getSelectedItem();
        if (selectedGroupe != null) {
            MainApp.showSMSDialog(selectedGroupe);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(MainApp.getPrimaryStage());
            alert.setTitle("Erreur: Vous n'avez pas sélectionné de contact");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez séléctionner un contact pour lui envoyer un SMS");

            alert.showAndWait();
        }
    }
	
}
