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
import com.hans.model.Segnalazione;
import com.hans.model.Sonda;
import com.hans.service.EdificioService;
import com.hans.service.SegnalazioneService;
import com.hans.service.SondaService;

@RestController
@RequestMapping("/gestioneIncendi/segnalazioni")
public class SegnalazioneController {

	@Autowired SegnalazioneService db;
	
	/* ESEMPIO POST SEGNALAZIONE
	  {
    "sonda": {
        "id": 6,
        "posizioneSonda": "seconda cucina",
        "edificio": {
            "id": 5,
            "tipo": "Casa",
            "lon": 190.63,
            "lat": -160.32
        }
    },
    "livelloFumo": 6,
    "dataOraSegnalazione": "2023-10-18T02:32:00.266056"
}*/
	
	@GetMapping()
	public ResponseEntity<List<Segnalazione>> cercaTutteSegnalazioni() {
		return new ResponseEntity<>(db.trovaTutteSegnalazioni(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> cercaSegnalazione(@PathVariable Long id) {
		if(db.segnalazioneEsistenteConId(id)) {
			Segnalazione s=db.trovaSegnalazione(id);
			return new ResponseEntity<>(s,HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERRORE!! La segnalazione cercata non esiste!",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping()
	public ResponseEntity<?> creaSegnalazione(@RequestBody Segnalazione s){
		Segnalazione segnalazione=db.salvaSegnalazioni(s);
		if (segnalazione!=null) {
			return new ResponseEntity<>(s,HttpStatus.CREATED);			
		}else
			return new ResponseEntity<>("ERRORE!! ",HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificaSegnalazione(@RequestBody Segnalazione s, @PathVariable Long id){
		if(s.getId()==id && db.segnalazioneEsistenteConId(id)) {
			Segnalazione segnalazione=db.modificaSegnalazione(s);
				if (segnalazione!=null) {
					return new ResponseEntity<>(s,HttpStatus.CREATED);			
				}else {
					return new ResponseEntity<>("ERRORE!! Modifica errata sulla Segnalazione ",HttpStatus.BAD_REQUEST);							
				}
				
		}else
			return new ResponseEntity<>("ERRORE!! Segnalazione non presente nel DB o id nell'url diverso da quella della Segnalazione passata!",HttpStatus.BAD_REQUEST);			
	}
	
	
}
