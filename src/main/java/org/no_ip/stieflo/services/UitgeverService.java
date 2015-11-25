package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.daos.UitgeverDAOInterface;
import org.no_ip.stieflo.entities.Uitgever;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ReadOnlyTransactionalService
class UitgeverService implements UitgeverServiceInterface { 
	private final UitgeverDAOInterface uitgeverDAO;
	  
	@Autowired
	UitgeverService(UitgeverDAOInterface uitgeverDAO) {
	  this.uitgeverDAO = uitgeverDAO;
	}  
  
	@Override
	@ModifyingTransactionalServiceMethod  
	public void create(Uitgever uitgever)  {
		uitgeverDAO.save(uitgever);
	}
  
	@Override
	public Uitgever read(long id) {
		return uitgeverDAO.findOne(id);
	} 
  
	@Override
	@ModifyingTransactionalServiceMethod 
	public void update(Uitgever uitgever) {
		uitgeverDAO.save(uitgever);
	} 
  
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)  
	public void delete(long id) {
		Uitgever uitgever = uitgeverDAO.findOne(id);
		if (uitgever != null) {
		  if ( ! uitgever.getStrips().isEmpty()) {
		    throw new HeeftNogStripsException();
		  }
		  uitgeverDAO.delete(id);
		}
	}  
  
	@Override
	public List<Uitgever> findAll() {
		return uitgeverDAO.findAll(new Sort("naam"));
	} 
  		
	@Override
	public List<Uitgever> findInNaam(String doelstring) {
		return uitgeverDAO.findByNaamContaining(doelstring);
	}	
}	 