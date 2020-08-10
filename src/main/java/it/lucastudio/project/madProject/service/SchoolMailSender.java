package it.lucastudio.project.madProject.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import it.lucastudio.project.madProject.dto.DataSchool;
import it.lucastudio.project.madProject.dto.MailInviate;

@Service
public interface SchoolMailSender {



	void saveEmail(String mail, String tipo) throws MessagingException;

	void saveEmail(MailInviate build);
	

	List<MailInviate> getEmailList(String tipo);

	void sendEmail(DataSchool data, String tipoDomanda, byte[] curriculum, byte[] lettera, byte[] mad)
			throws MessagingException;

}
