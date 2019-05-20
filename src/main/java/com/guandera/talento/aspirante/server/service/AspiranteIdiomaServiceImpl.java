package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteIdiomaService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteIdioma;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteIdiomaServiceImpl implements AspiranteIdiomaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteIdioma aspiranteIdioma) {
		ofy().save().entity(aspiranteIdioma).now();
	}

	@Override
	public AspiranteIdioma consultarPorId(Long id) {
		Key<AspiranteIdioma> k = Key.create(AspiranteIdioma.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteIdioma> consultarTodos() {
		return ofy().load().type(AspiranteIdioma.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteIdioma.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteIdioma aspiranteIdioma) {
		ofy().save().entity(aspiranteIdioma).now();
	}

	@Override
	public void eliminar(AspiranteIdioma aspiranteIdioma) {
		ofy().delete().entity(aspiranteIdioma).now();

	}

	@Override
	public List<AspiranteIdioma> consultarIdiomasAspirante(Aspirante aspirante) {
		return ofy().load().type(AspiranteIdioma.class).filter("aspirante", aspirante).list();
	}

}