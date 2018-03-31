package fr.rt.sms.view;

import fr.rt.sms.AnnuaireGroupe;
import fr.rt.sms.model.Contact;
import fr.rt.sms.model.Groupe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AppartenanceAddController {

	private AnnuaireGroupe annuaireGroupe;
	@FXML
    private TableView<Groupe> groupeTable;
    @FXML
    private TableColumn<Groupe, String> groupeColumn;
    
    private Contact contact;
    private Stage dialogStage;
    private boolean validerClicked = false;
	
    
	@FXML
	private void initialize() {
		groupeColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
	}
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setAnnuaireGroupe(AnnuaireGroupe annuaireGroupe) {
        this.annuaireGroupe = annuaireGroupe;
        groupeTable.setItems(annuaireGroupe.getGroupeListe());
    }
    public void setContact(Contact contact) {
    	this.contact = contact;
    }
    public boolean isValiderClicked() {
        return validerClicked;
    }
    
    @FXML
    private void handleValider() {
    	Groupe selectedGroupe = groupeTable.getSelectionModel().getSelectedItem();
        if (selectedGroupe != null) {
        	selectedGroupe.addAppartenance(contact);
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur: Vous n'avez pas sélectionné de groupe");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez séléctionner un groupe pour rajouter le contact dedans");

            alert.showAndWait();
        }
	    validerClicked = true;
	    dialogStage.close();
    }
    
}
