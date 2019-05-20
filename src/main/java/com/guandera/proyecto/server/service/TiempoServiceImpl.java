package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.TiempoService;
import com.guandera.proyecto.shared.model.Tiempo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class TiempoServiceImpl implements TiempoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Tiempo itemTiempo) {
		ofy().save().entity(itemTiempo).now();
	}

	@Override
	public Tiempo consultarPorId(Long id) {
		Key<Tiempo> k = Key.create(Tiempo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Tiempo> consultarTodos() {
		return ofy().load().type(Tiempo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Tiempo.class).count();
		return cont;

	}

	@Override
	public void crear(Tiempo itemTiempo) {
		ofy().save().entity(itemTiempo).now();
	}

	@Override
	public void eliminar(Tiempo itemTiempo) {
		ofy().delete().entity(itemTiempo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Tiempo.class).count();
		return siguiente + 1;
	}

}