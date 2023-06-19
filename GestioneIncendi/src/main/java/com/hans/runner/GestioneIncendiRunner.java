package com.hans.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class GestioneIncendiRunner implements CommandLineRunner{
	
	@Override
	public void run(String... args) throws Exception {
    System.out.println("Runner Gestione Incendi...");	
	}
	
	
}
