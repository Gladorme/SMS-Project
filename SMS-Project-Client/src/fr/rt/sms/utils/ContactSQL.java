package fr.rt.sms.utils;

public class ContactSQL {
    
    private String nom;
    private String prenom;
    private String naissance;
    private String email;
    private String adresse;
    private String ville;
    private int pro;
    private String tel;
    
    
    public ContactSQL(String nom, String prenom, String naissance, String email, String adresse, String ville, int pro, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.naissance= naissance;
		this.email = email;
		this.adresse = adresse;
		this.ville = ville;
		this.pro = pro;
		this.tel = tel;
    }
    
    public String getNom() {
    	return this.nom;
    }
    public String getPrenom() {
    	return this.prenom;
    }
    public String getNaissance() {
    	return this.naissance;
    }
    public String getEmail() {
    	return this.email;
    }
    public String getAdresse() {
    	return this.adresse;
    }
    public String getVille() {
    	return this.ville;
    }
    public int getPro() {
    	return this.pro;
    }
    public String getTel() {
    	return this.tel;
    }
    
    public void setNom(String nom) {
    	this.nom = nom;
    }
    public void setPrenom(String prenom) {
    	this.prenom = prenom;
    }
    public void setNaissance(String naissance) {
    	this.naissance = naissance;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public void setAdresse(String adresse) {
    	this.adresse = adresse;
    }
    public void setVille(String ville) {
    	this.ville = ville;
    }
    public void setPro(int pro) {
    	this.pro = pro;
    }
    public void setTel(String tel) {
    	this.tel = tel;
    }
    
}
