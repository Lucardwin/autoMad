package it.lucastudio.project.madProject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.lucastudio.project.madProject.dto.DataSchool;
@Service
public interface DataSchoolService {

	List<DataSchool> findAll();

	List<DataSchool> getMailByName(String name);
	
	List<DataSchool> getSchoolByProvincia(String name);
	List<DataSchool> getSchoolByRegione(String name);
	List<DataSchool> getSchoolByComune(String name);
	List<DataSchool> getSchoolByTipo(String name);
	
	boolean saveAll(List<DataSchool> l);
	
	boolean save(DataSchool d);
	
	boolean update(DataSchool d);

	List<DataSchool> getSchoolByTipoERegione(String tipo, String regione);
	List<DataSchool> getSchoolByTipoEProvincia(String tipo, String provincia);

}
