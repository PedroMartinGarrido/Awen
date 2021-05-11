package es.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Hashtable;
import java.util.List;

//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

//import org.primefaces.event.CellEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import awen.commons.to.PaisTO;
import es.web.services.ServiciosPais;

@Component("beanAdmiPais")
@ManagedBean(name = "beanAdmiPais")
@Scope("application")
public class BeanAdmiPais {

	private static final Logger logger = LoggerFactory.getLogger(BeanAdmiPais.class);
	ArrayList<PaisTO> paisAll = new ArrayList<PaisTO>();
	ServiciosPais serviciosPais = new ServiciosPais();
	PaisTO paisTO = new PaisTO();
	PaisTO paisOne = new PaisTO();
	ArrayList<PaisTO> paisSelected = new ArrayList<PaisTO>();
	int cursor = 0;
	List<String> listcontin = new ArrayList<String>();
//	private Hashtable htContin = new Hashtable();
//
//	public BeanAdmiPais() {
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

	public static Logger getLogger() {
		return logger;
	}

	public void verSelectPais() {
		selectPais();
		cursor = 0;
		
	}

	public void selectPais() {
		String url = "/pais/selectPais.xhtml";

		paisAll = (ArrayList<PaisTO>) serviciosPais.callSelectPais();
		paisAll.sort((p1, p2) -> (int) p1.comparePaisNopais(p2));
		for (PaisTO pais : paisAll) {
//			pais.setContinente((String) htContin.get(pais.getContin()));
		}

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void verInsertPais() {
		String url = "/pais/insertPais.xhtml";

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertPais() {
		paisTO = (PaisTO) serviciosPais.callInsertPais(paisOne);
		verSelectPais();
	}

	public void verUpdatePais() {
		paisSelected.clear();
		for (PaisTO pais : paisAll) {
			if (pais.getIssele()) {
				paisSelected.add(pais);
			}
		}

		if (paisSelected.size() > 0) {
			paisOne = paisSelected.get(0);

			String url = "/pais/updatePais.xhtml";
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("<.<.< No existen paises seleccionados para editar.");
		}
	}

	public void updatePais(boolean isUpdate) {
//		paisOne.setContin((String) htContin.get(paisOne.getContinente()));
		paisTO = (PaisTO) serviciosPais.callUpdatePais(paisOne, isUpdate);
		goNextPais(true);
	}

	public void verDisablePais() {
		paisSelected.clear();
		for (PaisTO pais : paisAll) {
			if (pais.getIssele()) {
				paisSelected.add(pais);
			}
		}

		if (paisSelected.size() > 0) {
			paisOne = paisSelected.get(0);
			
			String url = "/pais/disablePais.xhtml";
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("<*<*< No existen paises seleccionados para borar.");
		}
	}

	public void disablePais(boolean isUpdate) {
		paisTO = (PaisTO) serviciosPais.callUpdatePais(paisOne, isUpdate);
		goNextPais(false);
	}

//	public void addMessage() {
//		String summary = paisTO.getIsunieur() ? "Checked" : "Unchecked";
//		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
//	}

	// Navegación.

	public void goFirstPais(boolean isUpdate) {
		if (paisSelected.size() > 0) {

			cursor = 0;
			paisOne = paisSelected.get(cursor);
//			paisOne.setContinente((String) htContin.get(paisOne.getContin()));

			String url = "";
			if (isUpdate) {
				url = "/pais/updatePais.xhtml";
			} else {
				url = "/pais/disablePais.xhtml";
			}
			try {

				/////
				System.out.println("Antes goFirstPais");
				paisOne.printPais(paisOne);

				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
//			this.continent = paisOne.getContinente();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void goPriorPais(boolean isUpdate) {
		if (paisSelected.size() > 0) {
			if (cursor > 0) {
				cursor--;
				paisOne = paisSelected.get(cursor);
//				paisOne.setContinente((String) htContin.get(paisOne.getContin()));

				String url = "";
				if (isUpdate) {
					url = "/pais/updatePais.xhtml";
				} else {
					url = "/pais/disablePais.xhtml";
				}

				try {
					/////
					System.out.println("Antes goPriorPais");
					paisOne.printPais(paisOne);

					String contextPath = FacesContext.getCurrentInstance().getExternalContext()
							.getApplicationContextPath();
//				this.continent = paisOne.getContinente();
					FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void goNextPais(boolean isUpdate) {
		if (paisSelected.size() > 0) {
			if (cursor < paisSelected.size() - 1) {
				cursor++;
				paisOne = paisSelected.get(cursor);
//				paisOne.setContinente((String) htContin.get(paisOne.getContin()));

				String url = "";
				if (isUpdate) {
					url = "/pais/updatePais.xhtml";
				} else {
					url = "/pais/disablePais.xhtml";
				}
				try {
					String contextPath = FacesContext.getCurrentInstance().getExternalContext()
							.getApplicationContextPath();
					FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
//					if (cursor == paisSelected.size() - 1) {
//						verSelectPais();
//					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			else {
				System.out.println ("*****/// antes de llamar a verSelectPais el curor es " + cursor);
				verSelectPais();
			}
		}
	}

	public void goLastPais(boolean isUpdate) {
		if (paisSelected.size() > 0) {
			cursor = paisSelected.size() - 1;
			if (cursor < 0)
				cursor = 0;
			paisOne = paisSelected.get(cursor);
//			paisOne.setContinente((String) htContin.get(paisOne.getContin()));

			String url = "";
			if (isUpdate) {
				url = "/pais/updatePais.xhtml";
			} else {
				url = "/pais/disablePais.xhtml";
			}
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
//			this.continent = paisOne.getContinente();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void goAceptPais(boolean isUpdate) {
		paisTO = (PaisTO) serviciosPais.callUpdatePais(paisOne, isUpdate);
		goNextPais(isUpdate);
	}

	public void goCancelPais(Boolean isUpdate) {
		goNextPais(isUpdate);
	}

	public void goAbortPais() {
	}

	public void disableInpais() {

	}

//	public void editPais(CellEditEvent event) {
//		Object oldValue = event.getOldValue();
//		Object newValue = event.getNewValue();
//	}

	public void clikissele(Long inpais) {
		for (PaisTO pais : paisAll) {
			if (pais.getInpais() == inpais) {
				pais.setIssele(!pais.getIssele());
				break;
			}
		}
	}

	public void clikunieur (Long inpais) {
		for (PaisTO pais : paisAll) {
			if (pais.getInpais() == inpais) {
				pais.setUnieur(!pais.getUnieur());
				break;
			}
		}
	}
		
	public ArrayList<PaisTO> getPaisAll() {
		return paisAll;
	}

	public void setPaisAll(ArrayList<PaisTO> paisAll) {
		this.paisAll = paisAll;
	}

	public PaisTO getPaisOne() {
		return paisOne;
	}

	public void setPaisOne(PaisTO paisOne) {
		this.paisOne = paisOne;
	}

	public List<String> getContins() {
		listcontin.clear();
		listcontin.add("  ");
		listcontin.add("África");
		listcontin.add("América");
		listcontin.add("Antártida");
		listcontin.add("Asia");
		listcontin.add("Europa");
		listcontin.add("Oceanía");
		return listcontin;
	}

	public void setContins(List<String> listcontin) {
		this.listcontin = listcontin;
	}

//	public Hashtable getHtContin() {
//		return htContin;
//	}
//
//	public void setHtContin(Hashtable htContin) {
//		this.htContin = htContin;
//	}

}