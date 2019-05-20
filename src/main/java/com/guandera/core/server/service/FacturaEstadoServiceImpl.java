package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.FacturaEstadoService;
import com.guandera.core.shared.model.FacturaEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class FacturaEstadoServiceImpl implements FacturaEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(FacturaEstado facturaEstado) {
		ofy().save().entity(facturaEstado).now();
	}

	@Override
	public FacturaEstado consultarPorId(Long id) {
		Key<FacturaEstado> k = Key.create(FacturaEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<FacturaEstado> consultarTodos() {
		return ofy().load().type(FacturaEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(FacturaEstado.class).count();
		return cont;

	}

	@Override
	public void crear(FacturaEstado facturaEstado) {
		ofy().save().entity(facturaEstado).now();
	}

	@Override
	public void eliminar(FacturaEstado facturaEstado) {
		ofy().delete().entity(facturaEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(FacturaEstado.class).count();
		return siguiente + 1;
	}
}