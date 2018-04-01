package fr.rt.sms.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rt.sms.utils.Connexion;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SMS {
	private Integer id_sms;
    private final StringProperty contenu;
    public static String tel_src;
    private final StringProperty tel_dest;
    private final IntegerProperty chiffrement;
	
    public SMS () {
    	this(null,null,0);
    }
    
	public SMS (String contenu, String tel_dest, Integer chiffrement) {
		this.contenu = new SimpleStringProperty(contenu);
		this.tel_dest = new SimpleStringProperty(tel_dest);
		this.chiffrement = new SimpleIntegerProperty(chiffrement);
	}
	
	public Integer getId_sms () {
		return this.id_sms;
	}
    public String getContenu () {
    	return this.contenu.get();
    }
    public void setContenu (String contenu) {
    	this.contenu.set(contenu);
    }
    public StringProperty contenuProperty() {
    	return this.contenu;
    }
    
    public static String getTel_src () {
    	return SMS.tel_src;
    }
    public static void setTel_src (String tel_src) {
    	SMS.tel_src = tel_src;
    }
    
    public String getTel_dest () {
    	return this.tel_dest.get();
    }
    public void setTel_dest (String tel_dest) {
    	this.tel_dest.set(tel_dest);
    }
    public StringProperty tel_destProperty() {
    	return this.tel_dest;
    }
    
    public Integer getChiffrement () {
    	return this.chiffrement.get();
    }
    public void setChiffrement (Integer chiffrement) {
    	this.chiffrement.set(chiffrement);
    }
    public IntegerProperty chiffrementProperty() {
    	return this.chiffrement;
    }
    public void sendSMS() throws MalformedURLException, IOException {
    	String url = "http://localhost/SMS-Project/index.php";
    	String charset = "UTF-8";
    	String json = "{\"action\":\"send\","
    			    + "\"dest\":\"" + this.getTel_dest() + "\","
    			    + "\"msg\":\"" + this.getContenu() + "\""
    			    + "}"
    			    ;
    	String query = String.format("json=%s", json);
    	
    	HttpURLConnection connection = (HttpURLConnection) new URL(url + "?" + query).openConnection();
    	connection.setRequestProperty("Accept-Charset", charset);
    	InputStream reponse = connection.getInputStream();
    	int status = connection.getResponseCode();
    	
    	System.out.println(url + "?" + query + "\n");
    	System.out.println("Code réponse" + status);
    	
    }
    public void insertSQL() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.addSMS(this);
        connexion.close();
    }
    public void deleteSQL() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        connexion.deleteSMS(this);
        connexion.close();
    }
    public static int getMessageEnvoye() {
        Connexion connexion = new Connexion("src/fr/rt/sms/utils/bdd.db");
        connexion.connect();
        int resultat = connexion.getMessageEnvoye();
        connexion.close();
        
        return resultat;
    }
}
