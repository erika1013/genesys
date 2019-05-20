package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.NivelEducativoService;
import com.guandera.talento.shared.model.NivelEducativo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class NivelEducativoServiceImpl implements NivelEducativoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(NivelEducativo nivelEducativo) {
		ofy().save().entity(nivelEducativo).now();
	}

	@Override
	public NivelEducativo consultarPorId(Long id) {
		Key<NivelEducativo> k = Key.create(NivelEducativo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<NivelEducativo> consultarTodos() {
		return ofy().load().type(NivelEducativo.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(NivelEducativo.class).count();
		return cont;

	}

	@Override
	public void crear(NivelEducativo nivelEducativo) {
		ofy().save().entity(nivelEducativo).now();
	}

	@Override
	public void eliminar(NivelEducativo nivelEducativo) {
		ofy().delete().entity(nivelEducativo).now();

	}

}