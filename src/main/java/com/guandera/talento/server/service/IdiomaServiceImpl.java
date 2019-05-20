package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.IdiomaService;
import com.guandera.talento.shared.model.Idioma;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class IdiomaServiceImpl implements IdiomaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Idioma idioma) {
		ofy().save().entity(idioma).now();
	}

	@Override
	public Idioma consultarPorId(Long id) {
		Key<Idioma> k = Key.create(Idioma.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Idioma> consultarTodos() {
		return ofy().load().type(Idioma.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Idioma.class).count();
		return cont;

	}

	@Override
	public void crear(Idioma idioma) {
		ofy().save().entity(idioma).now();
	}

	@Override
	public void eliminar(Idioma idioma) {
		ofy().delete().entity(idioma).now();

	}

}