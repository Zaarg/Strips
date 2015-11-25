package org.no_ip.stieflo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "vertalers")
public class Vertaler implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Length(min = 1, max = 40)
	@SafeHtml
	//@Column(unique = true)
	private String naam;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vertaler") 
	private List<Strip> strips;
	
	public Vertaler() {}
	
	public Vertaler(String naam) {
		setNaam(naam);
		strips = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}
	
	public List<Strip> getStrips() {
		return strips;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public boolean equals(Object obj) {
	  if ( ! (obj instanceof Vertaler)) {
	    return false;
	  }
	  return ((Vertaler) obj).naam.equals(naam);
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
