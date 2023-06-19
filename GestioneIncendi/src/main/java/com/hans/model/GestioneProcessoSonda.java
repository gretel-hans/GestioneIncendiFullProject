package com.hans.model;

import java.util.ArrayList;
import java.util.List;

public class GestioneProcessoSonda {
	private List<ProcessoSonda> listaProcessi=new ArrayList<ProcessoSonda>();
	
	public void aggiungiProcesso(ProcessoSonda p) {
		listaProcessi.add(p);
	}
	
	public void rimuoviProcesso(ProcessoSonda p) {
		listaProcessi.remove(p);
	}

	public void allertaProcesso(Segnalazione s) {
		listaProcessi.forEach(processo->processo.update(s));
	}
	
}
