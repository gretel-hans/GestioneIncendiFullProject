package com.hans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hans.model.Segnalazione;

public interface SegnalazioneRepository extends JpaRepository<Segnalazione, Long>{

	
	@Query("SELECT s from Segnalazione s WHERE s.sonda.edificio.id = :idEdificio")
	List<Segnalazione> trovaSegnalazioneConEdificio(@Param("idEdificio")Long id);
}
