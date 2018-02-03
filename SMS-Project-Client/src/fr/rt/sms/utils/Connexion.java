package fr.rt.sms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {
    private String DBPath = "Chemin à la base de donnée";
    private Connection connection = null;
    private Statement statement = null;
 
    public Connexion(String dBPath) {
        DBPath = dBPath;
    }
 
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion à " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion");
        }
    }
    
    public ResultSet query(String request) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requête : " + request);
        }
        return resultat;
  
    }
    public void addContact(ContactSQL contact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Contacts VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, contact.getNom());
            preparedStatement.setString(2, contact.getPrenom());
            preparedStatement.setString(3, contact.getNaissance());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.setString(5, contact.getAdresse());
            preparedStatement.setString(6, contact.getVille() );
            preparedStatement.setInt(7, contact.getPro());
            preparedStatement.setString(8, contact.getTel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /*public void removeContact(String nom) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `contacts` WHERE `contacts`.`nom` = ? OR `contacts`.`numero_tel` = ?");
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeContact(String nom, String prenom) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `contacts` WHERE `contacts`.`nom` = ? AND `contacts`.`prenom` = ?");
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void changeContact(String mail, String numero_tel) { //méthode pour changer le mail d'une personne
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `contacts` SET `email`=?  WHERE `contacts`.`numero_tel`= ?");
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,numero_tel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
    public void changeContact(String nom, String prenom, String numero_tel) { //méthode pour changer un numéro de telephone en fonction du nom et prenom
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `contacts` SET `numero_tel` = ? WHERE `contacts`.`nom` = ? AND `contacts`.`prenom` = ?");
            preparedStatement.setString(1,numero_tel);            
            preparedStatement.setString(2,nom);
            preparedStatement.setString(3,prenom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addentreehistorique(SMS sms) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO historique VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1, sms.getIdsms());
            preparedStatement.setString(2, sms.getTexte());
            preparedStatement.setString(3, sms.getNumero_tel_source());
            preparedStatement.setString(4, sms.getNumero_tel_source());
            preparedStatement.setString(5, sms.getDateenvoi());
            preparedStatement.setInt(6,0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
