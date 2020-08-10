package it.lucastudio.project.madProject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import it.lucastudio.project.madProject.clients.ProcessMad;
import it.lucastudio.project.madProject.dto.DataSchool;
import it.lucastudio.project.madProject.dto.MailInviate;
import it.lucastudio.project.madProject.jasperObject.MadDto;
import it.lucastudio.project.madProject.service.BuildPdf;
import it.lucastudio.project.madProject.service.DataSchoolService;
import it.lucastudio.project.madProject.service.ReaderCsv;
import it.lucastudio.project.madProject.service.SchoolMailSender;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("api/rest/dataSchool")
@Slf4j
public class SchoolDataController {	

	@Autowired
	DataSchoolService service;

	@Autowired
	ProcessMad processMad;

	@Autowired
	ReaderCsv readerCsv;

	@Autowired
	SchoolMailSender schoolMailSender;

	@Autowired
	BuildPdf buildPdf;

	private String path="src/main/resources/";


	private final String SOSTEGNO_PATH="madFilosofia.jasper";

	private final String POSTOCOMUNESOSTEGNO_PATH="madPostoComune.jasper";

	private final String FILOSOFIA_PATH="madFilosofiaSup.jasper";

	private final String TIPO_DOMANDA_SOSTEGNO="SOSTEGNO";

	private final String TIPO_DOMANDA_POSTOCOMUNESOSTEGNO="POSTOCOMUNESOSTEGNO";

	private final String TIPO_DOMANDA_FILOSOFIA="FILOSOFIA";

	private String generalPath;




	@GetMapping(value="{tipoDomanda:SOSTEGNO|POSTOCOMUNESOSTEGNO|FILOSOFIA}/{tipoScuola}/{regione}/{provincia}/{numeroMail}", produces = "application/json")
	public String getBook(@PathVariable(required=true) String tipoDomanda,
			@PathVariable(required=true) String tipoScuola,
			@PathVariable(required=true) String regione,
			@PathVariable(required=false) String provincia,
			@PathVariable(required=false) Integer numeroMail) throws JRException, IOException, MessagingException {

		if(tipoDomanda.equalsIgnoreCase(TIPO_DOMANDA_SOSTEGNO)) {
			generalPath=SOSTEGNO_PATH;
		}else if(tipoDomanda.equalsIgnoreCase(TIPO_DOMANDA_POSTOCOMUNESOSTEGNO)) {
			generalPath=POSTOCOMUNESOSTEGNO_PATH;
		}else if(tipoDomanda.equalsIgnoreCase(TIPO_DOMANDA_FILOSOFIA)){
			generalPath=FILOSOFIA_PATH;
		}
		Instant start = Instant.now();
		List<DataSchool> ds=null;
		System.out.println("NOME PROVINCIA: "+provincia);
		if(provincia==null || provincia.equalsIgnoreCase("nessuna")) {
			ds=service.getSchoolByTipoERegione(tipoScuola, regione);
		}else {
			ds=service.getSchoolByTipoEProvincia(tipoScuola, provincia);
		}

		List<String> s=new ArrayList<String>();

		List<MailInviate> daNonInviare=schoolMailSender.getEmailList(tipoDomanda);

		int c=0;
		try {
			for(DataSchool d: ds) {
				if(validate(tipoDomanda,d,daNonInviare)) {
					MadDto mad= new MadDto();
					mad.setCapscuola(d.getCapscuola());
					mad.setDenominazioneistitutodiriferimento(d.getDescrizionetipologiagradoistruzionescuola()+" - "+d.getDenominazionescuola());
					mad.setIndirizzoscuola(d.getIndirizzoscuola());
					mad.setProvincia(d.getProvincia());
					mad.setDescrizionecomune(d.getDescrizionecomune());
					Gson g=new Gson();
					String json=g.toJson(mad);
					byte[] madPdf= buildPdf.createReport(json, path+generalPath);
					byte[] cv = Files.readAllBytes(Paths.get(path+"cv.pdf"));
					byte[] lettera = Files.readAllBytes(Paths.get(path+"lettera.pdf"));


					if(!s.contains(d.getIndirizzoemailscuola())) {
						schoolMailSender.sendEmail(d, tipoDomanda, cv, lettera, madPdf);
						schoolMailSender.saveEmail(MailInviate.builder()
								.mail_inviata(d.getIndirizzoemailscuola())
								.tipo_domanda(tipoDomanda)
								.tipo_scuola(tipoScuola)
								.build());
						s.add(d.getIndirizzoemailscuola());
						Thread.currentThread().sleep(20000);
						c++;
						System.out.println("Mail inviata numero ---> "+c);

					}
					if(c==numeroMail) {
						break;
					}

					/*if(!s.contains(d.getIndirizzoemailscuola())) {
						//schoolMailSender.sendEmail(d, cv, lettera, madPdf);
						schoolMailSender.saveEmail(MailInviate.builder()
								.mail_inviata(d.getIndirizzoemailscuola())
								.tipo_domanda(tipo)
								.build());
						c++;
					}*/

				}else {
					System.out.println("mail già inviata: "+tipoDomanda+"-> "+tipoScuola+"-> "+d.getIndirizzoemailscuola());
				}
			}
		}catch(Exception e) {
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
			System.out.println( "mail inviate: "+c+" tempo esecuzione: "+timeElapsed+" eccezione: " +e.getMessage());
			return "mail inviate: "+c+" tempo esecuzione: "+timeElapsed+" eccezione: " +e.getMessage();
		}

		Instant finish = Instant.now();
		long timeElapsed = Duration.between(start, finish).toMillis();  //in millis

		System.out.println( "mail inviate: "+c+" tempo esecuzione: "+timeElapsed);

		return  "mail inviate: "+c+" tempo esecuzione: "+timeElapsed;
	}

	@GetMapping(value="SETSITOMAD/{regione}", produces = "application/json")
	public ResponseEntity<?>setNuvola(@PathVariable(required=true) String regione) {
		List<DataSchool> list=null;
		try {
			if(regione!=null && regione.equalsIgnoreCase("nessuna")) {
				list=service.findAll();
			}else {
				list=service.getSchoolByRegione(regione);
			}
			
			int c=0;
			int total=0;

			for(DataSchool dat:list) {
				if(total==10000) {
					break;
				}


				try {
					String sito=null;
					if(dat.getSitomad()==null  || dat.getSitomad().equals("Non Disponibile")) {
						Thread.currentThread().sleep(100);
						sito=processMad.process(dat.getCodicescuola().trim().toUpperCase());
						total++;


						if(sito!=null) {
							dat.setSitomad(sito);
							service.update(dat);
							c++;
							System.out.println(c+": Usa mad-->"+dat.getDenominazionescuola()+", "+dat.getCodicescuola()+", sito: "+sito );

						}else {
							dat.setSitomad("Non Disponibile");
							service.update(dat);
						}
					}
				}catch(Exception e) {
					dat.setSitomad("Non Disponibile");
					service.update(dat);
				}
			}
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return null;




	}



	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
		List<DataSchool> list=null;
		try {
			list=readerCsv.createListBean(file.getBytes());
			log.trace("lista creata size: " + list.size());
			System.out.println("lista creata size: " + list.size());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if(service.saveAll(list)) {
			log.trace("inserimento terminato");
			System.out.println("inserimento terminato");
			return ResponseEntity.ok().build();
		}else {
			log.trace("inserimento fallito");
			System.out.println("inserimento fallito");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}


	}

	private boolean validate(String tipoDomanda,DataSchool d,List<MailInviate> l) {

		return d.getCapscuola()!=null && !("Non Disponibile").equalsIgnoreCase(d.getCapscuola()) 
				&& d.getDescrizionetipologiagradoistruzionescuola()!= null && !("Non Disponibile").equalsIgnoreCase(d.getDenominazionescuola()) 
				&& d.getDenominazionescuola()!= null && !("Non Disponibile").equalsIgnoreCase(d.getDenominazionescuola())
				&& d.getIndirizzoscuola()!= null && !("Non Disponibile").equalsIgnoreCase(d.getIndirizzoscuola())
				&& d.getProvincia()!= null && !("Non Disponibile").equalsIgnoreCase(d.getProvincia())
				&& d.getDescrizionecomune()!= null && !("Non Disponibile").equalsIgnoreCase(d.getDescrizionecomune())
				&& d.getIndirizzoemailscuola()!= null && !("Non Disponibile").equalsIgnoreCase(d.getIndirizzoemailscuola())
				&& l!=null && !l.contains(MailInviate.builder()
						.mail_inviata(d.getIndirizzoemailscuola())
						.tipo_domanda(tipoDomanda)
						.tipo_scuola(d.getDescrizionetipologiagradoistruzionescuola())
						.build());



	}

}
