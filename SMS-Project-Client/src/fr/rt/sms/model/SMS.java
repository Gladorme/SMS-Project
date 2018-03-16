package fr.rt.sms.model;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

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
    private String url = "192.168.43.201";
	
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
    public void sendSMS() throws ClientProtocolException, IOException {
    	Gson         gson          = new Gson();
    	HttpClient   httpClient    = HttpClientBuilder.create().build();
    	HttpPost     post          = new HttpPost(url);
    	StringEntity postingString = new StringEntity(gson.toJson("test"));
    	post.setEntity(postingString);
    	post.setHeader("Content-type", "application/json");
    	HttpResponse  response = httpClient.execute(post);
    	System.out.println(response.getEntity().getContent());
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
}
