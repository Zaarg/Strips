package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.daos.AuteurDAOInterface;
import org.no_ip.stieflo.entities.Auteur;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ReadOnlyTransactionalService
class AuteurService implements AuteurServiceInterface { 
	private final AuteurDAOInterface auteurDAO;
	  
	@Autowired
	AuteurService(AuteurDAOInterface auteurDAO) {
	  this.auteurDAO = auteurDAO;
	}  
  
	@Override
	@ModifyingTransactionalServiceMethod  
	public void create(Auteur auteur)  {
		auteurDAO.save(auteur);
	}
  
	@Override
	public Auteur read(long id) {
		return auteurDAO.findOne(id);
	} 
  
	@Override
	@ModifyingTransactionalServiceMethod 
	public void update(Auteur auteur) {
		auteurDAO.save(auteur);
	} 
  
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)  
	public void delete(long id) {
		Auteur auteur = auteurDAO.findOne(id);
		if (auteur != null) {
		  if ( ! (auteur.getTekenaarstrips().isEmpty() || auteur.getScenariststrips().isEmpty() || auteur.getInkleurderstrips().isEmpty())  ) {
		    throw new HeeftNogStripsException();
		  }
		  auteurDAO.delete(id);
		}
	}  
  
	@Override
	public List<Auteur> findAll() {
		return auteurDAO.findAll(new Sort("naam"));
	} 
  	
	@Override
	public List<Auteur> findInNaam(String doelstring) {
		return auteurDAO.findByNaamContaining(doelstring);
	}
		
}	 