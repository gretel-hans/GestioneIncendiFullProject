package com.hans.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hans.enums.LivelloPericolosita;
import com.hans.model.Allarme;
import com.hans.model.Edificio;
import com.hans.model.GestioneProcessoSonda;
import com.hans.model.ProcessoSonda;
import com.hans.model.ProcessoSondaConcreto;
import com.hans.model.Segnalazione;
import com.hans.model.Sonda;
import com.hans.repository.AllarmeRepository;
import com.hans.repository.EdificioRepository;
import com.hans.repository.SegnalazioneRepository;
import com.hans.repository.SondaRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SegnalazioneService {
	
	@Autowired SegnalazioneRepository db;
	@Autowired AllarmeService dbAllarme;
	

	
	public Segnalazione salvaSegnalazioni(Segnalazione s) {
		if(segnalazioneEsistente(s) || (s.getLivelloFumo()<=0 || s.getLivelloFumo()>=11)) {
			throw new EntityExistsException("ERRORE!! Segnalazione già esistente o livello di fumo non valido!! "); 
		}
		else {
			GestioneProcessoSonda gPS=new GestioneProcessoSonda();
			ProcessoSonda p1= new ProcessoSondaConcreto();
			gPS.aggiungiProcesso(p1);
			s.setLat(s.getSonda().getEdificio().getLat());
			s.setLon(s.getSonda().getEdificio().getLon());
			Segnalazione segnalazione=db.save(s);
			if(s.getLivelloFumo()>=5) {
				Allarme a=new Allarme();
				a.setSegnalazione(s);
				a.setDataAllarme(LocalDate.of(s.getDataOraSegnalazione().getYear(), s.getDataOraSegnalazione().getMonthValue(), s.getDataOraSegnalazione().getDayOfMonth()));
				a.setOraAllarme(LocalTime.of(s.getDataOraSegnalazione().getHour(), s.getDataOraSegnalazione().getMinute(), s.getDataOraSegnalazione().getSecond()));
				
				if(s.getLivelloFumo()>=5 &&s.getLivelloFumo()<=7) {
					a.setLivelloPeriocolosita(LivelloPericolosita.Medio);
					dbAllarme.salvaAllarme(a);
					
				}else if(s.getLivelloFumo()>7) {
					a.setLivelloPeriocolosita(LivelloPericolosita.Alto);
					dbAllarme.salvaAllarme(a);
				}
				if(segnalazione!=null) {
					gPS.allertaProcesso(segnalazione);  
					
				}			
			}
			return segnalazione;
		}
	}
	
	public Segnalazione modificaSegnalazione(Segnalazione s) {
		if(!segnalazioneEsistenteConId(s.getId())) {
			throw new EntityNotFoundException("ERRORE!! La segnalazione non esiste!!"); 
		}else {
			GestioneProcessoSonda gPS=new GestioneProcessoSonda();
			ProcessoSonda p1= new ProcessoSondaConcreto();
			gPS.aggiungiProcesso(p1);
			s.setLat(s.getSonda().getEdificio().getLat());
			s.setLon(s.getSonda().getEdificio().getLon());
			Segnalazione segnalazione=db.save(s);
			if(s.getLivelloFumo()>=5) {
				Allarme a=dbAllarme.trovaAllarmeConIdSegnalazione(s.getId());
				a.setSegnalazione(s);
				a.setDataAllarme(LocalDate.of(s.getDataOraSegnalazione().getYear(), s.getDataOraSegnalazione().getMonthValue(), s.getDataOraSegnalazione().getDayOfMonth()));
				a.setOraAllarme(LocalTime.of(s.getDataOraSegnalazione().getHour(), s.getDataOraSegnalazione().getMinute(), s.getDataOraSegnalazione().getSecond()));
				if(s.getLivelloFumo()>=5 &&s.getLivelloFumo()<=7) {
					a.setLivelloPeriocolosita(LivelloPericolosita.Medio);
					dbAllarme.salvaAllarme(a);
					
				}else if(s.getLivelloFumo()>7) {
					a.setLivelloPeriocolosita(LivelloPericolosita.Alto);
					dbAllarme.salvaAllarme(a);
				}
				if(segnalazione!=null) {
					gPS.allertaProcesso(segnalazione);   
				}			
			}
			return segnalazione;			
		}
	}
	
	
	
	public List<Segnalazione> trovaTutteSegnalazioni(){
		return db.findAll();
	}

	public Segnalazione trovaSegnalazione(Long id){
		Segnalazione s=db.findById(id).get();
		if(s!=null) {
			return s;			
		}else {			
			throw new EntityNotFoundException("ERRORE!! La segnalazione cercata non esiste!!"); 
		}	
	}
	

	
	public List<Segnalazione> trovaSegnalazioneConIdEdificio(Long id){
		return db.trovaSegnalazioneConEdificio(id);
	}
	
	boolean esiste;
	public boolean segnalazioneEsistente(Segnalazione s) {
		esiste=false;
		db.findAll().forEach(segna->{
			if(segna.getDataOraSegnalazione().equals(s.getDataOraSegnalazione()) && segna.getLat()==s.getLat() && segna.getLon()==s.getLon() && segna.getLivelloFumo()==s.getLivelloFumo() && segna.getSonda().equals(s.getSonda())  ){
				esiste=true;
			}
		});
		if(esiste) {
			return true;
		}else
		return false;
		
	}
	
	public boolean segnalazioneEsistenteConId(Long id) {
		if (db.existsById(id)){
			return true;	
		}else
		return false;
	}
	

}
