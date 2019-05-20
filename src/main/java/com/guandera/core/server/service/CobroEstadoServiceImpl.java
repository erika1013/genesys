package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CobroEstadoService;
import com.guandera.core.shared.model.CobroEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CobroEstadoServiceImpl implements CobroEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CobroEstado cobroEstado) {
		ofy().save().entity(cobroEstado).now();
	}

	@Override
	public CobroEstado consultarPorId(Long id) {
		Key<CobroEstado> k = Key.create(CobroEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<CobroEstado> consultarTodos() {
		return ofy().load().type(CobroEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CobroEstado.class).count();
		return cont;

	}

	@Override
	public void crear(CobroEstado cobroEstado) {
		ofy().save().entity(cobroEstado).now();
	}

	@Override
	public void eliminar(CobroEstado cobroEstado) {
		ofy().delete().entity(cobroEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(CobroEstado.class).count();
		return siguiente + 1;
	}
}