package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.guandera.core.client.service.acceso.ConfigurarAccesoService;

import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ConfigurarAccesoServiceImpl implements ConfigurarAccesoService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void crearRol(Rol rol) {
		ofy().save().entity(rol).now();

	}

	@Override
	public void crearVista(Opcion vista) {
		ofy().save().entity(vista).now();

	}

	@Override
	public void crearMenu(Menu menu) {
		ofy().save().entity(menu).now();

	}

	@Override
	public void crearSubmenu(Submenu submenu) {
		ofy().save().entity(submenu).now();

	}

	@Override
	public void crearAcceso(Acceso acceso) {
		ofy().save().entity(acceso).now();

	}

	@Override
	public List<Rol> consultarListaRoles() {
		return ofy().load().type(Rol.class).list();
	}

	@Override
	public List<Opcion> consultarListaOpciones() {
		return ofy().load().type(Opcion.class).list();
	}

	@Override
	public List<Menu> consultarListaMenus() {
		return ofy().load().type(Menu.class).list();
	}

	@Override
	public List<Acceso> consultarListaAccesos() {
		return ofy().load().type(Acceso.class).list();
	}

	@Override
	public List<Submenu> consultarListaSubmenus() {
		return ofy().load().type(Submenu.class).list();
	}

}