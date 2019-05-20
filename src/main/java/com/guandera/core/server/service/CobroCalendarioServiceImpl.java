package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.CobroCalendarioService;
import com.guandera.core.shared.model.CobroCalendario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CobroCalendarioServiceImpl implements CobroCalendarioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CobroCalendario cobroCalendario) {
		ofy().save().entity(cobroCalendario).now();
	}

	@Override
	public CobroCalendario consultarPorId(Long id) {
		Key<CobroCalendario> k = Key.create(CobroCalendario.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<CobroCalendario> consultarTodos() {
		return ofy().load().type(CobroCalendario.class).order("periodo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CobroCalendario.class).count();
		return cont;

	}

	@Override
	public void crear(CobroCalendario cobroCalendario) {
		ofy().save().entity(cobroCalendario).now();
	}

	@Override
	public void eliminar(CobroCalendario cobroCalendario) {
		ofy().delete().entity(cobroCalendario).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(CobroCalendario.class).count();
		return siguiente + 1;
	}

	@Override
	public boolean existePeriodo(String periodo) {

		CobroCalendario calendario = ofy().load().type(CobroCalendario.class).filter("periodo", periodo).first().now();

		if (calendario != null) {
			return true;
		} else {
			return false;
		}
	}
}