package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoPersonaService;
import com.guandera.core.shared.model.TipoPersona;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoPersonaServiceImpl implements TipoPersonaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoPersona tipoPersona) {
		ofy().save().entity(tipoPersona).now();
	}

	@Override
	public TipoPersona consultarPorId(Long id) {
		Key<TipoPersona> k = Key.create(TipoPersona.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoPersona> consultarTodos() {
		return ofy().load().type(TipoPersona.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoPersona.class).count();
		return cont;

	}

	@Override
	public void crear(TipoPersona tipoPersona) {
		ofy().save().entity(tipoPersona).now();
	}

	@Override
	public void eliminar(TipoPersona tipoPersona) {
		ofy().delete().entity(tipoPersona).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoPersona.class).count();
		return siguiente + 1;
	}
}