package awen.commons.to;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import entities.Pred;

public class PaisTO implements Serializable {

	// Cliente awen
	private Long inpais;
	private String nopais;
	private Long incont;
	private Boolean unieur;
	private String borlog;
	private Long usupro;
	private Long usucre;
	private Long usuult;
	private Timestamp feccre;
	private Timestamp fecult;
	private Boolean issele = false;
	private String contin;

	public Long getInpais() {
		return inpais;
	}

	public void setInpais(Long inpais) {
		this.inpais = inpais;
	}

	public String getNopais() {
		return nopais;
	}

	public void setNopais(String nopais) {
		this.nopais = nopais;
	}


	public Long getIncont() {
		return incont;
	}

	public void setIncont(Long incont) {
		this.incont = incont;
	}

	public Boolean getUnieur() {
		return unieur;
	}

	public void setUnieur(Boolean unieur) {
		this.unieur = unieur;
	}

	public String getBorlog() {
		return borlog;
	}

	public void setBorlog(String borlog) {
		this.borlog = borlog;
	}

	public Long getUsupro() {
		return usupro;
	}

	public void setUsupro(Long usupro) {
		this.usupro = usupro;
	}

	public Long getUsucre() {
		return usucre;
	}

	public void setUsucre(Long usucre) {
		this.usucre = usucre;
	}

	public Long getUsuult() {
		return usuult;
	}

	public void setUsuult(Long usuult) {
		this.usuult = usuult;
	}

	public Timestamp getFeccre() {
		return feccre;
	}

	public void setFeccre(Timestamp feccre) {
		this.feccre = feccre;
	}

	public Timestamp getFecult() {
		return fecult;
	}

	public void setFecult(Timestamp fecult) {
		this.fecult = fecult;
	}

	public Boolean getIssele() {
		return issele;
	}

	public void setIssele(Boolean issele) {
		this.issele = issele;
	}

	public String getContin() {
		return contin;
	}

	public void setContin(String contin) {
		this.contin = contin;
	}

	public Object comparePaisNopais(PaisTO pais) {
		return this.nopais.compareTo(pais.getNopais());
	}

	public void printPais(PaisTO pais) {
		System.out.println("<<< Pais.inpais....: " + pais.getInpais());
		System.out.println("<<< Pais.nopais....: " + pais.getNopais());
		System.out.println(">>> Pais.incont: " + pais.getIncont());
		System.out.println("<<< Pais.unieur....: " + pais.getUnieur());
		System.out.println("<<< Pais.borlog....: " + pais.getBorlog());
		System.out.println("<<< Pais.usupro....: " + pais.getUsupro());
		System.out.println("<<< Pais.usucre....: " + pais.getUsucre());
		System.out.println("<<< Pais.usuult....: " + pais.getUsuult());
		System.out.println("<<< Pais.feccre....: " + pais.getFeccre());
		System.out.println("<<< Pais.fecult....: " + pais.getFecult());
		System.out.println("<<< Pais.issele....: " + pais.getIssele());
		System.out.println("<<< Pais.continente: " + pais.getContin());
	}

}