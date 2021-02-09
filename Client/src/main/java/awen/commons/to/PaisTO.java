package awen.commons.to;

import java.io.Serializable;
import java.util.Date;

public class PaisTO implements Serializable {

	private String inpais;
	private String nopais;
	private String contin;
	private String comeur;
	private String borlog;
	private String usupro;
	private String usucre;
	private String usuult;
	private Date feccre;
	private Date fecult;

	public String getInpais() {
		return inpais;
	}

	public void setInpais(String inpais) {
		this.inpais = inpais;
	}

	public String getNopais() {
		return nopais;
	}

	public void setNopais(String nopais) {
		this.nopais = nopais;
	}

	public String getContin() {
		return contin;
	}

	public void setContin(String contin) {
		this.contin = contin;
	}

	public String getComeur() {
		return comeur;
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

}