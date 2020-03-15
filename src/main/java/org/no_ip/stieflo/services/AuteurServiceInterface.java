package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.entities.Project;

public interface AuteurServiceInterface {
  
	void create(Project reeks); 
	
	Project read(long id);
	
	void update(Project reeks);
	
	void delete(long id);
	
	List<Project> findAll();
	
	List<Project> findInNaam(String doelstring);
					
} 