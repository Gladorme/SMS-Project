package fr.rt.sms;

import java.io.IOException;

import fr.rt.sms.model.Contact;
import fr.rt.sms.view.ContactAddDialogController;
import fr.rt.sms.view.ContactController;
import fr.rt.sms.view.ContactEditDialogController;
import fr.rt.sms.view.HistoriqueController;
import fr.rt.sms.view.LoginController;
import fr.rt.sms.view.SMSDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static Stage primaryStage;
	private static BorderPane nav;
	
	@Override
	public void start(Stage primaryStage) {
		MainApp.primaryStage = primaryStage;
		MainApp.primaryStage.setTitle("SMS-Project");
		showLogin();
	}
	
	public static void initNav() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Nav.fxml"));
            nav = (BorderPane) loader.load();

            Scene scene = new Scene(nav);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void showLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Login.fxml"));
            AnchorPane Login = (AnchorPane) loader.load();
            
            Scene scene = new Scene(Login);
            primaryStage.setScene(scene);
            primaryStage.show();
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void showAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Accueil.fxml"));
            AnchorPane accueil = (AnchorPane) loader.load();

            nav.setCenter(accueil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void showAnnuaire() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Annuaire.fxml"));
            AnchorPane annuaire = (AnchorPane) loader.load();

            nav.setCenter(annuaire);
            
            ContactController controller = loader.getController();
            Annuaire Annuaire = new Annuaire();
            controller.setAnnuaire(Annuaire);
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void showMessagerie() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Messagerie.fxml"));
            AnchorPane messagerie = (AnchorPane) loader.load();

            nav.setCenter(messagerie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void showHistorique() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Historique.fxml"));
            AnchorPane historique = (AnchorPane) loader.load();
            
	        HistoriqueController controller = loader.getController();

            nav.setCenter(historique);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static boolean showContactEditDialog(Contact contact) {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ContactEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Editer un contact");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        ContactEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setContact(contact);

	        dialogStage.showAndWait();

	        return controller.isValiderClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static boolean showContactAddDialog(Contact contact) {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ContactAddDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Rajouter un contact");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        ContactAddDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setContact(contact);

	        dialogStage.showAndWait();

	        return controller.isValiderClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public static boolean showSMSDialog(Contact contact) {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/SMSDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Envoyer un SMS à " + contact.getPrenom() + " " + contact.getNom());
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        SMSDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setContact(contact);

	        dialogStage.showAndWait();

	        return controller.isValiderClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
