package it.lucastudio.project.madProject.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.lucastudio.project.madProject.dto.MailInviate;
import it.lucastudio.project.madProject.dto.StaticMailData;

public class MailCrudRepoImpl implements MailCrudRepo {

	@PersistenceContext
	EntityManager entityManager;
	
	private final String byTipo="SELECT m "
			+ " FROM mail_inviate m"
			+ " WHERE "
			+ " m.tipo_domanda= ?1";

	@Override
	public List<MailInviate> getAllByTipo(String tipo) {
		Query query = entityManager.createQuery(byTipo, MailInviate.class);
		query.setParameter(1, tipo);
		return query.getResultList();
	}
	
	@Override
	public boolean save(MailInviate mailInviate) {
		entityManager.persist(mailInviate);
		return true;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(StaticMailData arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends StaticMailData> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<StaticMailData> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<StaticMailData> findAllById(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StaticMailData> findById(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StaticMailData> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends StaticMailData> Iterable<S> saveAll(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	










}
