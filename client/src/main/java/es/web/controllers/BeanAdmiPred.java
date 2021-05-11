package es.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import awen.commons.to.PredTO;
import es.web.services.ServiciosPred;



@Component("beanAdmiPred")
@ManagedBean(name = "beanAdmiPred")
@Scope("application")
public class BeanAdmiPred {

	private static final Logger logger = LoggerFactory.getLogger(BeanAdmiPred.class);
	ArrayList<PredTO> predAll = new ArrayList<PredTO>();
	ServiciosPred serviciosPred = new ServiciosPred();
	PredTO predTO = new PredTO();
	PredTO predOne = new PredTO();
	ArrayList<PredTO> predSelected = new ArrayList<PredTO>();
	int cursor = 0;
	List<String> contins = new ArrayList<String>();
//	private Hashtable htContin = new Hashtable();
//
//	public BeanAdmiPred() {
//		htContin.put("  ", "  ");
//		htContin.put("AF", "África");
//		htContin.put("AM", "América");
//		htContin.put("AN", "Antártida");
//		htContin.put("AS", "Asia");
//		htContin.put("EU", "Europa");
//		htContin.put("OC", "Oceanía");
//
//		htContin.put("África", "AF");
//		htContin.put("América", "AM");
//		htContin.put("Antártida", "AN");
//		htContin.put("Asia", "AS");
//		htContin.put("Europa", "EU");
//		htContin.put("Oceanía", "OC");
//	}
	
	public BeanAdmiPred() {
		
	}

	public static Logger getLogger() {
		return logger;
	}

	public void verSelectPred() {
		selectPred();
		cursor = 0;
	}

	public void selectPred() {
		String url = "/pred/selectPred.xhtml";
		predAll = (ArrayList<PredTO>) serviciosPred.callSelectPred();
		predAll.sort((p1, p2) -> (int) p1.comparePredClavex(p2));

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void verInsertPred() {
		String url = "/pred/insertPred.xhtml";

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertPred() {
		/////
//		System.out.println("\nAntes: BeanAdmiPred.insertPred - PredTO");
//		predTO.printPred(predTO);
//		System.out.println("\nAntes: BeanAdmiPred.insertPred - PredOne");
//		predOne.printPred(predOne);

		predTO = (PredTO) serviciosPred.callInsertPred(predOne);

		/////
//		System.out.println("\nDespues: BeanAdmiPred.insertPred - PredTO");
//		predTO.printPred(predTO);
//		System.out.println("\nDespues: BeanAdmiPred.insertPred - PredOne");
//		predOne.printPred(predOne);

		verSelectPred();
	}

	public void verUpdatePred() {
		predSelected.clear();
		for (PredTO pred : predAll) {
			if (pred.getIssele()) {
				predSelected.add(pred);
			}
		}

		if (predSelected.size() > 0) {
			predOne = predSelected.get(0);

			String url = "/pred/updatePred.xhtml";
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("<.<.< No existen predes seleccionados para editar.");
		}
	}

	public void updatePred(boolean isUpdate) {
//		predOne.setContin((String) htContin.get(predOne.getContinente()));
		predTO = (PredTO) serviciosPred.callUpdatePred(predOne, isUpdate);
		goNextPred(true);
	}

	public void verDisablePred() {
		predSelected.clear();
		for (PredTO pred : predAll) {
			if (pred.getIssele()) {
				predSelected.add(pred);
			}
		}

		if (predSelected.size() > 0) {
			predOne = predSelected.get(0);
			
			String url = "/pred/disablePred.xhtml";
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("<*<*< No existen predes seleccionados para borar.");
		}
	}

	public void disablePred(boolean isUpdate) {
		predTO = (PredTO) serviciosPred.callUpdatePred(predOne, isUpdate);
		goNextPred(false);
	}

//	public void addMessage() {
//		String summary = predTO.getIsunieur() ? "Checked" : "Unchecked";
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
//	}

	// Navegación.

	public void goFirstPred(boolean isUpdate) {
		if (predSelected.size() > 0) {

			cursor = 0;
			predOne = predSelected.get(cursor);
//			predOne.setContinente((String) htContin.get(predOne.getContin()));

			String url = "";
			if (isUpdate) {
				url = "/pred/updatePred.xhtml";
			} else {
				url = "/pred/disablePred.xhtml";
			}
			try {

				/////
				System.out.println("Antes goFirstPred");
				predOne.printPred(predOne);

				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
//			this.continent = predOne.getContinente();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void goPriorPred(boolean isUpdate) {
		if (predSelected.size() > 0) {
			if (cursor > 0) {
				cursor--;
				predOne = predSelected.get(cursor);
//				predOne.setContinente((String) htContin.get(predOne.getContin()));

				String url = "";
				if (isUpdate) {
					url = "/pred/updatePred.xhtml";
				} else {
					url = "/pred/disablePred.xhtml";
				}

				try {
					/////
					System.out.println("Antes goPriorPred");
					predOne.printPred(predOne);

					String contextPath = FacesContext.getCurrentInstance().getExternalContext()
							.getApplicationContextPath();
//				this.continent = predOne.getContinente();
					FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void goNextPred(boolean isUpdate) {
		System.out.println ("*****/// Entrando en goNExtPred el curor es " + cursor);
		if (predSelected.size() > 0) {
			if (cursor < predSelected.size() - 1) {
				cursor++;
				predOne = predSelected.get(cursor);
//				predOne.setContinente((String) htContin.get(predOne.getContin()));

				String url = "";
				if (isUpdate) {
					url = "/pred/updatePred.xhtml";
				} else {
					url = "/pred/disablePred.xhtml";
				}
				try {
					String contextPath = FacesContext.getCurrentInstance().getExternalContext()
							.getApplicationContextPath();
					FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
//					if (cursor == predSelected.size() - 1) {
//						verSelectPred();
//					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			else {
				System.out.println ("*****/// antes de llamar a verSelectPred el curor es " + cursor);
				verSelectPred();
			}
		}
	}

	public void goLastPred(boolean isUpdate) {
		if (predSelected.size() > 0) {
			cursor = predSelected.size() - 1;
			if (cursor < 0)
				cursor = 0;
			predOne = predSelected.get(cursor);
//			predOne.setContinente((String) htContin.get(predOne.getContin()));

			String url = "";
			if (isUpdate) {
				url = "/pred/updatePred.xhtml";
			} else {
				url = "/pred/disablePred.xhtml";
			}
			try {
				////
				System.out.println("Antes goLastPred");
				predOne.printPred(predOne);

				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
//			this.continent = predOne.getContinente();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void goAceptPred(boolean isUpdate) {
		predTO = (PredTO) serviciosPred.callUpdatePred(predOne, isUpdate);
		goNextPred(isUpdate);
	}

	public void goCancelPred(Boolean isUpdate) {
		goNextPred(isUpdate);
	}

	public void goAbortPred() {
	}

	public void disableInpred() {

	}

	public void editPred(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
	}

	public void seleccionar(Long inpred) {
		System.out.println("<*> Has seleccionado:" + inpred);
		for (PredTO pred : predAll) {
			if (pred.getInpred() == inpred) {
				pred.setIssele(!pred.getIssele());
				break;
			}
		}
	}

	public ArrayList<PredTO> getPredAll() {
		return predAll;
	}

	public void setPredAll(ArrayList<PredTO> predAll) {
		this.predAll = predAll;
	}

	public PredTO getPredOne() {
		return predOne;
	}

	public void setPredOne(PredTO predOne) {
		this.predOne = predOne;
	}

}