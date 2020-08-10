package it.lucastudio.project.madProject.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessMadImpl implements ProcessMad {
	
	@Autowired
	NuovolaDocente clientNuovola;
	
	@Autowired
	SpaggiariDocente clientSpaggiari;

	@Override
	public String process(String codiceMec) {
		return this.execute(codiceMec);

	}
	
	
	private String execute(String codiceMec){
		try {
			throw new Exception();
			/*
			clientNuovola.getCodice(codiceMec);
			System.out.println("Nuvola ok");
			return "https://nuvola.madisoft.it/mad/"+codiceMec.trim().toUpperCase()+"/docente/form/inserisci";
*/
		}catch(Exception e) {
			try {
			String s=clientSpaggiari.getCodice(codiceMec);
			System.out.println(s);
			if(!s.contains("NESSUN")) {	
				System.out.println("Spiaggiari ok");
				return "https://web.spaggiari.eu/ber/app/default/gestione_mad.php?sede_codice="+codiceMec.trim().toUpperCase();

			}else {
				throw new Exception("404 spaggiari");
			}
			//System.out.println("Spiaggiari ok");
			//return "https://web.spaggiari.eu/ber/app/default/gestione_mad.php?mod=MAD_01&target=ber&sede_codice="+codiceMec.trim().toUpperCase();
			}catch(Exception ex) {
				System.out.println(ex);
				return null;				
				
			}
		}
	}


}
