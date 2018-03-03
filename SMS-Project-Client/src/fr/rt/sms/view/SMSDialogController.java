package fr.rt.sms.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SMSDialogController {

    @FXML
    private TextArea contenuArea;

    private Stage dialogStage;
    private boolean validerClicked = false;
    
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isValiderClicked() {
        return validerClicked;
    }
   
    @FXML
    private void handleValider() {
        if (isInputValid()) {

        	//sms.insertSQL();
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

        if (contenuArea.getText() == null || contenuArea.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi le contenu du message !\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Erreur: Inputs incorrects !");
            alert.setHeaderText("Veuillez vérifier les informations suivantes: ");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
	
}
