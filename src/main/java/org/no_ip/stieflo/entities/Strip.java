package org.no_ip.stieflo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "strips")
public class Strip implements Serializable { 
	private static final long serialVersionUID = 1L;
	private static final long MIN_ISBN_LENGTH = 1000000000000L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false, cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "reeksid")
	private Reeks reeks;
	
	@NumberFormat(style = Style.NUMBER)
	@Min(0)
	@Digits(integer = 3, fraction = 0)
	private Long albumnr;
	
	@Length(min = 1, max = 60)
	@SafeHtml
	private String titel;
	
	@NumberFormat(style = Style.NUMBER, pattern = "###")
	@Min(0)
	@Digits(integer = 3, fraction = 0)
	private Long paginas;
	
	private boolean hardcover;
	
	@NumberFormat(style = Style.NUMBER, pattern = "####")
	@Min(1900)
	@Digits(integer = 4, fraction = 0)
	private Long jaar;
	
	@Length(min = 0, max = 60)
	@SafeHtml
	private String speciaal;
	
	@Length(min = 0, max = 60)
	@SafeHtml
	private String beschrijving;
	
	@Length(min = 0, max = 60)
	@SafeHtml
	private String isbntext;
	
	@NumberFormat(style = Style.NUMBER)
	@Min(MIN_ISBN_LENGTH)
	@Digits(integer = 13, fraction = 0)
	private Long isbn;
	
	@NumberFormat(style = Style.NUMBER)
	@Min(1)
	//@Digits(integer = 4, fraction = 0)
	private Long druknr;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "vertalerid")
	private Vertaler vertaler;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) 
	@JoinColumn(name = "uitgeverid")
	private Uitgever uitgever;
	
	@NumberFormat(style = Style.NUMBER, pattern = "#####,##")
	@Min(0)
	@Digits(integer = 5, fraction = 2)
	private BigDecimal inkoopprijs;
	
	@Length(min = 0, max = 60)
	@SafeHtml
	private String cover;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "strip_tekenaars", joinColumns = @JoinColumn(name="stripid"), inverseJoinColumns = @JoinColumn(name="tekenaarid"))
	private Set<Auteur> tekenaars;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "strip_scenaristen", joinColumns = @JoinColumn(name="stripid"), inverseJoinColumns = @JoinColumn(name="scenaristid"))
	private Set<Auteur> scenaristen;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "strip_inkleurders", joinColumns = @JoinColumn(name="stripid"), inverseJoinColumns = @JoinColumn(name="inkleurderid"))
	private Set<Auteur> inkleurders;

	public Strip () {}
	
	public Strip(Reeks reeks, Long albumnr, String titel, Long paginas, boolean hardcover, Long jaar, String speciaal,
			String beschrijving, String isbntext, Long isbn, Long druknr, Vertaler vertaler, Uitgever uitgever,
			BigDecimal inkoopprijs, String cover, Set<Auteur> tekenaars, Set<Auteur> scenaristen,
			Set<Auteur> inkleurders) {
		this.albumnr = albumnr;
		this.titel = titel;
		this.paginas = paginas;
		this.hardcover = hardcover;
		this.jaar = jaar;
		this.speciaal = speciaal;
		this.beschrijving = beschrijving;
		this.isbntext = isbntext;
		this.isbn = isbn;
		this.druknr = druknr;
		this.inkoopprijs = inkoopprijs;
		this.cover = cover;
		tekenaars = new HashSet<>();
		scenaristen = new HashSet<>();
		inkleurders = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public Reeks getReeks() {
		return reeks;
	}

	public Long getAlbumnr() {
		return albumnr;
	}

	public String getTitel() {
		return titel;
	}

	public Long getPaginas() {
		return paginas;
	}

	public boolean isHardcover() {
		return hardcover;
	}

	public Long getJaar() {
		return jaar;
	}

	public String getSpeciaal() {
		return speciaal;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public String getIsbntext() {
		return isbntext;
	}

	public Long getIsbn() {
		return isbn;
	}

	public Long getDruknr() {
		return druknr;
	}

	public Vertaler getVertaler() {
		return vertaler;
	}

	public Uitgever getUitgever() {
		return uitgever;
	}

	public BigDecimal getInkoopprijs() {
		return inkoopprijs;
	}

	public String getCover() {
		return cover;
	}

	public Set<Auteur> getTekenaars() {
		return tekenaars;
	}

	public Set<Auteur> getScenaristen() {
		return scenaristen;
	}

	public Set<Auteur> getInkleurders() {
		return inkleurders;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public void setReeks(Reeks reeks) {
		this.reeks = reeks;
	}

	public void setAlbumnr(Long albumnr) {
		this.albumnr = albumnr;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public void setPaginas(Long paginas) {
		this.paginas = paginas;
	}

	public void setHardcover(boolean hardcover) {
		this.hardcover = hardcover;
	}

	public void setJaar(Long jaar) {
		this.jaar = jaar;
	}

	public void setSpeciaal(String speciaal) {
		this.speciaal = speciaal;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public void setIsbntext(String isbntext) {
		this.isbntext = isbntext;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public void setDruknr(Long druknr) {
		this.druknr = druknr;
	}

	public void setVertaler(Vertaler vertaler) {
		this.vertaler = vertaler;
	}

	public void setUitgever(Uitgever uitgever) {
		this.uitgever = uitgever;
	}

	public void setInkoopprijs(BigDecimal inkoopprijs) {
		this.inkoopprijs = inkoopprijs;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public void setTekenaars(Set<Auteur> tekenaars) {
		this.tekenaars = tekenaars;
	}

	public void setScenaristen(Set<Auteur> scenaristen) {
		this.scenaristen = scenaristen;
	}

	public void setInkleurders(Set<Auteur> inkleurders) {
		this.inkleurders = inkleurders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (albumnr ^ (albumnr >>> 32));
		result = prime * result + ((isbntext == null) ? 0 : isbntext.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Strip other = (Strip) obj;
		if (reeks.getNaam() != other.reeks.getNaam())
			return false;
		if (albumnr != other.albumnr)
			return false;
		if (isbntext == null) {
			if (other.isbntext != null)
				return false;
		} else if (!isbntext.equals(other.isbntext))
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Strip [reeks=");
		builder.append(reeks);
		builder.append(", albumnr=");
		builder.append(albumnr);
		builder.append(", titel=");
		builder.append(titel);
		builder.append(", paginas=");
		builder.append(paginas);
		builder.append(", hardcover=");
		builder.append(hardcover);
		builder.append(", jaar=");
		builder.append(jaar);
		builder.append(", speciaal=");
		builder.append(speciaal);
		builder.append(", beschrijving=");
		builder.append(beschrijving);
		builder.append(", isbntext=");
		builder.append(isbntext);
		builder.append(", isbn=");
		builder.append(isbn);
		builder.append(", druknr=");
		builder.append(druknr);
		builder.append(", vertaler=");
		builder.append(vertaler);
		builder.append(", uitgever=");
		builder.append(uitgever);
		builder.append(", inkoopprijs=");
		builder.append(inkoopprijs);
		builder.append(", cover=");
		builder.append(cover);
		builder.append(", tekenaars=");
		builder.append(tekenaars);
		builder.append(", scenaristen=");
		builder.append(scenaristen);
		builder.append(", inkleurders=");
		builder.append(inkleurders);
		builder.append("]");
		return builder.toString();
	}
	
}
