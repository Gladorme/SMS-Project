package fr.rt.sms.view;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rt.sms.model.Contact;
import fr.rt.sms.model.SMS;
import fr.rt.sms.utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class HistoriqueController {

	@FXML
	private ListView<String> listViewSMS = new ListView<>();
	
	
	
    private Stage dialogStage;
    
    
    @FXML
    private void initialize() {
    	
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ObservableList<String> smsListe = FXCollections.observableArrayList();
        ResultSet sms = connexion.query("SELECT * FROM SMS ORDER BY id_sms desc");
        try {
			while (sms.next()) {
				String chiffrement;
				if (sms.getInt("chiffrement") == 1) {
					chiffrement = "Oui";
				} else {
					chiffrement = "Non";
				}
				smsListe.add(
						"Destinataire: " + sms.getString("tel_dest") + "          -         Chiffré: " + chiffrement + "\n"
				      + "Contenu :" + "\n" + sms.getString("contenu") + "\n "
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
        
    	connexion.close();
    	
    	/*ObservableList<String> items = FXCollections.observableArrayList (
    	    "Destinataire: " + "0682209302" + "          -         Chiffré: " + "Non" + "\n"
    	  + "Contenu :" + "\n" + "Je te rappel que tu as un rapport à faire dans les prochains jours, n'oublie pas de me confirmer la date. Ceci était un test ! A bientot :D Je sais pas quoi marquer, aide moi ou pas, qfzefzef ezfzef zefgdz zefezfzfz zefz zefzefzefezf" + "\n ",
    	  "Destinataire: " + "0606060606" + "          -         Chiffré: " + "Oui" + "\n"
    	  + "Contenu :" + "\n" + "ça marche bien, nàn ?" + "\n ",
    	  "Destinataire: " + "0505050505" + "          -         Chiffré: " + "J'sais pas" + "\n"
    	    	  + "Contenu :" + "\n" + "je pense que Guillaume à fait un client du futur" + "\n "
    			);*/
    	listViewSMS.setItems(smsListe);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
}
