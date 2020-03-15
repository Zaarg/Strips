package org.no_ip.stieflo.daos;

import java.util.List;

import org.no_ip.stieflo.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurDAOInterface extends JpaRepository<Project,Long>{

	List<Project> findByNaamContaining(String doelstring);
	
}
