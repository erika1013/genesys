package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;

import com.guandera.talento.client.service.CompetenciaService;
import com.guandera.talento.shared.model.Competencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CompetenciaServiceImpl implements CompetenciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Competencia competencia) {
		ofy().save().entity(competencia).now();
	}

	@Override
	public Competencia consultarPorId(Long id) {
		Key<Competencia> k = Key.create(Competencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Competencia> consultarTodos() {
		return ofy().load().type(Competencia.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Competencia.class).count();
		return cont;

	}

	@Override
	public void crear(Competencia competencia) {
		ofy().save().entity(competencia).now();
	}

	@Override
	public void eliminar(Competencia competencia) {
		ofy().delete().entity(competencia).now();

	}

	@Override
	public Integer siguienteCodigoCompetencia() {

		Integer siguiente = 0;

		Competencia competencia = new Competencia();

		try {
			competencia = ofy().load().type(Competencia.class).order("-codigo").first().now();
			siguiente = competencia.getCodigo();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

}