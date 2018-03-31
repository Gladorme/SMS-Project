package fr.rt.sms.view;

import fr.rt.sms.MainApp;

import javafx.fxml.FXML;

public class NavController {

    @FXML
    private void initialize() {
    }
    
    @FXML
    private void handleAccueil() {
        MainApp.showAccueil();
    }
    @FXML
    private void handleAnnuaire() {
        MainApp.showAnnuaire();
    }
    @FXML
    private void handleGroupe() {
        MainApp.showGroupe();
    }
    @FXML
    private void handleHistorique() {
        MainApp.showHistorique();
    }
    @FXML
    private void handleConnassance() {
        MainApp.showConnaissance();
        }
    
	
}
