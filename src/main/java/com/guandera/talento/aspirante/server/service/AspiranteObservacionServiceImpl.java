package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteObservacionService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteObservacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteObservacionServiceImpl implements AspiranteObservacionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteObservacion aspiranteObservacionObservacion) {
		ofy().save().entity(aspiranteObservacionObservacion).now();
	}

	@Override
	public AspiranteObservacion consultarPorId(Long id) {
		Key<AspiranteObservacion> k = Key.create(AspiranteObservacion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteObservacion> consultarTodos() {
		return ofy().load().type(AspiranteObservacion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteObservacion.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteObservacion aspiranteObservacionObservacion) {
		ofy().save().entity(aspiranteObservacionObservacion).now();
	}

	@Override
	public void eliminar(AspiranteObservacion aspiranteObservacionObservacion) {
		ofy().delete().entity(aspiranteObservacionObservacion).now();

	}

	@Override
	public List<AspiranteObservacion> consultarObservacionesAspirante(Aspirante aspirante) {
		return ofy().load().type(AspiranteObservacion.class).filter("aspirante", aspirante).list();
	}

}