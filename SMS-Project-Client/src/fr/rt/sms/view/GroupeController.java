package fr.rt.sms.view;

import fr.rt.sms.Annuaire;
import fr.rt.sms.AnnuaireGroupe;
import fr.rt.sms.model.Contact;
import fr.rt.sms.model.Groupe;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
	
}
