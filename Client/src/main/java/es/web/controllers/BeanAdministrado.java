package es.web.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import awen.commons.to.PaisTO;
import es.web.services.ServiciosPais;

@Component("beanAdministrado")
@ManagedBean(name = "beanAdministrado")
@Scope("application")
public class BeanAdministrado {

	private static final Logger logger = LoggerFactory.getLogger(BeanAdministrado.class);
	private String usuario = "";
	private String contrasenya = ""; 
	ArrayList<PaisTO> paisAll = new ArrayList<PaisTO>();
	ServiciosPais serviciosPais = new ServiciosPais();
	PaisTO paisTO = new PaisTO();
	PaisTO paisOne = new PaisTO();
	ArrayList<PaisTO> paisSelected = new ArrayList<PaisTO>();
	int cursor = 0;

	public static Logger getLogger() {
		return logger;
	}

	public void irMenu() {
		String url = "/mainawen.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void irPais() {
		cursor = 0;
		String url = "/pais/selectPais.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String enviar() {
		System.out.print("Metodo enviar(): Usuario: " + usuario + " - Contraseña: " + contrasenya);

		String url = "mainawen.xhtml";
		return url + "?faces-redirect=true";
	}

	public void capturePais() {
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
		irPais();
	}

	public void selectPais() {
		String url = "/pais/selectPais.xhtml";
		paisAll = (ArrayList<PaisTO>) serviciosPais.callSelectPais();
		paisAll.sort((p1, p2) -> (int) p1.comparePaisNopais(p2));
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void filterUpdatePais () {
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
		}
		else {
			System.out.println("<<< No existen paises seleccionados para editar.");
		}
	}

	public void updatePais (boolean isUpdate) {
		paisTO = (PaisTO) serviciosPais.callUpdatePais(paisOne, isUpdate);
		goNextPais(true);
	}
	
	public void filterDisablePais () {
		paisSelected.clear();
		for (PaisTO pais : paisAll) {	
			if (pais.getIssele()) {
				paisSelected.add(pais);				
				
				System.out.println("<<<<<<<<1233>>>>>> inpais: "  + pais.getInpais());
				System.out.println("<<<<<<<<1233>>>>>> nopais: "  + pais.getNopais());
				System.out.println("<<<<<<<<1233>>>>>> contin: "  + pais.getContin());
				System.out.println("<<<<<<<<1233>>>>>> comeur: "  + pais.getComeur());
				System.out.println("<<<<<<<<1233>>>>>> borlog: "  + pais.getBorlog());
				System.out.println("--------------------------------------------------");
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
		}
		else {
			System.out.println("<<< No existen paises seleccionados para borar.");
		}
	}

	public void disablePais (boolean isUpdate) {
		//paisOne.setBorlog("S");
		paisTO = (PaisTO) serviciosPais.callUpdatePais(paisOne, isUpdate);
		goNextPais(false);
	}
	
	public void goFirstPais(boolean isUpdate) {
		cursor = 0;
		paisOne = paisSelected.get(cursor);
		
		String url = "";
		if (isUpdate) {
			url = "/pais/updatePais.xhtml";
		}
		else {
			url = "/pais/disablePais.xhtml";
		}

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goPriorPais(boolean isUpdate) {
		if (cursor > 0) {
			cursor --;
			paisOne = paisSelected.get(cursor);
			
			String url = "";
			if (isUpdate) {
				url = "/pais/updatePais.xhtml";
			}
			else {
				url = "/pais/disablePais.xhtml";
			}

			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void goNextPais(boolean isUpdate) {
		cursor ++;
		if (cursor == paisSelected.size()) {
			irPais();
		}
		else {
			//cursor = paisSelected.size() - 1;
			paisOne = paisSelected.get(cursor);
			
			String url = "";
			if (isUpdate) {
				url = "/pais/updatePais.xhtml";
			}
			else {
				url = "/pais/disablePais.xhtml";
			}

			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
			
	}
	
	public void goLastPais(boolean isUpdate) {
		System.out.println ("Estoy entrado en goLastPais con el cursor " + cursor);
		cursor = paisSelected.size() - 1;
		if (cursor < 0) cursor = 0;
		paisOne = paisSelected.get(cursor);
		
		String url = "";
		if (isUpdate) {
			url = "/pais/updatePais.xhtml";
		}
		else {
			url = "/pais/disablePais.xhtml";
		}

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goAceptPais(boolean isUpdate) {
		paisTO = (PaisTO) serviciosPais.callUpdatePais(paisOne, isUpdate);
	}
	
	public void goCancelPais(Boolean isUpdate) {
		goNextPais(isUpdate);
	}
	
	public void goAbortPais() {
	}
		
	
	public void disableInpais () {
		
	}
	
	public void editPais(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
    }
	
	public void seleccionar(String inpais) {
		System.out.println("<*> Has seleccionado:" + inpais);
		for (PaisTO pais : paisAll) {	
			if (pais.getInpais().equalsIgnoreCase(inpais)) {
				pais.setIssele(!pais.getIssele());
				break;
			}
		}
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
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

}