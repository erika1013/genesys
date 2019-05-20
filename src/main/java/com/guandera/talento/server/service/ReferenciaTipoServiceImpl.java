package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.ReferenciaTipoService;
import com.guandera.talento.shared.model.ReferenciaTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ReferenciaTipoServiceImpl implements ReferenciaTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ReferenciaTipo referenciaTipo) {
		ofy().save().entity(referenciaTipo).now();
	}

	@Override
	public ReferenciaTipo consultarPorId(Long id) {
		Key<ReferenciaTipo> k = Key.create(ReferenciaTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ReferenciaTipo> consultarTodos() {
		return ofy().load().type(ReferenciaTipo.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ReferenciaTipo.class).count();
		return cont;

	}

	@Override
	public void crear(ReferenciaTipo referenciaTipo) {
		ofy().save().entity(referenciaTipo).now();
	}

	@Override
	public void eliminar(ReferenciaTipo referenciaTipo) {
		ofy().delete().entity(referenciaTipo).now();

	}

}