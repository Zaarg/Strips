package org.no_ip.stieflo.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "genres")
public class Genre implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Length(min = 1, max = 40)
	@SafeHtml
	@Column(unique = true)
	private String naam;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
	@OrderBy("naam")
	private Set<Reeks> reeksen;
	
	public Genre() {}
	
	public Genre(String naam) {
		setNaam(naam);
		reeksen = new LinkedHashSet<>();
	}
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}
	
	public Set<Reeks> getReeksen() {
	    return Collections.unmodifiableSet(reeksen);
	}	
	
	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public boolean equals(Object obj) {
	  if ( ! (obj instanceof Genre)) {
	    return false;
	  }
	  return ((Genre) obj).naam.equals(naam);
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
