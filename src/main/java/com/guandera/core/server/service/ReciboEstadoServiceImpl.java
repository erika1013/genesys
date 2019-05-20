package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ReciboEstadoService;
import com.guandera.core.shared.model.ReciboEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ReciboEstadoServiceImpl implements ReciboEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ReciboEstado reciboEstado) {
		ofy().save().entity(reciboEstado).now();
	}

	@Override
	public ReciboEstado consultarPorId(Long id) {
		Key<ReciboEstado> k = Key.create(ReciboEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ReciboEstado> consultarTodos() {
		return ofy().load().type(ReciboEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ReciboEstado.class).count();
		return cont;

	}

	@Override
	public void crear(ReciboEstado reciboEstado) {
		ofy().save().entity(reciboEstado).now();
	}

	@Override
	public void eliminar(ReciboEstado reciboEstado) {
		ofy().delete().entity(reciboEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ReciboEstado.class).count();
		return siguiente + 1;
	}
}