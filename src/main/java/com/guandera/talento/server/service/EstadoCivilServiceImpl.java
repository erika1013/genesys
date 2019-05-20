package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.EstadoCivilService;
import com.guandera.talento.shared.model.EstadoCivil;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EstadoCivilServiceImpl implements EstadoCivilService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EstadoCivil estadoCivil) {
		ofy().save().entity(estadoCivil).now();
	}

	@Override
	public EstadoCivil consultarPorId(Long id) {
		Key<EstadoCivil> k = Key.create(EstadoCivil.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EstadoCivil> consultarTodos() {
		return ofy().load().type(EstadoCivil.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EstadoCivil.class).count();
		return cont;

	}

	@Override
	public void crear(EstadoCivil estadoCivil) {
		ofy().save().entity(estadoCivil).now();
	}

	@Override
	public void eliminar(EstadoCivil estadoCivil) {
		ofy().delete().entity(estadoCivil).now();

	}

}