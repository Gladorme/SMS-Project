package com.TEAM_RT.SMS_PROJECT_CLIENT;
import java.sql.Date;
public class Main {
 
    public static void main(String[] args) {
        Connexion connexion = new Connexion("C:/Users/Ahmed/Downloads/Database.db");
        connexion.connect();
        Contacts contact = new Contacts("0000000001","Test", "Ajout", 0, Date.valueOf("2018-01-02"),
                "Gagne");
        connexion.addContact(contact);
        connexion.close();
    }
}
 