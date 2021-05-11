package es.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import awen.commons.to.ProvTO;
import es.web.services.ServiciosProv;

@Component("beanAdmiProv")
@ManagedBean(name="beanAdmiProv")
@Scope("application")
public class BeanAdmiProv {

	private static final Logger logger = LoggerFactory.getLogger(BeanAdmiProv.class);
	ArrayList<ProvTO> provAll = new ArrayList<ProvTO>();
	ServiciosProv serviciosProv = new ServiciosProv();
	ProvTO provTO = new ProvTO();
	ProvTO provOne = new ProvTO();
	ArrayList<ProvTO> provSelected = new ArrayList<ProvTO>();
	int cursor = 0;
	List<String> listpais = new ArrayList<String>();

	public static Logger getLogger() {
		return logger;
	}

	public void irProv() {
		selectProv();
		cursor = 0;
	}

	public void captureProv() {
		String url = "/prov/insertProv.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertProv() {
		provTO = (ProvTO) serviciosProv.callInsertProv(provOne);
		irProv();
	}

	public void selectProv() {
		String url = "/prov/selectProv.xhtml";
		provAll = (ArrayList<ProvTO>) serviciosProv.callSelectProv();
		provAll.sort((p1, p2) -> (int) p1.compareProvProvin(p2));
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	public void filterUpdateProv () {
		provSelected.clear();
		for (ProvTO prov : provAll) {	
			if (prov.getIssele()) {
				provSelected.add(prov);
			}
		}		
		
		if (provSelected.size() > 0) {
			provOne = provSelected.get(0);
			String url = "/prov/updateProv.xhtml";
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("<<< No existen provincias seleccionadas para editar.");
		}
	}

	public void updateProv (boolean isUpdate) {
		provOne.setInpais(provOne.getInpais().substring(0, 2));
		provTO = (ProvTO) serviciosProv.callUpdateProv(provOne, isUpdate);
		goNextProv(true);
	}
	
	public void filterDisableProv () {
		provSelected.clear();
		for (ProvTO prov : provAll) {	
			if (prov.getIssele()) {
				provSelected.add(prov);				
			}
		}		
		
		if (provSelected.size() > 0) {
			provOne = provSelected.get(0);
			String url = "/prov/disableProv.xhtml";
			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("<<< No existen provincias seleccionadas para borar.");
		}
	}

	public void disableProv (boolean isUpdate) {
		//provOne.setBorlog("S");
		provTO = (ProvTO) serviciosProv.callUpdateProv(provOne, isUpdate);
		goNextProv(false);
	}
	
	public void goFirstProv(boolean isUpdate) {
		cursor = 0;
		provOne = provSelected.get(cursor);
		
		String url = "";
		if (isUpdate) {
			url = "/prov/updateProv.xhtml";
		}
		else {
			url = "/prov/disableProv.xhtml";
		}

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goPriorProv(boolean isUpdate) {
		if (cursor > 0) {
			cursor --;
			provOne = provSelected.get(cursor);
			
			String url = "";
			if (isUpdate) {
				url = "/prov/updateProv.xhtml";
			}
			else {
				url = "/prov/disableProv.xhtml";
			}

			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void goNextProv(boolean isUpdate) {
		cursor ++;
		if (cursor == provSelected.size()) {
			irProv();
		}
		else {
			//cursor = provSelected.size() - 1;
			provOne = provSelected.get(cursor);
			
			String url = "";
			if (isUpdate) {
				url = "/prov/updateProv.xhtml";
			}
			else {
				url = "/prov/disableProv.xhtml";
			}

			try {
				String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
				FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
			
	}
	
	public void goLastProv(boolean isUpdate) {
		System.out.println ("Estoy entrado en goLastProv con el cursor " + cursor);
		cursor = provSelected.size() - 1;
		if (cursor < 0) cursor = 0;
		provOne = provSelected.get(cursor);
		
		String url = "";
		if (isUpdate) {
			url = "/prov/updateProv.xhtml";
		}
		else {
			url = "/prov/disableProv.xhtml";
		}

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void goAceptProv(boolean isUpdate) {
		provTO = (ProvTO) serviciosProv.callUpdateProv(provOne, isUpdate);
	}
	
	public void goCancelProv(Boolean isUpdate) {
		goNextProv(isUpdate);
	}
	
	public void goAbortProv() {
	}
		
	
	public void disableInprov () {
		
	}
	
	public void editProv(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
    }
	
	public void seleccionar(String inprov) {
		System.out.println("<*> Has seleccionado:" + inprov);
		for (ProvTO prov : provAll) {	
			if (prov.getInprov().equalsIgnoreCase(inprov)) {
				prov.setIssele(!prov.getIssele());
				break;
			}
		}
	}
	
	public ArrayList<ProvTO> getProvAll() {
		return provAll;
	}

	public void setProvAll(ArrayList<ProvTO> provAll) {
		this.provAll = provAll;
	}

	public ProvTO getProvOne() {
		return provOne;
	}

	public void setProvOne(ProvTO provOne) {
		this.provOne = provOne;
	}

	public List<String> getListpais() {
		return listpais;
	}

	public void setListpais(List<String> listpais) {
		this.listpais = listpais;
	}

	
}