package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.LoginService;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal-GUANDERA S.A.S.
 */

public class LoginServiceImpl implements LoginService, Serializable {

	private static final long serialVersionUID = -5158903422244484923L;

	@Override
	public Usuario CargarUsuario(String nombreusuario) {

		return ofy().load().type(Usuario.class).filter("usuariousuario", nombreusuario).first().now();

	}

	@Override
	public List<Menu> consultarMenuPorRol(Long rolid) {

		Key<Rol> krol = Key.create(Rol.class, rolid);

		List<Menu> listaMenu = new ArrayList<Menu>();

		List<Acceso> listaAcceso = ofy().load().type(Acceso.class).filter("rol", krol).list();

		for (Acceso acceso1 : listaAcceso) {

			Menu menu1 = new Menu();

			menu1 = acceso1.getMenu();

			listaMenu.add(menu1);

		}

		return listaMenu;

	}

	@Override
	public List<Opcion> consultarOpcionesPorMenu(Long menuid) {

		Key<Menu> kmenu = Key.create(Menu.class, menuid);

		List<Opcion> listaOpcion = new ArrayList<Opcion>();

		List<Submenu> listaSubmenu = ofy().load().type(Submenu.class).filter("menu", kmenu).order("submenuorden")
				.list();

		for (Submenu submenu1 : listaSubmenu) {

			Opcion opcion1 = new Opcion();

			opcion1 = submenu1.getOpcion();

			listaOpcion.add(opcion1);

		}

		return listaOpcion;

	}

	@Override
	public boolean existeUsuario(String usuariousuario) {
		Usuario usuario1 = ofy().load().type(Usuario.class).filter("usuariousuario", usuariousuario).first().now();

		if (usuario1 != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verificarUsuario(Usuario usuario) {

		Usuario usuario1 = ofy().load().type(Usuario.class).filter("usuariousuario", usuario.getUsuariousuario())
				.filter("usuarioclave", usuario.getUsuarioclave()).first().now();

		if (usuario1 != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void actualizarIngreso(Usuario usuario) {
		ofy().save().entity(usuario).now();

	}

}
