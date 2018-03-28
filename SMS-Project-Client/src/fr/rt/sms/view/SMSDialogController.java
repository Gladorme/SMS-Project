package fr.rt.sms.view;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Base64;

import fr.rt.sms.model.Contact;
import fr.rt.sms.model.SMS;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class SMSDialogController {

    @FXML
    private TextArea contenuArea;
    @FXML
    private CheckBox chiffrement;

    private Contact contact;
    private Stage dialogStage;
    private boolean validerClicked = false;
    
    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public boolean isValiderClicked() {
        return validerClicked;
    }
   
    @FXML
    private void handleValider() {
    	String contenuBase64;
        if (isInputValid()) {
        	SMS sms = new SMS();
        	sms.setTel_dest(contact.getTel());
        	if (chiffrement.isSelected()) {
        		sms.setChiffrement(1);
        		contenuBase64 = Base64.getEncoder().encodeToString(contenuArea.getText().getBytes());
        		sms.setContenu(contenuBase64);
        	} else {
        		sms.setChiffrement(0);
        		sms.setContenu(contenuArea.getText());
        	}
        	try {
				sms.sendSMS();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	sms.insertSQL();
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
