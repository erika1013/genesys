package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ClienteContactoService;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteCargo;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ClienteContactoServiceImpl implements ClienteContactoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ClienteContacto itemClienteContacto) {
		ofy().save().entity(itemClienteContacto).now();
	}

	@Override
	public ClienteContacto consultarPorId(Long id) {
		Key<ClienteContacto> k = Key.create(ClienteContacto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ClienteContacto> consultarTodos() {
		return ofy().load().type(ClienteContacto.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ClienteContacto.class).count();
		return cont;

	}

	@Override
	public void crear(ClienteContacto itemClienteContacto) {

		ofy().save().entity(itemClienteContacto).now();
	}

	@Override
	public void eliminar(ClienteContacto itemClienteContacto) {
		ofy().delete().entity(itemClienteContacto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ClienteContacto.class).count();
		return siguiente + 1;
	}

	@Override
	public List<TipoIdentificacion> consultarTiposIdentificacion() {
		return ofy().load().type(TipoIdentificacion.class).list();
	}

	@Override
	public boolean existeClienteContacto(Integer numeroidentificacion) {
		ClienteContacto persona = ofy().load().type(ClienteContacto.class)
				.filter("numeroidentificacion", numeroidentificacion).first().now();
		if (persona != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<ClienteCargo> consultarCargos() {
		return ofy().load().type(ClienteCargo.class).list();
	}

	@Override
	public List<Cliente> consultarClientes() {
		// TODO Auto-generated method stub
		return ofy().load().type(Cliente.class).list();
	}

}