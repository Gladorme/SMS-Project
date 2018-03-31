package fr.rt.sms.utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Enumeration;

public class GroupeDePersonnes {
	private ArrayList<String> groupes;
	private static ArrayList<String> tabOrdre;
	public static ArrayList<ArrayList<String>> tous;

	public GroupeDePersonnes(ArrayList<String> groupes) {
		super();
		this.groupes = groupes;
	}

	public ArrayList<String> getGroupes() {
		return groupes;
	}

	public static ArrayList<String> getTabOrdre() {
		return tabOrdre;
	}

	public static void setTabOrdre(ArrayList<String> tabOrdre) {
		GroupeDePersonnes.tabOrdre = tabOrdre;
	}

	public static ArrayList<ArrayList<String>> getTous() {
		return tous;
	}

	public static void setTous(ArrayList<ArrayList<String>> tous) {
		GroupeDePersonnes.tous = tous;
	}

	public void setGroupes(ArrayList<String> groupes) {
		this.groupes = groupes;
	}

	public static Hashtable algoGraphe (ArrayList<ArrayList<String>> groupes, Hashtable dicoChemins){
		if (!groupes.isEmpty()){
		for (String unIndividuCle : groupes.get(0)){

				ArrayList<String> valeur = new ArrayList<String>();
				try{
					valeur = (ArrayList<String>)dicoChemins.get(unIndividuCle);
				}catch (Exception e){
					valeur = new ArrayList<String>();
				}
				
				if (valeur == null){
					valeur = new ArrayList<String>();
				}
				for (String unIndividu : groupes.get(0)){	
					Boolean present = false;
				
					if (!unIndividuCle.equals(unIndividu)){
						if (!valeur.isEmpty()){
							for (int i=0; i < valeur.size(); i++){
								if (unIndividu.equals(valeur.get(i))){
									present = true;
								}
							}
						}

						if (present == false){
							valeur.add(unIndividu);
						}
					}
				}
				dicoChemins.put(unIndividuCle, valeur);
			}
			groupes.remove(0);
			return algoGraphe (groupes, dicoChemins);
		}
		
		else{
			return dicoChemins;
		}
	}

	public static int[][] creationMatrice(ArrayList<ArrayList<String>> groupes) {
		Hashtable dico = new Hashtable();
		dico = algoGraphe(groupes, dico);
		Enumeration en = dico.keys();
		tabOrdre = new ArrayList<String>();

		while (en.hasMoreElements()){
			tabOrdre.add((String)en.nextElement());
		}
		int[][] matriceDeMalade=new int[tabOrdre.size()][tabOrdre.size()];

		for (int i=0; i < tabOrdre.size(); i++){
			
			for (int j=0; j < tabOrdre.size(); j++) {
				
				if (tabOrdre.get(i).equals(tabOrdre.get(j))) {
					//ici c'est 0
					matriceDeMalade[i][j] = 0;
				}else{
					//cas 2
					int valeur = 0;
					if(dico.get(tabOrdre.get(i)) != null){
						ArrayList<String> val = new ArrayList<String>();
						val = (ArrayList<String>)dico.get(tabOrdre.get(i));
						for(String uneVal : val){
							if (uneVal.equals(tabOrdre.get(j))) {
								valeur = 1;		
							}	
						}
					}
					matriceDeMalade[i][j] = valeur;
				}
			}
		}
		return matriceDeMalade;
	}
	public static int nombresommets(int matrice[][])
	{
		int i=0;
		// Pour chaque colonne
		for (int[] colonne : matrice) {
			i++;
		}
		return i;
	}
}

