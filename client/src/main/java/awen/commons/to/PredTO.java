package awen.commons.to;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class PredTO implements Serializable {

	// Cliente awen
	private Long inpred;
	private String clavex;
	private String valorx;
	private String borlog;
	private Long usupro;
	private Long usucre;
	private Long usuult;
	private Timestamp feccre;
	private Timestamp fecult;
	private Boolean issele = false;

	public Long getInpred() {
		return inpred;
	}

	public void setInpred(Long inpred) {
		this.inpred = inpred;
	}

	public String getClavex() {
		return clavex;
	}

	public void setClavex(String clavex) {
		this.clavex = clavex;
	}

	public String getValorx() {
		return valorx;
	}

	public void setValorx(String valorx) {
		this.valorx = valorx;
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

	public Object comparePredClavex(PredTO pred) {
		return this.clavex.compareTo(pred.getClavex());
	}

	public void printPred(PredTO pred) {
		System.out.println("<<< Pred.Inpred....: " + pred.getInpred());
		System.out.println("<<< Pred.Clavex....: " + pred.getClavex());
		System.out.println("<<< Pred.Valorx....: " + pred.getValorx());
		System.out.println("<<< Pred.Borlog....: " + pred.getBorlog());
		System.out.println("<<< Pred.Usupro....: " + pred.getUsupro());
		System.out.println("<<< Pred.Usucre....: " + pred.getUsucre());
		System.out.println("<<< Pred.Usuult....: " + pred.getUsuult());
		System.out.println("<<< Pred.Feccre....: " + pred.getFeccre());
		System.out.println("<<< Pred.Fecult....: " + pred.getFecult());
		System.out.println("<<< Pred.Issele....: " + pred.getIssele());
	}

}