package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ProveedorPagoEstadoService;
import com.guandera.core.shared.model.ProveedorPagoEstado;

public class ProveedorPagoEstadoServiceImpl implements ProveedorPagoEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ProveedorPagoEstado estado) {
		ofy().save().entity(estado).now();
	}

	@Override
	public ProveedorPagoEstado consultarPorId(Long id) {
		Key<ProveedorPagoEstado> k = Key.create(ProveedorPagoEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ProveedorPagoEstado> consultarTodos() {
		return ofy().load().type(ProveedorPagoEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ProveedorPagoEstado.class).count();
		return cont;

	}

	@Override
	public void crear(ProveedorPagoEstado estado) {
		ofy().save().entity(estado).now();
	}

	@Override
	public void eliminar(ProveedorPagoEstado estado) {
		ofy().delete().entity(estado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ProveedorPagoEstado.class).count();
		return siguiente + 1;
	}
}