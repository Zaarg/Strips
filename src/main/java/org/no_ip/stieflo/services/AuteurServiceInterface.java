package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Auteur;

public interface AuteurServiceInterface {
  
	void create(Auteur reeks); 
	
	Auteur read(long id);
	
	void update(Auteur reeks);
	
	void delete(long id);
	
	List<Auteur> findAll();
	
	List<Auteur> findInNaam(String doelstring);
					
} 