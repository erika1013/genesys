package com.guandera.core.client.view;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.guandera.core.client.view.acceso.LoginManagedBean;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.talento.aspirante.client.view.LoginAspiranteManagedBean;

/**
 * 
 * @author Fredi J. Velasco Villarreal
 */
public class BaseManagedBean {

	public void error(String msj) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, msj);
		ctx.addMessage(null, message);
	}

	public void errorTexto(String msj) {

		error(msj);

	}

	public void info(String msj) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msj, msj);
		ctx.addMessage(null, message);
	}

	public void mensaje(String msj) {
		info(msj);

	}

	public void mensajeError(String msj) {
		error(ResourceBundle.getBundle("com.guandera.core.Bundle").getString(msj));

	}

	public void mensajeInfo(String msj) {
		info(ResourceBundle.getBundle("com.guandera.core.Bundle").getString(msj));

	}

	public String usuarioSession() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LoginManagedBean loginMB = (LoginManagedBean) session.getAttribute("LoginMB");
		return loginMB.getUsuario().getUsuarionombre();

	}

	public Long usuarioSessionId() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LoginManagedBean loginMB = (LoginManagedBean) session.getAttribute("LoginMB");
		return loginMB.getUsuario().getUsuarioid();

	}

	public String usuarioSessionUsuario() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LoginManagedBean loginMB = (LoginManagedBean) session.getAttribute("LoginMB");
		return loginMB.getUsuario().getUsuariousuario();

	}

	public Usuario usuario() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		LoginManagedBean loginMB = (LoginManagedBean) session.getAttribute("LoginMB");
		return loginMB.getUsuario();

	}
	
	

	
	

}
