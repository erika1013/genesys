package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoPagoService;
import com.guandera.core.shared.model.TipoPago;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoPagoServiceImpl implements TipoPagoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoPago tipoPago) {
		ofy().save().entity(tipoPago).now();
	}

	@Override
	public TipoPago consultarPorId(Long id) {
		Key<TipoPago> k = Key.create(TipoPago.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoPago> consultarTodos() {
		return ofy().load().type(TipoPago.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoPago.class).count();
		return cont;

	}

	@Override
	public void crear(TipoPago tipoPago) {
		ofy().save().entity(tipoPago).now();
	}

	@Override
	public void eliminar(TipoPago tipoPago) {
		ofy().delete().entity(tipoPago).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoPago.class).count();
		return siguiente + 1;
	}
}