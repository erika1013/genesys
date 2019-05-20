package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoServicioService;
import com.guandera.core.shared.model.TipoCobro;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoServicioServiceImpl implements TipoServicioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoServicio tipoServicio) {
		ofy().save().entity(tipoServicio).now();
	}

	@Override
	public TipoServicio consultarPorId(Long id) {
		Key<TipoServicio> k = Key.create(TipoServicio.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoServicio> consultarTodos() {
		return ofy().load().type(TipoServicio.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoServicio.class).count();
		return cont;

	}

	@Override
	public void crear(TipoServicio tipoServicio) {
		ofy().save().entity(tipoServicio).now();
	}

	@Override
	public void eliminar(TipoServicio tipoServicio) {
		ofy().delete().entity(tipoServicio).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoServicio.class).count();
		return siguiente + 1;
	}

	@Override
	public List<TipoCobro> consultarTipoCobro() {
		return ofy().load().type(TipoCobro.class).list();
	}
}