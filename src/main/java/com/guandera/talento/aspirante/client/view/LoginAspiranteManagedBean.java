/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.talento.aspirante.client.view;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.talento.aspirante.client.service.LoginAspiranteService;
import com.guandera.talento.aspirante.server.service.LoginAspiranteServiceImpl;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;

/**
 * 
 * @author Fredi J. Velasco Villarreal
 */
@ManagedBean(name = "LoginAspiranteMB")
@SessionScoped
public class LoginAspiranteManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private LoginAspiranteService loginAspiranteService;
	private AspiranteAcceso aspiranteAcceso = new AspiranteAcceso();
	private Aspirante aspirante = new Aspirante();

	private boolean autenticado = false;

	private List<Menu> listaMenu;

	private MenuModel menu;

	public LoginAspiranteManagedBean() {

		loginAspiranteService = new LoginAspiranteServiceImpl();

	}

	private void cargarMenuAspiranteAcceso() {

		menu = new DefaultMenuModel();
		DefaultSubMenu submenu = new DefaultSubMenu("Menu Principal");

		DefaultMenuItem item;

		List<Opcion> listaOpcion;

		for (Menu menu1 : listaMenu) {

			submenu = new DefaultSubMenu("Hoja de Vida", "fa fa-book");
			// menu.addElement(submenu);

			item = new DefaultMenuItem("Hoja Vida");

			item.setUrl("Detalle.jsf");
			submenu.addElement(item);

			menu.addElement(submenu);

		}

	}

	public LoginAspiranteService getLoginAspiranteService() {
		return loginAspiranteService;
	}

	public MenuModel getMenu() {
		return menu;
	}

	public AspiranteAcceso getAspiranteAcceso() {
		return aspiranteAcceso;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public String login() {

		String md51 = ("X7B*" + aspiranteAcceso.getClave());
		String aspiranteUsuario = aspiranteAcceso.getCorreo().trim();

		autenticado = false;
		if (loginAspiranteService.existeUsuario(aspiranteUsuario)) {

			
			String md5 = md5Encode(md51);
			

			AspiranteAcceso aspiranteAcceso1 = new AspiranteAcceso();

			aspiranteAcceso1.setClave(md5.trim());
			aspiranteAcceso1.setCorreo(aspiranteUsuario);

			autenticado = loginAspiranteService.verificarAspiranteAcceso(aspiranteAcceso1);
			
			aspiranteAcceso = loginAspiranteService.CargarAspiranteAcceso(aspiranteAcceso1);

			if (autenticado) {


				aspiranteAcceso = loginAspiranteService.CargarAspiranteAcceso(aspiranteAcceso1);

				aspirante = loginAspiranteService.CargarAspirante(aspiranteAcceso);

				setAspiranteAcceso(aspiranteAcceso);

				aspiranteAcceso.setUltimoAcceso(new Date());
				loginAspiranteService.actualizarUtimoAcceso(aspiranteAcceso);

				//cargarMenuAspiranteAcceso();

			} else {

				mensaje("Acceso NO Autorizado!!");
			}

		} else {

			mensaje("El usuario, no se  encuentra registrado en nuestro sistema!!");

		}

		// return autenticado ? "index" : "login";

		if (autenticado) {

			return "hojaVida/Index.jsf";

		} else {

			return "login";
		}

	}

	public String logout() {

		if (autenticado) {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.invalidate();
			aspiranteAcceso = null;
			autenticado = false;

		}

		autenticado = false;

		return "/login?faces-refirect=true";

	}

	public static String md5Encode(String texto) {
		String output = "";
		try {
			byte[] defaultBytes = texto.getBytes();
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}

			output = hexString + "";

		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {

		}
		return output;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public void setLoginAspiranteService(LoginAspiranteService loginAspiranteService) {
		this.loginAspiranteService = loginAspiranteService;
	}

	public void setMenu(MenuModel menu) {
		this.menu = menu;
	}

	public void setAspiranteAcceso(AspiranteAcceso aspiranteAcceso) {
		this.aspiranteAcceso = aspiranteAcceso;
	}

	public Aspirante getAspirante() {
		return aspirante;
	}

	public void setAspirante(Aspirante aspirante) {
		this.aspirante = aspirante;
	}

}
