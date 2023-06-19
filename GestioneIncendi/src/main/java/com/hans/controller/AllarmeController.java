package com.hans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hans.model.Allarme;
import com.hans.service.AllarmeService;

@RestController
@RequestMapping("/gestioneIncendi/allarmi")
public class AllarmeController {


	@Autowired AllarmeService db;
	
	@GetMapping()
	public ResponseEntity<List<Allarme>> cercaTutteVociAllarmi() {
		return new ResponseEntity<>(db.trovaTuttiAllarmi(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> cercaVoceAllarme(@PathVariable Long id) {
		if(db.allarmeeEsistenteConId(id)) {
			Allarme s=db.trovaAllarme(id);
			return new ResponseEntity<>(s,HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERRORE!! La voce di allarme cercata non esiste!",HttpStatus.BAD_REQUEST);
	}
	
/*
	@PutMapping("/{id}")
	public ResponseEntity<?> modificaVoceAllarme(@RequestBody Allarme s, @PathVariable Long id){
		if(s.getId()==id && db.allarmeeEsistenteConId(id)) {
			Allarme allarme=db.modificaSegnalazione(s);
				if (allarme!=null) {
					return new ResponseEntity<>(s,HttpStatus.CREATED);			
				}else {
					return new ResponseEntity<>("ERRORE!! Modifica errata sulla Segnalazione ",HttpStatus.BAD_REQUEST);							
				}
				
		}else
			return new ResponseEntity<>("ERRORE!! Segnalazione non presente nel DB o id nell'url diverso da quella della Segnalazione passata!",HttpStatus.BAD_REQUEST);			
	}
	*/
	
}
