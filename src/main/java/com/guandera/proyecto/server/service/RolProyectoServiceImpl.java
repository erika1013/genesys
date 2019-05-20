package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.RolProyectoService;
import com.guandera.proyecto.shared.model.RolProyecto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class RolProyectoServiceImpl implements RolProyectoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(RolProyecto itemRolProyecto) {
		ofy().save().entity(itemRolProyecto).now();
	}

	@Override
	public RolProyecto consultarPorId(Long id) {
		Key<RolProyecto> k = Key.create(RolProyecto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<RolProyecto> consultarTodos() {
		return ofy().load().type(RolProyecto.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(RolProyecto.class).count();
		return cont;

	}

	@Override
	public void crear(RolProyecto itemRolProyecto) {
		ofy().save().entity(itemRolProyecto).now();
	}

	@Override
	public void eliminar(RolProyecto itemRolProyecto) {
		ofy().delete().entity(itemRolProyecto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(RolProyecto.class).count();
		return siguiente + 1;
	}

}