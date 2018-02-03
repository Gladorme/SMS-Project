package fr.rt.sms.utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SQLite {

    public static void main(String[] args) {
    	Connexion connexion = new Connexion("Database.db");
        connexion.connect();

    	/*SMS texto1 = new SMS("J'aime les raclettes","0123456789","0222222222",dateString);
    	texto1.envoiSMS();
    	connexion.addentreehistorique(texto1);*/
    	connexion.close();
	}
	
}
