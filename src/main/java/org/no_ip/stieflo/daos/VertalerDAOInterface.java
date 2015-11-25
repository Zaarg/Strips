package org.no_ip.stieflo.daos;

import java.util.List;

import org.no_ip.stieflo.entities.Vertaler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VertalerDAOInterface extends JpaRepository<Vertaler,Long>{

	List<Vertaler> findByNaamContaining(String doelstring);
	
}
