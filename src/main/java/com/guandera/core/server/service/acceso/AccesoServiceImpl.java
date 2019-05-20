package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.AccesoService;
import com.guandera.core.shared.model.acceso.Acceso;

/**
 * 
 * @author FrediJavier
 */
public class AccesoServiceImpl implements AccesoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void actualizar(Acceso acceso) {
		ofy().save().entity(acceso).now();
	}

	@Override
	public Acceso consultarPorId(Long id) {
		Key<Acceso> k = Key.create(Acceso.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Acceso> consultarTodos() {
		return ofy().load().type(Acceso.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Acceso.class).count();
		return cont;
	}

	@Override
	public void crear(Acceso acceso) {

		ofy().save().entity(acceso).now();

	}

	@Override
	public void eliminar(Acceso acceso) {
		ofy().delete().entity(acceso).now();
	}

	@Override
	public int siguienteRegistro() {

		int siguiente = ofy().load().type(Acceso.class).count();
		return siguiente + 1;
	}
}