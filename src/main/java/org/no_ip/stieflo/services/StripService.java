package org.no_ip.stieflo.services;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.no_ip.stieflo.daos.AuteurDAOInterface;
import org.no_ip.stieflo.daos.ReeksDAOInterface;
import org.no_ip.stieflo.daos.StripDAOInterface;
import org.no_ip.stieflo.daos.UitgeverDAOInterface;
import org.no_ip.stieflo.daos.VertalerDAOInterface;
import org.no_ip.stieflo.entities.Project;
import org.no_ip.stieflo.entities.Reeks;
import org.no_ip.stieflo.entities.Strip;
import org.no_ip.stieflo.entities.Uitgever;
import org.no_ip.stieflo.entities.Vertaler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@ReadOnlyTransactionalService
class StripService implements StripServiceInterface { 
	private static final String CATAWIKI_SEARCH_URL ="http://www.catawiki.nl/search?q=";
	private static final String STRIPINFO_SEARCH_URL ="http://www.stripinfo.be/zoek.php?zoekstring=";
	private final StripDAOInterface stripDAO;
	private final ReeksDAOInterface reeksDAO;
	private final AuteurDAOInterface auteurDAO;
	private final UitgeverDAOInterface uitgeverDAO;
	private final VertalerDAOInterface vertalerDAO;
	  
	@Autowired
	public StripService(StripDAOInterface stripDAO, ReeksDAOInterface reeksDAO, AuteurDAOInterface auteurDAO,
			UitgeverDAOInterface uitgeverDAO, VertalerDAOInterface vertalerDAO) {
		this.stripDAO = stripDAO;
		this.reeksDAO = reeksDAO;
		this.auteurDAO = auteurDAO;
		this.uitgeverDAO = uitgeverDAO;
		this.vertalerDAO = vertalerDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod  
	public void create(Strip strip)  {
		stripDAO.save(strip);
	}
  
	@Override
	public Strip read(long id) {
		return stripDAO.findOne(id);
	} 
  
	@Override
	@ModifyingTransactionalServiceMethod 
	public void update(Strip strip) {
		stripDAO.save(strip);
	} 
  
	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)  
	public void delete(long id) {
		Strip strip = stripDAO.findOne(id);
		if (strip != null) {
		  stripDAO.delete(id);
		}
	}  
  
	@Override
	public List<Strip> findAll() { 
		return stripDAO.findAll(new Sort("titel"));
	} 
  
	@Override
	public long findAantalStrips() {
		return stripDAO.count();
	}
	
	@Override
	public List<Strip> findInTitel(String doelstring) {
		return stripDAO.findByTitelContaining(doelstring);
	}

	@Override
	public List<Reeks> findAllReeksen() {
		return reeksDAO.findAll(new Sort("naam"));	
	}

	@Override
	public List<Project> findAllAuteurs() {
		return auteurDAO.findAll(new Sort("naam"));
	}

	@Override
	public List<Uitgever> findAllUitgevers() {
		return uitgeverDAO.findAll(new Sort("naam"));
	}

	@Override
	public List<Vertaler> findAllVertalers() {
		return vertalerDAO.findAll(new Sort("naam"));
	}
	
	public String OnlineISBNOpzoeken(String ISBN) throws IOException {
		Document doc = Jsoup.connect(STRIPINFO_SEARCH_URL+ISBN).get();
		Elements results = doc.select(".span8");
		return "";
	}
}	 