package es.web.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
	private String nombre = "";
	private String contrasenya = "";
	ArrayList<PaisTO> paisAll = new ArrayList<PaisTO>();
	ServiciosPais serviciosPais = new ServiciosPais();
	PaisTO paisTO = new PaisTO();
	PaisTO paisOne = new PaisTO();

	public static Logger getLogger() {
		return logger;
	}

	public void irMenu() {
		String url = "/mainawen.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String enviar() {
		System.out.print("Metodo enviar(): Nombre: " + nombre + " - Contraseña: " + contrasenya);

		String url = "mainawen.xhtml";
		return url + "?faces-redirect=true";
	}

	public void capturePais() {
		String url = "/pais/insertPais.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertPais() {
		paisTO = (PaisTO) serviciosPais.callInsertPais(paisOne);
		String url = "/mainawen.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectPais() {
		String url = "/pais/selectPais.xhtml";
		paisAll = (ArrayList<PaisTO>) serviciosPais.callSelectPais();
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return url + "?faces-redirect=true";
		return;
	}

	public String getNombre() {
		System.out.println("Estoy en getNombre....");
		return nombre;
	}

	public void setNombre(String nombre) {
		System.out.println("Estoy en setNombre....");
		this.nombre = nombre;
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