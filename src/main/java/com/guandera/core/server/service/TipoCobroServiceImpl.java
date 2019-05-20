package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoCobroService;
import com.guandera.core.shared.model.TipoCobro;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoCobroServiceImpl implements TipoCobroService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoCobro tipoCobro) {
		ofy().save().entity(tipoCobro).now();
	}

	@Override
	public TipoCobro consultarPorId(Long id) {
		Key<TipoCobro> k = Key.create(TipoCobro.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoCobro> consultarTodos() {
		return ofy().load().type(TipoCobro.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoCobro.class).count();
		return cont;

	}

	@Override
	public void crear(TipoCobro tipoCobro) {
		ofy().save().entity(tipoCobro).now();
	}

	@Override
	public void eliminar(TipoCobro tipoCobro) {
		ofy().delete().entity(tipoCobro).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoCobro.class).count();
		return siguiente + 1;
	}
}