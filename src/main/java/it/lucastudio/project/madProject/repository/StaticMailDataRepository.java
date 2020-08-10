package it.lucastudio.project.madProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.lucastudio.project.madProject.dto.StaticMailData;

@Repository
public interface StaticMailDataRepository extends CrudRepository<StaticMailData, Long>{

}
