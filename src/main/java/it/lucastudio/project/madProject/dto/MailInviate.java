package it.lucastudio.project.madProject.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@Entity(name="mail_inviate")
@Table(name="mail_inviate")
@AllArgsConstructor
@NoArgsConstructor
public class MailInviate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	
	String tipo_domanda;
	
	String tipo_scuola;
	
	String mail_inviata;
	
	
	
	
	@Override
	public boolean equals(Object m) {
		if(!(m instanceof MailInviate) || m==null) return false;
		MailInviate mail=(MailInviate)m;		
		if(mail.getMail_inviata()!=null && mail.getMail_inviata().equalsIgnoreCase(mail_inviata) &&
				mail.getTipo_domanda()!=null && mail.getTipo_domanda().equalsIgnoreCase(tipo_domanda) && 
					mail.getTipo_scuola()!=null && mail.getTipo_scuola().equalsIgnoreCase(tipo_scuola)){
			return true;
		}
		
		return false;
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getTipo_domanda() {
		return tipo_domanda;
	}




	public void setTipo_domanda(String tipo_domanda) {
		this.tipo_domanda = tipo_domanda;
	}




	public String getMail_inviata() {
		return mail_inviata;
	}




	public void setMail_inviata(String mail_inviata) {
		this.mail_inviata = mail_inviata;
	}




	public String getTipo_scuola() {
		return tipo_scuola;
	}




	public void setTipo_scuola(String tipo_scuola) {
		this.tipo_scuola = tipo_scuola;
	}
	
	
	
	

}
