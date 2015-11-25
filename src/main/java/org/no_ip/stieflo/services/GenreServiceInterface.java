package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Genre;

public interface GenreServiceInterface {
  
	void create(Genre reeks); 
	
	Genre read(long id);
	
	void update(Genre reeks);
	
	void delete(long id);
	
	List<Genre> findAll();
					
} 