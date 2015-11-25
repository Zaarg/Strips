package org.no_ip.stieflo.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Embeddable
public class Plaats implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Length(min = 1, max = 40)
	@SafeHtml
	private String plaatsnaam;
	
	@NotBlank
	@Length(min = 2, max = 2)
	@SafeHtml
	private String landcode;
	
	public Plaats() {
	}

	public Plaats(String plaatsnaam, String landcode) {
		this.plaatsnaam = plaatsnaam;
		this.landcode = landcode;
	}
	
	public String getPlaatsnaam() {
		return plaatsnaam;
	}

	public String getLandcode() {
		return landcode;
	}

	@Override
	public boolean equals(Object obj) {
	  if ( ! (obj instanceof Plaats)) {
	    return false;
	  }
	  return ((Plaats) obj).getPlaatsnaam() == this.getPlaatsnaam() && ((Plaats) obj).getLandcode() == this.getLandcode();
	} 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((landcode == null) ? 0 : landcode.hashCode());
		result = prime * result + ((plaatsnaam == null) ? 0 : plaatsnaam.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return this.getPlaatsnaam()+','+this.getLandcode();
	}
	
}
