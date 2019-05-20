package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.AsignacionEstadoService;
import com.guandera.proyecto.shared.model.AsignacionEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AsignacionEstadoServiceImpl implements AsignacionEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AsignacionEstado itemAsignacionEstado) {
		ofy().save().entity(itemAsignacionEstado).now();
	}

	@Override
	public AsignacionEstado consultarPorId(Long id) {
		Key<AsignacionEstado> k = Key.create(AsignacionEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AsignacionEstado> consultarTodos() {
		return ofy().load().type(AsignacionEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AsignacionEstado.class).count();
		return cont;

	}

	@Override
	public void crear(AsignacionEstado itemAsignacionEstado) {
		ofy().save().entity(itemAsignacionEstado).now();
	}

	@Override
	public void eliminar(AsignacionEstado itemAsignacionEstado) {
		ofy().delete().entity(itemAsignacionEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(AsignacionEstado.class).count();
		return siguiente + 1;
	}

}