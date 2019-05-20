package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.MonedaService;
import com.guandera.core.shared.model.Moneda;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class MonedaServiceImpl implements MonedaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Moneda moneda) {
		ofy().save().entity(moneda).now();
	}

	@Override
	public Moneda consultarPorId(Long id) {
		Key<Moneda> k = Key.create(Moneda.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Moneda> consultarTodos() {
		return ofy().load().type(Moneda.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Moneda.class).count();
		return cont;

	}

	@Override
	public void crear(Moneda moneda) {
		ofy().save().entity(moneda).now();
	}

	@Override
	public void eliminar(Moneda moneda) {
		ofy().delete().entity(moneda).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Moneda.class).count();
		return siguiente + 1;
	}
}