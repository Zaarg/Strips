package org.no_ip.stieflo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.no_ip.stieflo.valueobjects.Plaats;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name = "uitgevers")
public class Uitgever implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Length(min = 1, max = 40)
	@SafeHtml
	private String naam;
	
	@Valid
	@Embedded
	private Plaats plaats;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "uitgever") 
	private List<Strip> strips;
	
	public Uitgever() {}
	
	public Uitgever(String naam) {
		setNaam(naam);
		strips = new ArrayList<>();
	}
	
	public Uitgever(String naam, Plaats plaats) {
		setNaam(naam);
		setPlaats(plaats);
		strips = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Plaats getPlaats() {
		return plaats;
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
	
	public void setPlaats(Plaats plaats) {
		this.plaats = plaats;
	}
	
	@Override
	public boolean equals(Object obj) {
	  if ( ! (obj instanceof Uitgever)) {
	    return false;
	  }
	  return ((Uitgever) obj).naam.equals(naam);
	} 
	
	@Override
	public int hashCode() {
	  return naam.hashCode();
	}
	
	@Override
	public String toString() {
		return this.getNaam()+','+this.getPlaats().toString();
	}
	
}
