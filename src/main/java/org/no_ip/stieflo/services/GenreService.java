package org.no_ip.stieflo.services;

import java.util.List;

import org.no_ip.stieflo.daos.GenreDAOInterface;
import org.no_ip.stieflo.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ReadOnlyTransactionalService
class GenreService implements GenreServiceInterface { 
	private final GenreDAOInterface genreDAO;
	  
	@Autowired
	GenreService(GenreDAOInterface genreDAO) {
	  this.genreDAO = genreDAO;
	}  
  
	@Override
	@ModifyingTransactionalServiceMethod  
	public void create(Genre genre)  {
		genreDAO.save(genre);
	}
  
	@Override
	public Genre read(long id) {
		return genreDAO.findOne(id);
	} 
  
	@Override
	@ModifyingTransactionalServiceMethod 
	public void update(Genre genre) {
		genreDAO.save(genre);
	} 
  
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)  
	public void delete(long id) {
		Genre genre = genreDAO.findOne(id);
		if (genre != null) {
		  if ( ! genre.getReeksen().isEmpty()) {
		    throw new RuntimeException();
		  }
		  genreDAO.delete(id);
		}
	}  
  
	@Override
	public List<Genre> findAll() {
		return genreDAO.findAll(new Sort("naam"));
	} 
  		
		
}	 