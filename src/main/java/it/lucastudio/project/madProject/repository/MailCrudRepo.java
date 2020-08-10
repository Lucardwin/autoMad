package it.lucastudio.project.madProject.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.lucastudio.project.madProject.dto.MailInviate;
import it.lucastudio.project.madProject.dto.StaticMailData;

@Repository
public interface MailCrudRepo extends CrudRepository<StaticMailData, Integer>{
	
	List<MailInviate> getAllByTipo(String Tipo);

	boolean save(MailInviate mailInviate);

	

}
