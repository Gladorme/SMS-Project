package com.TEAM_RT.SMS_PROJECT_CLIENT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Connexion {
    private String DBPath = "Chemin aux base de donnée SQLite";
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
            System.out.println("Connexion a " + DBPath + " avec succès");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connecxion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connecxion");
        }
    }
    
    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(requet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur dans la requet : " + requet);
        }
        return resultat;
  
    }
    public void addContact(Contacts contact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Contacts VALUES(?,?,?,?,?,?)");
            preparedStatement.setString(1, contact.getNumero_tel());
            preparedStatement.setString(2, contact.getNom());
            preparedStatement.setString(3, contact.getPrenom());
            preparedStatement.setInt(4, contact.getCategorie());
            preparedStatement.setDate(5, contact.getDatenaissance());
            preparedStatement.setString(6, contact.getEmail() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void removeContact(String nom) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `contacts` WHERE `contacts`.`nom` = ? OR WHERE `contacts`.`numero_tel` = ?");
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void changeContact(String mail, String numero_tel) { //méthode pour changer le mail d'une personne
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `contacts` SET `email` = ?  WHERE `contacts`.`numero_tel` = ?");
            preparedStatement.setString(1,mail);
            preparedStatement.setString(2,numero_tel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}