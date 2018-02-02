package com.TEAM_RT.SMS_PROJECT_CLIENT;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class Connexion {
    private String DBPath = "Chemin aux base de donn�e SQLite";
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
            System.out.println("Connexion a " + DBPath + " avec succ�s");
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
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}