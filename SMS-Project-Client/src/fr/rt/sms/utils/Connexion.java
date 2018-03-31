package fr.rt.sms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.rt.sms.model.Contact;
import fr.rt.sms.model.Groupe;
import fr.rt.sms.model.SMS;

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
    
    public void addContact(Contact contact) {
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
    
    public void changeContact(Contact contact, String tel) {
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement("UPDATE Contacts SET nom = ?, prenom = ?, naissance = ?, email = ?, adresse = ?, ville = ?, pro = ?, tel = ? WHERE tel = ?");
            preparedStatement.setString(1, contact.getNom());
            preparedStatement.setString(2, contact.getPrenom());
            preparedStatement.setString(3, contact.getNaissance());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.setString(5, contact.getAdresse());
            preparedStatement.setString(6, contact.getVille() );
            preparedStatement.setInt(7, contact.getPro());
            preparedStatement.setString(8, contact.getTel());
            preparedStatement.setString(9, tel);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteContact(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM Contacts WHERE tel = ?");
            preparedStatement.setString(1, contact.getTel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addSMS(SMS sms) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO SMS (contenu,tel_src,tel_dest,chiffrement) VALUES(?,?,?,?)");
            preparedStatement.setString(1, sms.getContenu());
            preparedStatement.setString(2, SMS.getTel_src());
            preparedStatement.setString(3, sms.getTel_dest());
            preparedStatement.setInt(4, sms.getChiffrement());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSMS(SMS sms) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM SMS WHERE id_sms = ?");
            preparedStatement.setInt(1, sms.getId_sms());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet getContacts(String groupe) {
    	ResultSet resultat = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM Contacts, Groupes, Appartenances WHERE id_groupe = groupe_id AND tel = contact_tel AND Groupes.nom = ?");
            preparedStatement.setString(1, groupe);
            resultat = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return resultat;
    }
    
    
    
    public void deleteGroupe(String nom) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM Groupe WHERE nom = ?");
            preparedStatement.setString(1, nom);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addGroupe(Groupe groupe) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Groupes (nom) VALUES(?)");
            preparedStatement.setString(1, groupe.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void addAppartenance(Groupe groupe, Contact contact) {
        try {
            PreparedStatement preparedStatement = connection
            .prepareStatement("INSERT INTO Appartenances (goupe_id, contact_tel) VALUES(?,?)");
            preparedStatement.setInt(1, groupe.getId());
            preparedStatement.setString(9, contact.getTel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteGroupe(Groupe groupe) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM Groupes WHERE nom = ?");
            preparedStatement.setString(1, groupe.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    
    public void close() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
