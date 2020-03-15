package org.no_ip.stieflo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Table(name = "reeksen")
public class Reeks implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Length(min = 1, max = 60)
	@SafeHtml
	private String naam;
	
	@Valid
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "genreid")
	private Genre genre;
	
	@Length(min = 0, max = 300)
	@SafeHtml
	private String beschrijving;
	
	private boolean beeindigd;
	private boolean volledig;
	private boolean geenlijst;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reeks")
	@OrderBy("albumnr")
	private List<Strip> strips;
	
	public Reeks() {}
	
	public Reeks(String naam, String beschrijving) {
		setNaam(naam);
		setBeschrijving(beschrijving);
		setBeeindigd(false);
		setVolledig(false);
		setGeenlijst(false);
		strips = new ArrayList<>();
	}
	
	public Reeks(String naam, String beschrijving, boolean beeindigd, boolean volledig, boolean geenlijst) {
		setNaam(naam);
		setBeschrijving(beschrijving);
		setBeeindigd(beeindigd);
		setVolledig(volledig);
		setGeenlijst(geenlijst);
		strips = new ArrayList<>();
	}
	
	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}

	public Genre getGenre() {
		return genre;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public boolean isBeeindigd() {
		return beeindigd;
	}

	public boolean isVolledig() {
		return volledig;
	}

	public boolean isGeenlijst() {
		return geenlijst;
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

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public void setBeeindigd(boolean beeindigd) {
		this.beeindigd = beeindigd;
	}

	public void setVolledig(boolean volledig) {
		this.volledig = volledig;
	}

	public void setGeenlijst(boolean geenlijst) {
		this.geenlijst = geenlijst;
	}

	@Override
	public boolean equals(Object obj) {
	  if ( ! (obj instanceof Reeks)) {
	    return false;
	  }
	  return ((Reeks) obj).naam.equals(naam);
	} 
	
	@Override
	public int hashCode() {
	  return naam.hashCode();
	}
	
	/*@Override
	public String toString() {
		return this.getNaam()+','+this.getGenre().toString()+','+this.getBeschrijving();
	}*/
	
}
