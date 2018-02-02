package com.TEAM_RT.SMS_PROJECT_CLIENT;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Main {
 
    public static void main(String[] args) {
        Connexion connexion = new Connexion("C:/Users/Ahmed/Downloads/Database.db");
        connexion.connect();
        /*Contacts contact = new Contacts("0000000006","Test2", "Ajout", 0, Date.valueOf("2018-01-02"),
                "Gagne");
        connexion.addContact(contact);*/
        connexion.removeContact("Test2");
        connexion.changeContact("test@gmail.com", "ABOUKORA");
        connexion.changeContact("ABOUKORA", "Ahmed","0123456789");

        ResultSet resultSet = connexion.query("SELECT * FROM contacts");
        try {
            while (resultSet.next()) {
                System.out.println("Nom : "+resultSet.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connexion.close();
    }
}
 