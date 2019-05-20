package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.ActividadTipoService;
import com.guandera.proyecto.shared.model.ActividadTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ActividadTipoServiceImpl implements ActividadTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ActividadTipo itemActividadTipo) {
		ofy().save().entity(itemActividadTipo).now();
	}

	@Override
	public ActividadTipo consultarPorId(Long id) {
		Key<ActividadTipo> k = Key.create(ActividadTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ActividadTipo> consultarTodos() {
		return ofy().load().type(ActividadTipo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ActividadTipo.class).count();
		return cont;

	}

	@Override
	public void crear(ActividadTipo itemActividadTipo) {
		ofy().save().entity(itemActividadTipo).now();
	}

	@Override
	public void eliminar(ActividadTipo itemActividadTipo) {
		ofy().delete().entity(itemActividadTipo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ActividadTipo.class).count();
		return siguiente + 1;
	}

}