package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ClienteCargoService;
import com.guandera.core.shared.model.ClienteCargo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ClienteCargoServiceImpl implements ClienteCargoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ClienteCargo itemClienteCargo) {
		ofy().save().entity(itemClienteCargo).now();
	}

	@Override
	public ClienteCargo consultarPorId(Long id) {
		Key<ClienteCargo> k = Key.create(ClienteCargo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ClienteCargo> consultarTodos() {
		return ofy().load().type(ClienteCargo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ClienteCargo.class).count();
		return cont;

	}

	@Override
	public void crear(ClienteCargo itemClienteCargo) {
		ofy().save().entity(itemClienteCargo).now();
	}

	@Override
	public void eliminar(ClienteCargo itemClienteCargo) {
		ofy().delete().entity(itemClienteCargo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ClienteCargo.class).count();
		return siguiente + 1;
	}

}