package fr.rt.sms;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rt.sms.model.Groupe;
import fr.rt.sms.utils.Connexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnnuaireGroupe {
	private ObservableList<Groupe> groupeListe = FXCollections.observableArrayList();
	
	public AnnuaireGroupe() {
		//contactData.add(new Contact("Ladorme", "Guillaume", "guillaume.ladorme@etu.unice.fr", "325 chemin de l'IUT", "Valbonne", "21/02/1998", 0, "0682209302"));
		
		Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();

        ResultSet groupe = connexion.query("SELECT * FROM Groupes");
        try {
			while (groupe.next()) {
				groupeListe.add(new Groupe(groupe.getString("nom_groupe")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
        
        
    	connexion.close();
		
	}
	public ObservableList<Groupe> getGroupeListe() {
		return groupeListe;
	}
}
