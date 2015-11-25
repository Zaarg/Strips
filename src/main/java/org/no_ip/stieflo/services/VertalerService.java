package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.daos.VertalerDAOInterface;
import org.no_ip.stieflo.entities.Vertaler;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ReadOnlyTransactionalService
class VertalerService implements VertalerServiceInterface { 
	private final VertalerDAOInterface vertalerDAO;
	  
	@Autowired
	VertalerService(VertalerDAOInterface vertalerDAO) {
	  this.vertalerDAO = vertalerDAO;
	}  
  
	@Override
	@ModifyingTransactionalServiceMethod  
	public void create(Vertaler vertaler)  {
		vertalerDAO.save(vertaler);
	}
  
	@Override
	public Vertaler read(long id) {
		return vertalerDAO.findOne(id);
	} 
  
	@Override
	@ModifyingTransactionalServiceMethod 
	public void update(Vertaler vertaler) {
		vertalerDAO.save(vertaler);
	} 
  
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)  
	public void delete(long id) {
		Vertaler vertaler = vertalerDAO.findOne(id);
		if (vertaler != null) {
		  if ( ! vertaler.getStrips().isEmpty()) {
		    throw new HeeftNogStripsException();
		  }
		  vertalerDAO.delete(id);
		}
	}  
  
	@Override
	public List<Vertaler> findAll() {
		return vertalerDAO.findAll(new Sort("naam"));
	} 
  		
	@Override
	public List<Vertaler> findInNaam(String doelstring) {
		return vertalerDAO.findByNaamContaining(doelstring);
	}	
}	 