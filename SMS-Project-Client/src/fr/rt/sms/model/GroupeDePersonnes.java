package fr.rt.sms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Enumeration;

public class GroupeDePersonnes {
	private ArrayList<String> groupes;

	public GroupeDePersonnes(ArrayList<String> groupes) {
		super();
		this.groupes = groupes;
	}

	public ArrayList<String> getGroupes() {
		return groupes;
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
		/*ArrayList<String> grp_a_b_c=new ArrayList<String>();
		ArrayList<String> grp_d_e_b=new ArrayList<String>();
		ArrayList<String> grp_c_a_d=new ArrayList<String>();
		ArrayList<String> grp_d_k=new ArrayList<String>();
		
		grp_a_b_c.add("a");
		grp_a_b_c.add("b");
		grp_a_b_c.add("c");
		
		grp_d_e_b.add("d");
		grp_d_e_b.add("e");
		grp_d_e_b.add("b");
		
		grp_c_a_d.add("c");
		grp_c_a_d.add("a");
		grp_c_a_d.add("d");
		
		grp_d_k.add("d");
		grp_d_k.add("k");
		
		ArrayList<ArrayList<String>> tous= new ArrayList<ArrayList<String>> ();
		
		tous.add(grp_a_b_c);
		tous.add(grp_d_e_b);
		tous.add(grp_c_a_d);
		tous.add(grp_d_k);
		
		System.out.println(tous);
		*/
		Hashtable dico = new Hashtable();
		dico = algoGraphe(groupes, dico);
		
		Enumeration en = dico.keys();
		ArrayList<String> tabOrdre = new ArrayList<String>();

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
		System.out.println("------------");
		return matriceDeMalade;
		/*
		for (int y = 0; y < matriceDeMalade.length; ++y) {
	        for (int x = 0; x < matriceDeMalade[y].length; ++x) {
	            System.out.print(matriceDeMalade[y][x]);
	        }
	        System.out.println();
	    }
	    */
	}
}

