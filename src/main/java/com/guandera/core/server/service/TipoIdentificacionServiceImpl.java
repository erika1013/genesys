package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.TipoIdentificacionService;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TipoIdentificacion tipoIdentificacion) {
		ofy().save().entity(tipoIdentificacion).now();
	}

	@Override
	public TipoIdentificacion consultarPorId(Long id) {
		Key<TipoIdentificacion> k = Key.create(TipoIdentificacion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TipoIdentificacion> consultarTodos() {
		return ofy().load().type(TipoIdentificacion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TipoIdentificacion.class).count();
		return cont;

	}

	@Override
	public void crear(TipoIdentificacion tipoIdentificacion) {
		ofy().save().entity(tipoIdentificacion).now();
	}

	@Override
	public void eliminar(TipoIdentificacion tipoIdentificacion) {
		ofy().delete().entity(tipoIdentificacion).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TipoIdentificacion.class).count();
		return siguiente + 1;
	}
}