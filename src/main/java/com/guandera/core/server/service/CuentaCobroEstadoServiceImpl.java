package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CuentaCobroEstadoService;
import com.guandera.core.shared.model.CuentaCobroEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CuentaCobroEstadoServiceImpl implements CuentaCobroEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CuentaCobroEstado cuentaCobroEstado) {
		ofy().save().entity(cuentaCobroEstado).now();
	}

	@Override
	public CuentaCobroEstado consultarPorId(Long id) {
		Key<CuentaCobroEstado> k = Key.create(CuentaCobroEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<CuentaCobroEstado> consultarTodos() {
		return ofy().load().type(CuentaCobroEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CuentaCobroEstado.class).count();
		return cont;

	}

	@Override
	public void crear(CuentaCobroEstado cuentaCobroEstado) {
		ofy().save().entity(cuentaCobroEstado).now();
	}

	@Override
	public void eliminar(CuentaCobroEstado cuentaCobroEstado) {
		ofy().delete().entity(cuentaCobroEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(CuentaCobroEstado.class).count();
		return siguiente + 1;
	}
}