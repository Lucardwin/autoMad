package it.lucastudio.project.madProject.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.lucastudio.project.madProject.dto.DataSchool;
import it.lucastudio.project.madProject.dto.MailInviate;
import it.lucastudio.project.madProject.repository.MailCrudRepo;


@Service
public class SchoolMailSenderImpl implements SchoolMailSender {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	MailCrudRepo mailCrudRepo;
	
	private final String TIPO_DOMANDA_SOSTEGNO="SOSTEGNO";
	
	private final String TIPO_DOMANDA_POSTOCOMUNESOSTEGNO="POSTOCOMUNESOSTEGNO";
	
	private final String TIPO_DOMANDA_FILOSOFIA="FILOSOFIA";
	
	private String generalText;
	private String oggetto;

	@Override
	public void sendEmail(DataSchool data, String tipoDomanda,byte[] curriculum,byte[] lettera,byte[] mad ) throws MessagingException {

		if(tipoDomanda.equalsIgnoreCase(TIPO_DOMANDA_SOSTEGNO)) {
			generalText="CLASSE DI CONCORSO AD0J - SOSTEGNO\r\n";
			oggetto="Messa a disposizione Sostegno";
		}else if(tipoDomanda.equalsIgnoreCase(TIPO_DOMANDA_POSTOCOMUNESOSTEGNO)) {
			generalText="CLASSE DI CONCORSO A019 - FILOSOFIA E STORIA E ADSS - SOSTEGNO\r\n";
			oggetto="Messa a disposizione Posto Comune e Sostegno";
		}else if(tipoDomanda.equalsIgnoreCase(TIPO_DOMANDA_FILOSOFIA)){
			generalText="CLASSE DI CONCORSO A019 - FILOSOFIA E STORIA\r\n";
			oggetto="Messa a disposizione classe di concorso A19";
		}
		MimeMessage msg= javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

	  //helper.setTo("lauracolosimo120389@gmail.com");


		helper.setTo(data.getIndirizzoemailscuola());
		
       // helper.setBcc("lauracolosimo120389@gmail.com");

		helper.setSubject(oggetto);
		helper.setText("\r\n" + 
				"Alla Cortese Attenzione del Dirigente Scolastico,\r\n" + 
				"la sottoscritta Laura Colosimo invia domanda di messa a disposizione per l'a.s.2020/2021\r\n" + 
				"\r\n" + 
				generalText+ 
				"\r\n" + 
				"\r\n" + 
				"Si allega domanda e curriculum vitae in formato PDF"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "Laura Colosimo");

		helper.addAttachment("Messa_a_disposizione_Laura_Colosimo.pdf", new ByteArrayResource(mad));
		helper.addAttachment("Cv_Laura_Colosimo.pdf", new ByteArrayResource(curriculum));
		//helper.addAttachment("Lettera_Presentazione_Laura_Colosimo.pdf", new ByteArrayResource(lettera));


		javaMailSender.send(msg);




	}
	
	
	@Override
	public void saveEmail(String mail, String tipo) throws MessagingException {

		mailCrudRepo.save(MailInviate.builder()
				.mail_inviata(mail)
				.tipo_domanda(tipo)
				.build());




	}


	@Override
	@Transactional
	public void saveEmail(MailInviate build) {
		mailCrudRepo.save(build);
		
	}


	@Override
	public List<MailInviate> getEmailList(String tipo) {
		return mailCrudRepo.getAllByTipo(tipo);
		
	}
	
	
	


}
