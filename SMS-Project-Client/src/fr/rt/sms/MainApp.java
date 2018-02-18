package fr.rt.sms;

import java.io.IOException;

import fr.rt.sms.model.Contact;
import fr.rt.sms.view.ContactController;
import fr.rt.sms.view.ContactEditDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
		
		initNav();
		
		showAnnuaire();
	}
	
	public void initNav() {
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
            
            ContactController controller = loader.getController();
            Annuaire Annuaire = new Annuaire();
            controller.setAnnuaire(Annuaire);
            
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public static void showAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Accueil.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            nav.setCenter(personOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void showAnnuaire() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Annuaire.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            nav.setCenter(personOverview);
            
            ContactController controller = loader.getController();
            Annuaire Annuaire = new Annuaire();
            controller.setAnnuaire(Annuaire);
            
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
	        dialogStage.setTitle("Edit Person");
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
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
