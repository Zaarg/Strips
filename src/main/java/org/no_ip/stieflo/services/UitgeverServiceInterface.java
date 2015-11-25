package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Uitgever;

public interface UitgeverServiceInterface {
  
	void create(Uitgever reeks); 
	
	Uitgever read(long id);
	
	void update(Uitgever reeks);
	
	void delete(long id);
	
	List<Uitgever> findAll();
	
	List<Uitgever> findInNaam(String doelstring);
} 