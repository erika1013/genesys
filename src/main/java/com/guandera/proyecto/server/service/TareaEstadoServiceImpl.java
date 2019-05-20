package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.TareaEstadoService;
import com.guandera.proyecto.shared.model.TareaEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TareaEstadoServiceImpl implements TareaEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(TareaEstado itemTareaEstado) {
		ofy().save().entity(itemTareaEstado).now();
	}

	@Override
	public TareaEstado consultarPorId(Long id) {
		Key<TareaEstado> k = Key.create(TareaEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<TareaEstado> consultarTodos() {
		return ofy().load().type(TareaEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(TareaEstado.class).count();
		return cont;

	}

	@Override
	public void crear(TareaEstado itemTareaEstado) {
		ofy().save().entity(itemTareaEstado).now();
	}

	@Override
	public void eliminar(TareaEstado itemTareaEstado) {
		ofy().delete().entity(itemTareaEstado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(TareaEstado.class).count();
		return siguiente + 1;
	}

}