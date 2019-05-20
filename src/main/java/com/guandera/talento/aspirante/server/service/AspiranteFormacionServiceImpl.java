package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteFormacionService;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteFormacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteFormacionServiceImpl implements AspiranteFormacionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteFormacion aspiranteFormacion) {
		ofy().save().entity(aspiranteFormacion).now();
	}

	@Override
	public AspiranteFormacion consultarPorId(Long id) {
		Key<AspiranteFormacion> k = Key.create(AspiranteFormacion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteFormacion> consultarTodos() {
		return ofy().load().type(AspiranteFormacion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteFormacion.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteFormacion aspiranteFormacion) {
		ofy().save().entity(aspiranteFormacion).now();
	}

	@Override
	public void eliminar(AspiranteFormacion aspiranteFormacion) {
		ofy().delete().entity(aspiranteFormacion).now();

	}

	@Override
	public List<AspiranteFormacion> consultarFormacionAspirante(Aspirante aspirante) {
		return ofy().load().type(AspiranteFormacion.class).filter("aspirante", aspirante).list();
	}

}