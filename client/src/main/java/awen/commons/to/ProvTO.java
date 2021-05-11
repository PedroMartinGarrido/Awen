package awen.commons.to;

import java.io.Serializable;
import java.util.Date;

public class ProvTO implements Serializable {

	private String  inprov;
	private String  provin;
	private String  inpais;
	private String  borlog;
	private String  usupro;
	private String  usucre;
	private String  usuult;
	private Date    feccre;
	private Date    fecult;
	private Boolean issele = false;
	public String getInprov() {
		return inprov;
	}
	public void setInprov(String inprov) {
		this.inprov = inprov;
	}
	public String getProvin() {
		return provin;
	}
	public void setProvin(String provin) {
		this.provin = provin;
	}
	public String getInpais() {
		return inpais;
	}
	public void setInpais(String inpais) {
		this.inpais = inpais;
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
	
	public Object compareProvProvin(ProvTO p2) {
		return this.provin.compareTo(p2.getProvin());
	}
	
}