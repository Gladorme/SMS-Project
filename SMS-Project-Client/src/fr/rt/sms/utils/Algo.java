package fr.rt.sms.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Algo {
	 int minDistance(int dist[], Boolean pluscourtchemin[], int nombre_sommets)
	 {
	     // Initialiser la distance minimale
	     int min = Integer.MAX_VALUE, min_index=-1;

	     for (int cpt = 0; cpt < nombre_sommets; cpt++)
	         if (pluscourtchemin[cpt] == false && dist[cpt] <= min)
	         {
	             min = dist[cpt];
	             min_index = cpt;
	         }

	     return min_index;
	 }


	 public Vector<String> dijkstra(int graphe[][],int nombre_sommets, ArrayList<String> personnes, int sommet_client)
	 {
	     int dist[] = new int[nombre_sommets]; // The output array. dist[i] will hold
	                              // the shortest distance from src to i 
	     //dist est le tableau_de_sortie, il va garder en memoire la valeur de la distance la plus petite 
	     //entre le sommet du client actuel et les autres
	     // pluspluscourtchemin[i] si le sommet i est inclus dans l'arbre de plus court chemin
	     Boolean pluscourtchemin[] = new Boolean[nombre_sommets];

	     // On va supposer que les distances et que les autres sommets sont eloignés du clients d'un distance infinie 
	     // On va supposser qu'ils ne sont pas inclus dans l'arbre de plus court chemin
	     for (int i = 0; i < nombre_sommets; i++)
	     {
	         dist[i] = Integer.MAX_VALUE;
	         pluscourtchemin[i] = false;
	     }

	     // Distance d'un sommet a lui meme
	     dist[sommet_client] = 0;

	     // Trouve le chemin plus court sur la liste des sommets
	     for (int compteur = 0; compteur < nombre_sommets-1; compteur++)
	     {
	        /* Prend la distance minimale de tous les sommets pas encore traités, distance sera toujours egal au sommetclient lors de la 1ere itération */
	         int distance_min = minDistance(dist, pluscourtchemin, nombre_sommets);
	         // marque le sommet en cours somme traité , pas besoin de revenir desus et permet d'éviter de boucler
	         pluscourtchemin[distance_min] = true;

	         //La boucle suivante va mettre a jour la valeur de la distance du sommet en cours de traitement
	         for (int compteur2 = 0; compteur2 < nombre_sommets; compteur2++)

	        	 //MAJ de la distance d'un sommet si un chemin trouvé n'est pas dans le plus court chemin
	        	 //comme ça on s'assure d'avoir la valeur la plus petite

	             if (!pluscourtchemin[compteur2] && graphe[distance_min][compteur2]!=0 &&
	                    dist[distance_min] != Integer.MAX_VALUE &&
	                     dist[distance_min]+graphe[distance_min][compteur2] < dist[compteur2])
	                 dist[compteur2] = dist[distance_min] + graphe[distance_min][compteur2];
	     }

	     // Construction du tableau recapitulatif
	     HashMap<Integer, String> lien_nom_numeros =new HashMap<>();
	     int cpt=0;
		for (String string : personnes) {
			lien_nom_numeros.put(cpt,string);
			cpt++;
		}
	     return afficherrelations(dist, lien_nom_numeros, nombre_sommets);
	 }
	 // Une fonction qui affiche les sommets et leur distance   
	 Vector<String> afficherrelations(int dist[], HashMap tab,int nombre_sommets)
	 {
		 Vector tableau = new Vector<String>();
	     for (int i = 0; i < nombre_sommets; i++)
	    	if(tab.keySet() != null)
	    	 if (dist[i]>=2 ) { // si A et B sont séparés par le serveur alors ils se connaissent mais on peut augmenter la portée
	         tableau.add(tab.get(i));
	    	 }
     return tableau;
	 }

public static void main(String args[]) {

}
}