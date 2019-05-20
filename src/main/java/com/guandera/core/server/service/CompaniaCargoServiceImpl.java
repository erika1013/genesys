package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CompaniaCargoService;
import com.guandera.core.shared.model.CompaniaCargo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CompaniaCargoServiceImpl implements CompaniaCargoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CompaniaCargo companiaCargo) {
		ofy().save().entity(companiaCargo).now();
	}

	@Override
	public CompaniaCargo consultarPorId(Long id) {

		Key<CompaniaCargo> k = Key.create(CompaniaCargo.class, id);

		return ofy().load().key(k).now();
	}

	@Override
	public List<CompaniaCargo> consultarTodos() {
		return ofy().load().type(CompaniaCargo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CompaniaCargo.class).count();
		return cont;

	}

	@Override
	public void crear(CompaniaCargo companiaCargo) {

		ofy().save().entity(companiaCargo).now();
	}

	@Override
	public void eliminar(CompaniaCargo companiaCargo) {
		ofy().delete().entity(companiaCargo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(CompaniaCargo.class).count();
		return siguiente + 1;
	}
}