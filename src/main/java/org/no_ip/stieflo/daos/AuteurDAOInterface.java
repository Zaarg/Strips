package org.no_ip.stieflo.daos;

import java.util.List;

import org.no_ip.stieflo.entities.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurDAOInterface extends JpaRepository<Auteur,Long>{

	List<Auteur> findByNaamContaining(String doelstring);
	
}
