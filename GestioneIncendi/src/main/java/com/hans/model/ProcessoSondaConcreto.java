package com.hans.model;

public class ProcessoSondaConcreto implements ProcessoSonda{

	@Override
	public void update(Segnalazione s) {
		System.out.println("Partito l'allarme nell'edificio con coordinate latitudine: "+s.getLat()+" longetudine: "+s.getLon()+", alle: "+s.getDataOraSegnalazione().getHour()+":"+s.getDataOraSegnalazione().getMinute()+":"+s.getDataOraSegnalazione().getSecond()+", livello pericolosità: "+s.getLivelloFumo());
		
	}

}
