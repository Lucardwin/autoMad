package it.lucastudio.project.madProject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.lucastudio.project.madProject.dto.DataSchool;

@Repository
public interface SchoolRepository extends CrudRepository<DataSchool, Long>{

	List<DataSchool> getSchoolByName(String name);
	List<DataSchool> getSchoolByProvincia(String name);
	List<DataSchool> getSchoolByRegione(String name);
	List<DataSchool> getSchoolByComune(String name);
	List<DataSchool> getSchoolByTipo(String name);
	List<DataSchool> getSchoolByTipoERegione(String tipo, String regione);
	List<DataSchool> getSchoolByTipoEProvincia(String tipo, String provincia);
	

}
