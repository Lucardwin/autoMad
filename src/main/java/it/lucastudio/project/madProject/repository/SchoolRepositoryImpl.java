package it.lucastudio.project.madProject.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.lucastudio.project.madProject.dto.DataSchool;

public class SchoolRepositoryImpl implements SchoolRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	private final String byName="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.denomination= ?1";
	
	private final String byRegione="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.regione= ?1";
	
	private final String byProvincia="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.provincia= ?1";
	
	private final String byComune="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.descrizionecomune = ?1";
	
	private final String byDescComune="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.descrizionecomune= ?1";
	
	private final String byTipo="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.descrizionetipologiagradoistruzionescuola= ?1";
	
	private final String byTipoAndRegione="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.descrizionetipologiagradoistruzionescuola= ?1 AND "
			+ " d.regione= ?2 ";
	
	private final String byTipoAndProvincia="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.descrizionetipologiagradoistruzionescuola= ?1 AND "
			+ " d.provincia= ?2 ";
	
	private final String byTipoSuperioreAndProvincia="SELECT d "
			+ " FROM data_school d"
			+ " WHERE "
			+ " d.descrizionetipologiagradoistruzionescuola IN "
			+ "('CONVITTO NAZIONALE', " + 
			"'IST PROF CINEMATOGRAFIA E TELEVISIONE', " + 
			"'IST PROF ALBERGHIERO', " + 
			"'LICEO CLASSICO', " + 
			"'IST PROF PER I SERVIZI COMMERCIALI E TURISTICI'" + 
			"'LICEO ARTISTICO', " + 
			"'ISTITUTO D''ARTE', " + 
			"'IST PROF INDUSTRIA E ARTIGIANATO PER SORDOMUTI', " + 
			"'ISTITUTO TECNICO AGRARIO', " + 
			"'CONVITTO ANNESSO', " + 
			"'IST PROF PER L''AGRICOLTURA', " + 
			"'ISTITUTO TECNICO PER GEOMETRI', " + 
			"'ISTITUTO TECNICO NAUTICO', " + 
			"'IST PROF PER L''AGRICOLTURA E L''AMBIENTE', " + 
			"'IST PROF PER I SERVIZI ALBERGHIERI E RISTORAZIONE', " + 
			"'IST PROF PER I SERVIZI TURISTICI', " + 
			"'ISTITUTO TECNICO INDUSTRIALE', " + 
			"'IST PROF INDUSTRIA E ARTIGIANATO PER CIECHI', " + 
			"'ISTITUTO TECNICO PER ATTIVITA'' SOCIALI (GIA'',  ITF)', " + 
			"'ISTITUTO MAGISTRALE', " + 
			"'ISTITUTO TECNICO PER IL TURISMO', " + 
			"'CENTRO TERRITORIALE', " + 
			"'LICEO SCIENTIFICO', " + 
			"'SCUOLA MAGISTRALE', " + 
			"'ISTITUTO TECNICO COMMERCIALE', " + 
			"'ISTITUTO TECNICO AERONAUTICO', " + 
			"'IST PROF PER I SERVIZI SOCIALI', " + 
			"'IST PROF PER I SERVIZI COMM TUR E DELLA PUBB'" + 
			"'EDUCANDATO', " + 
			"'ISTITUTO SUPERIORE', " + 
			"'IST PROF INDUSTRIA E ATTIVITA'',  MARINARE', " + 
			"'IST PROF PER I SERVIZI PUBBLICITARI', " + 
			"'IST PROF INDUSTRIA E ARTIGIANATO', " + 
			"'IST PROF PER I SERVIZI COMMERCIALI', " + 
			"'IST TEC COMMERCIALE E PER GEOMETRI')"
			+ " AND "
			+ " d.provincia= ?1 ";
	
	
	

			
	
	
	@Override
	public List<DataSchool> getSchoolByName(String name) {
		Query query = entityManager.createQuery(byName, DataSchool.class);
		query.setParameter(1, name);
		return query.getResultList();
	}
	
	@Override
	public List<DataSchool> getSchoolByProvincia(String name) {
		Query query = entityManager.createQuery(byProvincia, DataSchool.class);
		query.setParameter(1, name);
		return query.getResultList();
	}

	@Override
	public List<DataSchool> getSchoolByRegione(String name) {
		Query query = entityManager.createQuery(byRegione, DataSchool.class);
		query.setParameter(1, name);
		return query.getResultList();
	}


	@Override
	public List<DataSchool> getSchoolByComune(String name) {
		Query query = entityManager.createQuery(byComune, DataSchool.class);
		query.setParameter(1, name);
		return query.getResultList();
	}





	@Override
	public List<DataSchool> getSchoolByTipo(String name) {
		Query query = entityManager.createQuery(byTipo, DataSchool.class);
		query.setParameter(1, name);
		return query.getResultList();
	}
	
	@Override
	public List<DataSchool> getSchoolByTipoERegione(String tipo, String regione) {
		Query query = entityManager.createQuery(byTipoAndRegione, DataSchool.class);
		query.setParameter(1, tipo);
		query.setParameter(2, regione);
		return query.getResultList();
	}
	
	@Override
	public List<DataSchool> getSchoolByTipoEProvincia(String tipo, String provincia) {
		Query query =null;
		if(tipo.equalsIgnoreCase("superiore")) {
			 query = entityManager.createQuery(byTipoSuperioreAndProvincia, DataSchool.class);
			 query.setParameter(1, provincia);
		}else {
			query = entityManager.createQuery(byTipoAndProvincia, DataSchool.class);
			query.setParameter(1, tipo);
			query.setParameter(2, provincia);
		}
		
		
		return query.getResultList();
	}





	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(DataSchool arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends DataSchool> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existsById(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<DataSchool> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<DataSchool> findAllById(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DataSchool> findById(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends DataSchool> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends DataSchool> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}







}
