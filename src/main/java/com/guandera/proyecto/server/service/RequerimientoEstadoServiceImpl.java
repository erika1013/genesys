package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.RequerimientoEstadoService;
import com.guandera.proyecto.shared.model.RequerimientoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class RequerimientoEstadoServiceImpl implements RequerimientoEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(RequerimientoEstado itemRequerimientoEstado) {
		ofy().save().entity(itemRequerimientoEstado).now();
	}

	@Override
	public RequerimientoEstado consultarPorId(Long id) {
		Key<RequerimientoEstado> k = Key.create(RequerimientoEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<RequerimientoEstado> consultarTodos() {
		return ofy().load().type(RequerimientoEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(RequerimientoEstado.class).count();
		return cont;

	}

	@Override
	public void crear(RequerimientoEstado itemRequerimientoEstado) {
		ofy().save().entity(itemRequerimientoEstado).now();
	}

	@Override
	public void eliminar(RequerimientoEstado itemRequerimientoEstado) {
		ofy().delete().entity(itemRequerimientoEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(RequerimientoEstado.class).count();
		return siguiente + 1;
	}

}