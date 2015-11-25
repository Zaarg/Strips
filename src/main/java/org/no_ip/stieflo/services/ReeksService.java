package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.daos.GenreDAOInterface;
import org.no_ip.stieflo.daos.ReeksDAOInterface;
import org.no_ip.stieflo.entities.Genre;
import org.no_ip.stieflo.entities.Reeks;
import org.no_ip.stieflo.exceptions.HeeftNogStripsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ReadOnlyTransactionalService
class ReeksService implements ReeksServiceInterface { 
	private final ReeksDAOInterface reeksDAO;
	private final GenreDAOInterface genreDAO;
	
	@Autowired
	ReeksService(ReeksDAOInterface reeksDAO, GenreDAOInterface genreDAO) {
	  this.reeksDAO = reeksDAO;
	  this.genreDAO = genreDAO;
	}  
  
	@Override
	@ModifyingTransactionalServiceMethod  
	public void create(Reeks reeks)  {
		reeksDAO.save(reeks);
	}
  
	@Override
	public Reeks read(long id) {
		return reeksDAO.findOne(id);
	} 
  
	@Override
	@ModifyingTransactionalServiceMethod 
	public void update(Reeks reeks) {
		reeksDAO.save(reeks);
	} 
  
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)  
	public void delete(long id) {
		Reeks reeks = reeksDAO.findOne(id);
		if (reeks != null) {
		  if ( ! reeks.getStrips().isEmpty()) {
		    throw new HeeftNogStripsException();
		  }
		  reeksDAO.delete(id);
		}
	}  
  
	@Override
	public List<Reeks> findAll() {
		return reeksDAO.findAll(new Sort("naam"));
	} 
  
	@Override
	public long findAantalReeksen() {
		return reeksDAO.count();
	}
	
	@Override
	public List<Reeks> findInNaam(String doelstring) {
		return reeksDAO.findByNaamContainingOrderByNaam(doelstring);
	}
	
	@Override
	public List<Genre> findAllGenres() {
		return genreDAO.findAll(new Sort("naam"));
	}
		
}	 