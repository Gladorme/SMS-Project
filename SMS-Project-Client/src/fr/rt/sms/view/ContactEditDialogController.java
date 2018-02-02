package fr.rt.sms.view;

import fr.rt.sms.model.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContactEditDialogController {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField naissanceField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField villeField;
	
    private Stage dialogStage;
    private Contact contact;
    private boolean validerClicked = false;
    
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setContact(Contact contact) {
        this.contact = contact;

        nomField.setText(contact.getNom());
        prenomField.setText(contact.getPrenom());
        naissanceField.setText(contact.getNaissance());
        emailField.setText(contact.getEmail());
        adresseField.setText(contact.getAdresse());
        villeField.setText(contact.getVille());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isValiderClicked() {
        return validerClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleValider() {
        if (isInputValid()) {
        	contact.setNom(nomField.getText());
        	contact.setPrenom(prenomField.getText());
        	contact.setNaissance(naissanceField.getText());
        	contact.setEmail(emailField.getText());
        	contact.setAdresse(adresseField.getText());
        	contact.setVille(villeField.getText());

            validerClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleAnnuler() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomField.getText() == null || nomField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi le nom !\n";
        }
        if (prenomField.getText() == null || prenomField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi le prénom !\n";
        }
        if (naissanceField.getText() == null || naissanceField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi la date de naissance !\n";
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi l'email !\n";
        }
        if (adresseField.getText() == null || adresseField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi l'adresse !\n";
        }
        if (villeField.getText() == null || villeField.getText().length() == 0) {
            errorMessage += "Vous n'avez pas saissi la ville !\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
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
