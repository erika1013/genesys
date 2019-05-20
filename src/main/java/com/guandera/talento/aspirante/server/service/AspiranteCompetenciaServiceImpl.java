package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteCompetenciaService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteCompetencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteCompetenciaServiceImpl implements AspiranteCompetenciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteCompetencia aspirante) {
		ofy().save().entity(aspirante).now();
	}

	@Override
	public AspiranteCompetencia consultarPorId(Long id) {
		Key<AspiranteCompetencia> k = Key.create(AspiranteCompetencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteCompetencia> consultarTodos() {
		return ofy().load().type(AspiranteCompetencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteCompetencia.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteCompetencia aspirante) {
		ofy().save().entity(aspirante).now();
	}

	@Override
	public void eliminar(AspiranteCompetencia aspirante) {
		ofy().delete().entity(aspirante).now();

	}

	@Override
	public List<AspiranteCompetencia> consultarCompetenciasAspirante(Aspirante aspirante) {
		return ofy().load().type(AspiranteCompetencia.class).filter("aspirante", aspirante).list();
	}

}