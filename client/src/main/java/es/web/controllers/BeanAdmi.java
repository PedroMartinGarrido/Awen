package es.web.controllers;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("beanAdmi")
@ManagedBean(name="beanAdmi")
@Scope("application")
public class BeanAdmi {
	private static final Logger logger = LoggerFactory.getLogger(BeanAdmi.class);

	public void irMenu() {
		String url = "/mainawen.xhtml";
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
