package com.guandera.talento.aspirante.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.aspirante.client.service.AspiranteCambioEstadoService;
import com.guandera.talento.aspirante.shared.model.AspiranteCambioEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class AspiranteCambioEstadoServiceImpl implements AspiranteCambioEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(AspiranteCambioEstado aspiranteCambioEstado) {
		ofy().save().entity(aspiranteCambioEstado).now();
	}

	@Override
	public AspiranteCambioEstado consultarPorId(Long id) {
		Key<AspiranteCambioEstado> k = Key.create(AspiranteCambioEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<AspiranteCambioEstado> consultarTodos() {
		return ofy().load().type(AspiranteCambioEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(AspiranteCambioEstado.class).count();
		return cont;

	}

	@Override
	public void crear(AspiranteCambioEstado aspiranteCambioEstado) {
		ofy().save().entity(aspiranteCambioEstado).now();
	}

	@Override
	public void eliminar(AspiranteCambioEstado aspiranteCambioEstado) {
		ofy().delete().entity(aspiranteCambioEstado).now();

	}

}