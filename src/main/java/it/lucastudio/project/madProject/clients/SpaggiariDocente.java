package it.lucastudio.project.madProject.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.lucastudio.project.madProject.config.NuvolaClientConfig;

@FeignClient(name = "spaggiari", url = "https://web.spaggiari.eu/ber/app/default", configuration = NuvolaClientConfig.class)
public interface SpaggiariDocente extends CommonInterface {
	
	@RequestMapping(method = RequestMethod.GET, value = "/gestione_mad.php?sede_codice={codiceMeccanografico}")
	String getCodice(@PathVariable("codiceMeccanografico") String codiceMeccanografico);
	

}
