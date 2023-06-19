package com.hans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hans.model.Edificio;
import com.hans.service.EdificioService;

@RestController
@RequestMapping("/gestioneIncendi/edifici")
public class EdificioController {
	
	
	/* ESEMPIO POST EDIFICIO
	     {
        "tipo": "Azienda",
        "lon": 120.63,
        "lat":79.32
    } 
	 */

	@Autowired EdificioService db;
	
	@GetMapping()
	public ResponseEntity<List<Edificio>> cercaTuttiEdifici() {
		return new ResponseEntity<>(db.trovaTuttiEdifici(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> cercaEdificio(@PathVariable Long id) {
		if(db.edificioEsistenteConId(id)) {
			Edificio e=db.trovaEdificio(id);
			return new ResponseEntity<>(e,HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERRORE!! L'edificio cercato non esiste!",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping()
	public ResponseEntity<?> creaEdificio(@RequestBody Edificio e){
		Edificio edificio=db.salvaEdificio(e);
		if (edificio!=null) {
			return new ResponseEntity<>(e,HttpStatus.CREATED);			
		}else
			return new ResponseEntity<>("ERRORE!! ",HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificaEdificio(@RequestBody Edificio e, @PathVariable Long id){
		if(e.getId()==id && db.edificioEsistenteConId(id)) {
				Edificio edificio=db.modificaEdificio(e);
				if (edificio!=null) {
					return new ResponseEntity<>(e,HttpStatus.CREATED);			
				}else {
					return new ResponseEntity<>("ERRORE!! Modifica errata sull'edificio ",HttpStatus.BAD_REQUEST);							
				}
				
		}else
			return new ResponseEntity<>("ERRORE!! Edificio non presente nel DB o id nell'url diverso da quello dell'edificio passato!",HttpStatus.BAD_REQUEST);			
	}
	
	
}
