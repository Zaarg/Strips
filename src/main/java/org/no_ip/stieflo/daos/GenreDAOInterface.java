package org.no_ip.stieflo.daos;

import org.no_ip.stieflo.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDAOInterface extends JpaRepository<Genre,Long>{

	
	
}
