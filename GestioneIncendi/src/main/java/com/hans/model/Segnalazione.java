package com.hans.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="segnalazioni")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Segnalazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.REMOVE,CascadeType.MERGE} )
	@JoinColumn(nullable = false)
	private Sonda sonda;
	
	@Column(nullable = false)
	private Integer livelloFumo;
	
	private double lon;
	
	private double lat;
	
	@Column(name="data_ora_segnalazione", nullable=false)
	private LocalDateTime dataOraSegnalazione;
	

	
	public Segnalazione(Sonda sonda, Integer livelloFumo, LocalDateTime dataOraSegnalazione) {
		this.sonda = sonda;
		this.livelloFumo = livelloFumo;
		this.dataOraSegnalazione = dataOraSegnalazione;
		this.lon =sonda.getEdificio().getLon();
		this.lat=sonda.getEdificio().getLat();
	}
	
	
}
