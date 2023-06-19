package com.hans.model;

import com.hans.enums.TipoEdificio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="edifici")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoEdificio tipo;
	
	@Column(nullable = false)
	private double lon;
	
	@Column(nullable = false)
	private double lat;

	public Edificio(TipoEdificio tipo, double lon, double lat) {
		this.tipo = tipo;
		this.lon = lon;
		this.lat = lat;
	}
	
	
	
}
