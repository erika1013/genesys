package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoAcudienteService;
import com.guandera.core.shared.model.TipoAcudiente;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoAcudienteServiceImpl implements TipoAcudienteService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoAcudiente tipoAcudiente) {
		ofy().save().entity(tipoAcudiente).now();
	}

	@Override
	public TipoAcudiente consultarPorId(Long id) {
		Key<TipoAcudiente> k = Key.create(TipoAcudiente.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoAcudiente> consultarTodos() {
		return ofy().load().type(TipoAcudiente.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoAcudiente.class).count();
		return cont;

	}

	@Override
	public void crear(TipoAcudiente tipoAcudiente) {
		ofy().save().entity(tipoAcudiente).now();
	}

	@Override
	public void eliminar(TipoAcudiente tipoAcudiente) {
		ofy().delete().entity(tipoAcudiente).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoAcudiente.class).count();
		return siguiente + 1;
	}
}