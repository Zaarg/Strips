package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Project;
import org.no_ip.stieflo.entities.Reeks;
import org.no_ip.stieflo.entities.Strip;
import org.no_ip.stieflo.entities.Uitgever;
import org.no_ip.stieflo.entities.Vertaler;

public interface StripServiceInterface {
  
	void create(Strip strip); 
	Strip read(long id);
	void update(Strip strip);
	void delete(long id);
	
	List<Strip> findAll();
	
	long findAantalStrips();
		  
	List<Strip> findInTitel(String doelstring);
	
	List<Reeks> findAllReeksen();
	List<Project> findAllAuteurs();
	List<Uitgever> findAllUitgevers();
	List<Vertaler> findAllVertalers();
} 