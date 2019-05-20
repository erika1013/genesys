/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.MenuService;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Submenu;

/**
 * 
 * @author Fredi Javier Velasco Villarreal - GUANDERA S.A.S.
 */
public class MenuServiceImpl implements MenuService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7135198165093351128L;

	@Override
	public void actualizar(Menu menu) {
		ofy().save().entity(menu).now();
	}

	@Override
	public void actualizarSubmenu(Submenu submenu) {
		ofy().save().entity(submenu).now();

	}

	@Override
	public Menu consultarPorId(Long id) {
		Key<Menu> k = Key.create(Menu.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Submenu> consultarSubmenuPorMenu(Long menuid) {

		Key<Menu> kmenu = Key.create(Menu.class, menuid);
		return ofy().load().type(Submenu.class).filter("menu", kmenu).list();
	}

	@Override
	public List<Menu> consultarTodos() {
		return ofy().load().type(Menu.class).list();
	}

	@Override
	public List<Opcion> consultarOpciones() {

		return ofy().load().type(Opcion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Menu.class).count();
		return cont;
	}

	@Override
	public void crear(Menu menu) {
		ofy().save().entity(menu).now();
	}

	@Override
	public void crearSubmenu(Submenu submenu) {

		ofy().save().entity(submenu).now();
	}

	@Override
	public void eliminar(Menu menu) {

		Key<Menu> kmenu = Key.create(Menu.class, menu.getMenuid());

		List<Submenu> listaOpciones = new ArrayList<Submenu>();

		listaOpciones = ofy().load().type(Submenu.class).filter("menu", kmenu).list();

		for (Submenu submenu : listaOpciones) {

			ofy().delete().entity(submenu).now();

		}

		ofy().delete().entity(menu).now();

	}

	@Override
	public void eliminarSubmenu(Submenu submenu) {
		ofy().delete().entity(submenu).now();

	}

	@Override
	public Long siguienteRegistro() {
		long siguiente = ofy().load().type(Menu.class).count();
		return siguiente + 1;
	}

	@Override
	public Long siguienteSubmenu() {

		long siguiente = ofy().load().type(Submenu.class).count();

		return siguiente + 1;
	}
}