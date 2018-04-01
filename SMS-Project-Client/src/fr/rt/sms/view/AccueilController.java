package fr.rt.sms.view;

import fr.rt.sms.model.SMS;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    private Label nbrMessagesLabel;
    @FXML
    private Label tel_srcLabel;
    
    private Stage dialogStage;
    
    
	   @FXML
	    private void initialize() {
		   tel_srcLabel.setText(SMS.tel_src);
		   nbrMessagesLabel.setText(String.valueOf(SMS.getMessageEnvoye()));
	    }

	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
}
