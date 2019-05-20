package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.BancoPagoEstadoService;
import com.guandera.core.shared.model.BancoPagoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class BancoPagoEstadoServiceImpl implements BancoPagoEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(BancoPagoEstado bancoPagoEstado) {
		ofy().save().entity(bancoPagoEstado).now();
	}

	@Override
	public BancoPagoEstado consultarPorId(Long id) {
		Key<BancoPagoEstado> k = Key.create(BancoPagoEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<BancoPagoEstado> consultarTodos() {
		return ofy().load().type(BancoPagoEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(BancoPagoEstado.class).count();
		return cont;

	}

	@Override
	public void crear(BancoPagoEstado bancoPagoEstado) {
		ofy().save().entity(bancoPagoEstado).now();
	}

	@Override
	public void eliminar(BancoPagoEstado bancoPagoEstado) {
		ofy().delete().entity(bancoPagoEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(BancoPagoEstado.class).count();
		return siguiente + 1;
	}
}