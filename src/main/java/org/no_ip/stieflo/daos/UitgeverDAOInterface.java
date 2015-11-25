package org.no_ip.stieflo.daos;

import java.util.List;

import org.no_ip.stieflo.entities.Uitgever;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UitgeverDAOInterface extends JpaRepository<Uitgever,Long>{

	List<Uitgever> findByNaamContaining(String doelstring);
	
}
