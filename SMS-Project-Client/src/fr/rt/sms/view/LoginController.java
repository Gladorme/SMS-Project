package fr.rt.sms.view;

import fr.rt.sms.MainApp;
import fr.rt.sms.model.SMS;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField telInput;
	
    private Stage dialogStage;
    private boolean connexionClicked = false;


	

	   @FXML
	    private void initialize() {
	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	    public void setTel_src(String tel_src) {
	        SMS.setTel_src(tel_src);
	    }
	    
	    @FXML
	    private void handleValider() {
	        if (isInputValid()) {
	        	SMS.setTel_src(telInput.getText());
	        	MainApp.initNav();
	    		MainApp.showAccueil();
	        }
	    }
	    
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (telInput.getText() == null || telInput.getText().length() != 10) {
	            errorMessage += "Mauvais numéro de téléphone !\n";
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
	    
	    
	    public boolean isConnexionClicked() {
	        return connexionClicked;
	    }
	
}
