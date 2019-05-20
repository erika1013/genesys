package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.OpcionService;
import com.guandera.core.shared.model.acceso.Opcion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class OpcionServiceImpl implements OpcionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Opcion opcion) {
		ofy().save().entity(opcion).now();
	}

	@Override
	public Opcion consultarPorId(Long id) {
		Key<Opcion> k = Key.create(Opcion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Opcion> consultarTodos() {
		return ofy().load().type(Opcion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Opcion.class).count();
		return cont;

	}

	@Override
	public void crear(Opcion opcion) {
		ofy().save().entity(opcion).now();
	}

	@Override
	public void eliminar(Opcion opcion) {
		ofy().delete().entity(opcion).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Opcion.class).count();
		return siguiente + 1;
	}
}