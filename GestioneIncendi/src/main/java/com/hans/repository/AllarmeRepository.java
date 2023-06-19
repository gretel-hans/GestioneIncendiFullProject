package com.hans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hans.model.Allarme;

public interface AllarmeRepository extends JpaRepository<Allarme, Long>{

	@Query("SELECT u FROM Allarme u WHERE u.segnalazione.id = :idSegnalazione")
	Allarme trovaAllarmeConIdSegnalazione(@Param("idSegnalazione")Long id);
	
	public Allarme findBySegnalazioneId(Long id);
}
