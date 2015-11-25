package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Genre;
import org.no_ip.stieflo.entities.Reeks;

public interface ReeksServiceInterface {
  
	void create(Reeks reeks); 
	
	Reeks read(long id);
	
	void update(Reeks reeks);
	
	void delete(long id);
	
	List<Reeks> findAll();
	
	long findAantalReeksen();
		  
	List<Reeks> findInNaam(String doelstring);

	List<Genre> findAllGenres();
	
			
} 