package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.talento.client.service.CiudadService;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CiudadServiceImpl implements CiudadService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Ciudad ciudad) {
		ofy().save().entity(ciudad).now();
	}

	@Override
	public Ciudad consultarPorId(Long id) {
		Key<Ciudad> k = Key.create(Ciudad.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Ciudad> consultarTodos() {
		return ofy().load().type(Ciudad.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Ciudad.class).count();
		return cont;

	}

	@Override
	public void crear(Ciudad ciudad) {
		ofy().save().entity(ciudad).now();
	}

	@Override
	public void eliminar(Ciudad ciudad) {
		ofy().delete().entity(ciudad).now();

	}

}