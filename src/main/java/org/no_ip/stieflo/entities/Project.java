package org.no_ip.stieflo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "auteurs")
public class Project implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Length(min = 1, max = 50)
	@SafeHtml
	@Column(unique = true)
	private String naam;
	
	@ManyToMany(mappedBy="tekenaars")
	private List<Strip> tekenaarstrips;
	
	@ManyToMany(mappedBy="scenaristen")
	private List<Strip> scenariststrips;
	
	@ManyToMany(mappedBy="inkleurders")
	private List<Strip> inkleurderstrips;
	
	public Project() {}
	
	public Project(String naam) {
		setNaam(naam);
		tekenaarstrips = new ArrayList<>();
		scenariststrips = new ArrayList<>();
		inkleurderstrips = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}
	
	public List<Strip> getTekenaarstrips() {
		return tekenaarstrips;
	}

	public List<Strip> getScenariststrips() {
		return scenariststrips;
	}

	public List<Strip> getInkleurderstrips() {
		return inkleurderstrips;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public boolean equals(Object obj) {
	  if ( ! (obj instanceof Project)) {
	    return false;
	  }
	  return ((Project) obj).naam.equals(this.naam);
	} 
	
	@Override
	public int hashCode() {
	  return naam.hashCode();
	}
	
	@Override
	public String toString() {
		return this.getNaam();
	}
	
}
