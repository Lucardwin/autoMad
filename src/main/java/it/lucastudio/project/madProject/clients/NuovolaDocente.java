package it.lucastudio.project.madProject.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.lucastudio.project.madProject.config.NuvolaClientConfig;

@FeignClient(name = "nuvola", url = "https://nuvola.madisoft.it/mad", configuration = NuvolaClientConfig.class)
public interface NuovolaDocente extends CommonInterface {
	
	@RequestMapping(method = RequestMethod.GET, value = "/{codiceMeccanografico}/docente/form/inserisci")
	String getCodice(@PathVariable("codiceMeccanografico") String codiceMeccanografico);
	
	
	

}
