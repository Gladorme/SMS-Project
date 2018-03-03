package fr.rt.sms.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SMS {
	private Integer id_sms;
    private final StringProperty contenu;
    private final StringProperty tel_src;
    private final StringProperty tel_dest;
    private final IntegerProperty chiffrement;
	
	public SMS (String contenu, String tel_src, String tel_dest, Integer chiffrement) {
		this.contenu = new SimpleStringProperty(contenu);
		this.tel_src = new SimpleStringProperty(tel_src);
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
    
    public String getTel_src () {
    	return this.tel_src.get();
    }
    public void setTel_src (String tel_src) {
    	this.tel_src.set(tel_src);
    }
    public StringProperty tel_srcProperty() {
    	return this.tel_src;
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
}
