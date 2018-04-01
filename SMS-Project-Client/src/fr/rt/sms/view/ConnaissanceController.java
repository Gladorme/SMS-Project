package fr.rt.sms.view;
import javafx.fxml.FXML;
import com.google.common.*;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import fr.rt.sms.model.Contact;
import fr.rt.sms.model.SMS;
import fr.rt.sms.utils.Algo;
import fr.rt.sms.utils.Connexion;
import fr.rt.sms.model.GroupeDePersonnes;

import java.util.regex.Pattern;
public class ConnaissanceController {
    @FXML
    private Label tel_srcLabel;
    @FXML
    private Label tel_contact;
    @FXML
    private Label connaissancespotentielles;

    
	   @FXML
	    private void initialize()  {
		   tel_srcLabel.setText(SMS.tel_src);
		   tel_contact.setText(affichercontactpropre());
		  connaissancespotentielles.setText(connaissancepotentielles());
		   		}
	   public static HashMap<String,String> recupcontacts(String telSrc) {
				Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
		        connexion.connect();
			    HashMap<String, String> tableau =new HashMap<>();
		        ResultSet contact = connexion.query("SELECT * FROM Contacts");
		        try {
		            while (contact.next()) {
		            	if(!(contact.getString("tel").equals(telSrc)))
		            	{  //pas inclure le numero courant dans la liste des contacts
		            	if(!(contact.getString("tel").equals("inconnu_aldric"))) {
		                tableau.put(contact.getString("nom")+" "+contact.getString("prenom"),contact.getString("tel"));
		            																}
					}
		        }
		        }
				 catch (Exception e) {
					e.printStackTrace();
				 }
		        System.out.println();
		    	connexion.close();
		    	return tableau;
				
			}
	   public static  Multimap<String, String> recup_groupes() {
				Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
		        connexion.connect();
		        ArrayList<ArrayList<String>> tableau =new  ArrayList<ArrayList<String>>();
		        ResultSet nom_groupes = connexion.query("SELECT nom_groupe FROM Groupes");
		        ResultSet contact = connexion.query("SELECT DISTINCT nom,prenom,nom_groupe FROM CONTACTS,GROUPES,APPARTENANCES WHERE id_groupe=groupe_id AND contact_tel= tel ORDER BY Groupes.nom_groupe");
		        Multimap<String, String> alpha= ArrayListMultimap.create();
					try {
						if(nom_groupes.getString("nom_groupe").equals(contact.getString("nom_groupe"))) {
					    	alpha.put(nom_groupes.getString("nom_groupe"), contact.getString("nom")+" "+contact.getString("prenom"));
						}
						while(contact.next()){
							while(nom_groupes.next()) {
						if(nom_groupes.getString("nom_groupe").equals(contact.getString("nom_groupe"))) {
					    	alpha.put(nom_groupes.getString("nom_groupe"), contact.getString("nom")+" "+contact.getString("prenom"));
											}
												}							
												}
		        	}
			 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return alpha;
				
			}

	   public static String  affichercontactpropre() {
		   String resultat="";
		   for (Map.Entry<String, String> entree : recupcontacts(SMS.tel_src).entrySet()) {
			   resultat=resultat+"\n"+entree.getKey();
		}
		   return resultat;
	   }


	  public static String connaissancepotentielles(){
		  
		   Multimap<String,String> recup_groupe= recup_groupes();
		   
		   ArrayList<ArrayList<String>> valeurs= new ArrayList<ArrayList<String>>();

		   for (Collection collection : recup_groupe.asMap().values()) { 
		   
			   ArrayList<String> var = new ArrayList<>();
			   for (Object object : collection) {
				   var.add(String.valueOf(object));
				   valeurs.add(var);
				   }
		   }
		   int[][] graphe = GroupeDePersonnes.creationMatrice(valeurs);
		   int nombre_sommets= GroupeDePersonnes.nombresommets(graphe);
		   
		   Algo t = new Algo();
		    HashMap<String, Integer> lien_nom_numeros =new HashMap<>();
		     int cpt=0;
			for (String string : GroupeDePersonnes.getTabOrdre()) {
				lien_nom_numeros.put(string,cpt);
				cpt++;
			}
			System.out.println(lien_nom_numeros);
		   Vector<String> res =t.dijkstra(graphe, nombre_sommets, GroupeDePersonnes.getTabOrdre(),lien_nom_numeros.get("Ladorme Guillaume"));  
		   HashMap<String,String> a2=ConnaissanceController.recupcontacts(SMS.tel_src);
		   String resultat=new String();
		  resultat="";
		   HashMap<String, String> connaissancepotentielles= new HashMap<>();
		   connaissancepotentielles.put("Guillaume Ladorme","0645892662");
		   connaissancepotentielles.put("Ducreux Aldric","0645892985");
		   connaissancepotentielles.put("Aboukora Ahmed","0645892662");

		   for (String string : res) {
			if(connaissancepotentielles.containsKey(string) && a2.get(string)==null){ //si on connait pas cette personne et quelle est dans la liste des connaissances potentielles
				resultat=resultat+string+" au "+connaissancepotentielles.get(string)+"\n";
			}
		}
		
		return resultat;
	   }
	   
public static void main(String args[]) {
	System.out.println(connaissancepotentielles());

								}


}



	   
