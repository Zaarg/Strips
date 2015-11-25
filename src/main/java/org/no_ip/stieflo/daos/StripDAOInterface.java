package org.no_ip.stieflo.daos;

import java.util.List;

import org.no_ip.stieflo.entities.Strip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StripDAOInterface extends JpaRepository<Strip,Long>{

	List<Strip> findByTitelContaining(String doelstring);
	
}
