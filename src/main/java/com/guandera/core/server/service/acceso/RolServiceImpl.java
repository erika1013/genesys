package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.RolService;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Rol;

/**
 * 
 * @author FrediJavier
 */
public class RolServiceImpl implements RolService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Rol rol) {
		ofy().save().entity(rol).now();
	}

	@Override
	public List<Acceso> consultarAccesosPorRol(Long rolid) {

		Key<Rol> krol = Key.create(Rol.class, rolid);
		return ofy().load().type(Acceso.class).filter("rol", krol).list();

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
	public List<Menu> consultarMenus() {

		return ofy().load().type(Menu.class).list();

	}

	@Override
	public Rol consultarPorId(Long id) {

		Key<Rol> k = Key.create(Rol.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Rol> consultarTodos() {
		return ofy().load().type(Rol.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Rol.class).count();
		return cont;
	}

	@Override
	public void crear(Rol rol) {

		ofy().save().entity(rol).now();

	}

	@Override
	public void crearAcceso(Acceso acceso) {

		ofy().save().entity(acceso).now();
	}

	@Override
	public void eliminar(Rol rol) {
		ofy().delete().entity(rol).now();

	}

	@Override
	public void eliminarAcceso(Acceso acceso) {
		ofy().delete().entity(acceso).now();

	}

	@Override
	public Long siguienteAcceso() {

		long siguiente = ofy().load().type(Acceso.class).count();
		return siguiente + 1;

	}

	@Override
	public Long siguienteRegistro() {
		long siguiente = ofy().load().type(Rol.class).count();
		return siguiente + 1;
	}
}