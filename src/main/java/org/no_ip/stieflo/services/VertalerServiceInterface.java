package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Vertaler;

public interface VertalerServiceInterface {
  
	void create(Vertaler reeks); 
	
	Vertaler read(long id);
	
	void update(Vertaler reeks);
	
	void delete(long id);
	
	List<Vertaler> findAll();
	
	List<Vertaler> findInNaam(String doelstring);
} 