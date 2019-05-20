package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteEstadoService;
import com.guandera.talento.aspirante.shared.model.AspiranteEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteEstadoServiceImpl implements AspiranteEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteEstado aspiranteEstado) {
		ofy().save().entity(aspiranteEstado).now();
	}

	@Override
	public AspiranteEstado consultarPorId(Long id) {
		Key<AspiranteEstado> k = Key.create(AspiranteEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteEstado> consultarTodos() {
		return ofy().load().type(AspiranteEstado.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteEstado.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteEstado aspiranteEstado) {
		ofy().save().entity(aspiranteEstado).now();
	}

	@Override
	public void eliminar(AspiranteEstado aspiranteEstado) {
		ofy().delete().entity(aspiranteEstado).now();

	}

}