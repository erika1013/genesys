package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteExperienciaService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteExperiencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteExperienciaServiceImpl implements AspiranteExperienciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteExperiencia aspiranteExperiencia) {
		ofy().save().entity(aspiranteExperiencia).now();
	}

	@Override
	public AspiranteExperiencia consultarPorId(Long id) {
		Key<AspiranteExperiencia> k = Key.create(AspiranteExperiencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteExperiencia> consultarTodos() {
		return ofy().load().type(AspiranteExperiencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteExperiencia.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteExperiencia aspiranteExperiencia) {
		ofy().save().entity(aspiranteExperiencia).now();
	}

	@Override
	public void eliminar(AspiranteExperiencia aspiranteExperiencia) {
		ofy().delete().entity(aspiranteExperiencia).now();

	}

	@Override
	public List<AspiranteExperiencia> consultarExperienciaAspirante(Aspirante aspirante) {
		return ofy().load().type(AspiranteExperiencia.class).filter("aspirante", aspirante).list();

	}

}