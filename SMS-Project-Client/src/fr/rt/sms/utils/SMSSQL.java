package fr.rt.sms.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SMSSQL {
	private int idsms;
	private String texte;
	private String numero_tel_source;
	private String numero_tel_dest;
	private String dateenvoi;
	private int ACKs;


	public SMSSQL(String texte, String numero_tel_source, String numero_tel_dest, String dateenvoi) {
		super();
		this.texte = texte;
		this.numero_tel_source = numero_tel_source;
		this.numero_tel_dest = numero_tel_dest;
		this.dateenvoi = dateenvoi;
	}

	public int getIdsms() {
		return idsms;
	}
	public void setIdsms(int idsms) {
		this.idsms = idsms;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public String getNumero_tel_source() {
		return numero_tel_source;
	}
	public void setNumero_tel_source(String numero_tel_source) {
		this.numero_tel_source = numero_tel_source;
	}
	public String getNumero_tel_dest() {
		return numero_tel_dest;
	}
	public void setNumero_tel_dest(String numero_tel_dest) {
		this.numero_tel_dest = numero_tel_dest;
	}
	public String getDateenvoi() {
		return dateenvoi;
	}
	public void setDateenvoi(String dateenvoi) {
		this.dateenvoi = dateenvoi;
	}
	public int getACKs() {
		return ACKs;
	}
	public void setACKs(int aCKs) {
		ACKs = aCKs;
	}


	public File creationSMS() {
	File fichier = new File ("@../../../../../sms/" + this.getIdsms()+".json" );
	return fichier;
	}
	public void envoiSMS() {
		File fichier=creationSMS();
		FileWriter ecrireFichier;
		try{
			ecrireFichier = new FileWriter(fichier);
			ecrireFichier.write("{\"action\":\"send\",\"dest\": "+this.getNumero_tel_dest()+",\"msg\": \"" +this.getTexte()+ "\"}");
			ecrireFichier.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
