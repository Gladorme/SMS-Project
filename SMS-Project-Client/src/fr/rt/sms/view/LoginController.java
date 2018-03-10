package fr.rt.sms.view;

import fr.rt.sms.model.SMS;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private TextField tel;
	
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
	    
	    public boolean isConnexionClicked() {
	        return connexionClicked;
	    }
	
}
