package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.CompetenciaTipoService;
import com.guandera.talento.shared.model.CompetenciaTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CompetenciaTipoServiceImpl implements CompetenciaTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(CompetenciaTipo competenciaTipo) {
		ofy().save().entity(competenciaTipo).now();
	}

	@Override
	public CompetenciaTipo consultarPorId(Long id) {
		Key<CompetenciaTipo> k = Key.create(CompetenciaTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<CompetenciaTipo> consultarTodos() {
		return ofy().load().type(CompetenciaTipo.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(CompetenciaTipo.class).count();
		return cont;

	}

	@Override
	public void crear(CompetenciaTipo competenciaTipo) {
		ofy().save().entity(competenciaTipo).now();
	}

	@Override
	public void eliminar(CompetenciaTipo competenciaTipo) {
		ofy().delete().entity(competenciaTipo).now();

	}

}