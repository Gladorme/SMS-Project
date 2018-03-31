package fr.rt.sms.view;

import fr.rt.sms.model.Contact;
import fr.rt.sms.model.Groupe;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GroupeAddDialogController {
	
    @FXML
    private TextField groupeField;

    private Stage dialogStage;
    private Groupe groupe;
    private boolean validerClicked = false;
    
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
        
    }
    
    public boolean isValiderClicked() {
        return validerClicked;
    }
    
    @FXML
    private void handleValider() {
        if (isInputValid()) {
        	groupe.setNom(groupeField.getText());
        	groupe.insertSQL();
        	
            validerClicked = true;
            dialogStage.close();
        }
    }
    
    @FXML
    private void handleAnnuler() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (groupeField.getText() == null || groupeField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi le nom du groupe !\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur: Inputs incorrects !");
            alert.setHeaderText("Veuillez vérifier les inputs suivants: ");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
