package awen.commons.to;

import java.io.Serializable;
import java.util.Date;

public class PaisTO implements Serializable {

	private Boolean issele = false;
	private String  inpais;
	private String  nopais;
	private String  contin;
	private String  comeur;
	private String  borlog;
	private String  usupro;
	private String  usucre;
	private String  usuult;
	private Date    feccre;
	private Date    fecult;

	public String getInpais() {
		return inpais;
	}

	public void setInpais(String inpais) {
		this.inpais = inpais;
	}

	public String getNopais() {
		return nopais.trim();
	}

	public void setNopais(String nopais) {
		this.nopais = nopais;
	}

	public String getContin() {
		return contin.trim();
	}

	public void setContin(String contin) {
		this.contin = contin;
	}

	public String getComeur() {
		return comeur.trim();
	}

	public void setComeur(String comeur) {
		this.comeur = comeur;
	}

	public String getBorlog() {
		return borlog;
	}

	public void setBorlog(String borlog) {
		this.borlog = borlog;
	}

	public String getUsupro() {
		return usupro;
	}

	public void setUsupro(String usupro) {
		this.usupro = usupro;
	}

	public String getUsucre() {
		return usucre;
	}

	public void setUsucre(String usucre) {
		this.usucre = usucre;
	}

	public String getUsuult() {
		return usuult;
	}

	public void setUsuult(String usuult) {
		this.usuult = usuult;
	}

	public Date getFeccre() {
		return feccre;
	}

	public void setFeccre(Date feccre) {
		this.feccre = feccre;
	}

	public Date getFecult() {
		return fecult;
	}

	public void setFecult(Date fecult) {
		this.fecult = fecult;
	}

	public Boolean getIssele() {
		return issele;
	}

	public void setIssele(Boolean issele) {
		this.issele = issele;
	}

	public Object comparePaisNopais(PaisTO p2) {
		return this.nopais.compareTo(p2.getNopais());
	}
	
}