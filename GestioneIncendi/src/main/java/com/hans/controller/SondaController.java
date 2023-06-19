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
import com.hans.model.Sonda;
import com.hans.service.EdificioService;
import com.hans.service.SondaService;

@RestController
@RequestMapping("/gestioneIncendi/sonde")
public class SondaController {

	/* ESEMPIO POST SONDA
	  {
    "id": 6,
    "posizioneSonda": "mansarda",
    "edificio": {
        "id": 5,
        "tipo": "Casa",
        "lon": 190.63,
        "lat": -160.32
    }
}*/
	
	
	@Autowired SondaService db;
	
	@GetMapping()
	public ResponseEntity<List<Sonda>> cercaTutteSonde() {
		return new ResponseEntity<>(db.trovaTutteSonde(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> cercaSonda(@PathVariable Long id) {
		if(db.sondaEsistenteConId(id)) {
			Sonda e=db.trovaSonda(id);
			return new ResponseEntity<>(e,HttpStatus.OK);			
		}else
			return new ResponseEntity<>("ERRORE!! La sonda cercata non esiste!",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping()
	public ResponseEntity<?> creaSonda(@RequestBody Sonda s){
		Sonda sonda=db.salvaSonda(s);
		if (sonda!=null) {
			return new ResponseEntity<>(s,HttpStatus.CREATED);			
		}else
			return new ResponseEntity<>("ERRORE!! ",HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> modificaSonda(@RequestBody Sonda s, @PathVariable Long id){
		if(s.getId()==id && db.sondaEsistenteConId(id)) {
			Sonda sonda=db.modificaSonda(s);
				if (sonda!=null) {
					return new ResponseEntity<>(s,HttpStatus.CREATED);			
				}else {
					return new ResponseEntity<>("ERRORE!! Modifica errata sulla sonda ",HttpStatus.BAD_REQUEST);							
				}
				
		}else
			return new ResponseEntity<>("ERRORE!! Sonda non presente nel DB o id nell'url diverso da quella della sonda passata!",HttpStatus.BAD_REQUEST);			
	}
	
	
}
