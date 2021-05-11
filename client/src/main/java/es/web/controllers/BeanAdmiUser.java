package es.web.controllers;

import javax.faces.bean.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("beanAdminUser")
@ManagedBean(name="beanAdmiUser")
@Scope("application")
public class BeanAdmiUser {

	private static final Logger logger = LoggerFactory.getLogger(BeanAdmiUser.class);
	private String usuario = "";
	private String contras = ""; 

	public static Logger getLogger() {
		return logger;
	}

	public String enviar() {
		String url = "mainawen.xhtml";
		return url + "?faces-redirect=true";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContras() {
		return contras;
	}

	public void setContras(String contras) {
		this.contras = contras;
	}

}