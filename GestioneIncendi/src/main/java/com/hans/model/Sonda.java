package com.hans.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sonde")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sonda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, name="posizione_sonda")
	private String posizioneSonda;
	
	@ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.MERGE})
	private Edificio edificio;


	
}
