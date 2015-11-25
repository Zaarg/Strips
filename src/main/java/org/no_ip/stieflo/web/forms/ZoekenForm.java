package org.no_ip.stieflo.web.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;

public class ZoekenForm {
	
	@Length(min = 0, max = 60)
	@SafeHtml
	private String zoekTerm;
		
	public String getZoekTerm() {
		return zoekTerm;
	} 
  	
	public void setZoekTerm(String zoekTerm) {
		this.zoekTerm = zoekTerm;
	}
	
} 