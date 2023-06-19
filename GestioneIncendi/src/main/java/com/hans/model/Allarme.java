package com.hans.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.hans.enums.LivelloPericolosita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="registro_allarmi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Allarme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="livello_periocolosita")
	private LivelloPericolosita livelloPeriocolosita;

	@Column(name="data_allarme", nullable=false)
	private LocalDate dataAllarme;
	
	@Column(name="ora_allarme", nullable=false)
	private LocalTime oraAllarme;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Segnalazione segnalazione;
	
	
}
