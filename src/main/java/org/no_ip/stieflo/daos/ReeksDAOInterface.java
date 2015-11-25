package org.no_ip.stieflo.daos;

import java.util.List;

import org.no_ip.stieflo.entities.Reeks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReeksDAOInterface extends JpaRepository<Reeks,Long>{

	List<Reeks> findByNaamContainingOrderByNaam(String doelstring);
	
	
}
