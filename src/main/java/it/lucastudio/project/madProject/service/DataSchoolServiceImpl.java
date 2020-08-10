package it.lucastudio.project.madProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.lucastudio.project.madProject.dto.DataSchool;
import it.lucastudio.project.madProject.repository.SchoolCrudRepo;
import it.lucastudio.project.madProject.repository.SchoolRepository;
import it.lucastudio.project.madProject.repository.StaticMailDataRepository;

@Service
public class DataSchoolServiceImpl implements DataSchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	@Autowired
	private SchoolCrudRepo schoolCrudRepo;
	
	@Autowired
	private StaticMailDataRepository staticMailDataRepository;

	@Override
	public List<DataSchool> findAll() {

		List<DataSchool> dataSchool = (List<DataSchool>) schoolCrudRepo.findAll();
		return dataSchool;
	}
	
	@Override
	public List<DataSchool> getMailByName(String name){
		return schoolRepository.getSchoolByName(name);
	}


	@Override
	public boolean saveAll(List<DataSchool> l) {
		return schoolCrudRepo.saveAll(l) != null;
	}
	
	@Override
	public boolean save(DataSchool dat) {
		return schoolCrudRepo.save(dat) != null;
	}
	
	@Override
	public boolean update(DataSchool dat) {
		return schoolCrudRepo.save(dat) != null;
	}

	@Override
	public List<DataSchool> getSchoolByProvincia(String name) {
		return schoolRepository.getSchoolByProvincia(name);
	}

	@Override
	public List<DataSchool> getSchoolByRegione(String name) {
		return schoolRepository.getSchoolByRegione(name);
	}

	@Override
	public List<DataSchool> getSchoolByComune(String name) {
		return schoolRepository.getSchoolByComune(name);
	}

	@Override
	public List<DataSchool> getSchoolByTipo(String name) {
		return schoolRepository.getSchoolByTipo(name);
	}
	
	@Override
	public List<DataSchool> getSchoolByTipoERegione(String tipo, String regione) {
		return schoolRepository.getSchoolByTipoERegione(tipo,regione);
	}
	
	@Override
	public List<DataSchool> getSchoolByTipoEProvincia(String tipo, String provincia) {
		return schoolRepository.getSchoolByTipoEProvincia(tipo,provincia);
	}


}
