package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ClienteService;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteCargo;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ClienteServiceImpl implements ClienteService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Cliente cliente) {
		ofy().save().entity(cliente).now();
	}

	@Override
	public Cliente consultarPorId(Long id) {
		Key<Cliente> k = Key.create(Cliente.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Cliente> consultarTodos() {
		return ofy().load().type(Cliente.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Cliente.class).count();
		return cont;

	}

	@Override
	public void crear(Cliente cliente) {

		ofy().save().entity(cliente).now();
	}

	@Override
	public void eliminar(Cliente cliente) {
		ofy().delete().entity(cliente).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Cliente.class).count();
		return siguiente + 1;
	}

	@Override
	public List<TipoIdentificacion> consultarTiposIdentificacion() {
		return ofy().load().type(TipoIdentificacion.class).list();
	}

	@Override
	public void actualizarClienteContacto(ClienteContacto itemClienteContacto) {
		ofy().save().entity(itemClienteContacto).now();

	}

	@Override
	public ClienteContacto consultarClienteContactoPorId(Long itemClienteContacto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClienteContacto> consultarClienteContactos(Long clienteid) {
		Key<Cliente> k = Key.create(Cliente.class, clienteid);
		return ofy().load().type(ClienteContacto.class).filter("cliente", k).list();
	}

	@Override
	public void crearClienteContacto(ClienteContacto itemClienteContacto) {
		ofy().save().entity(itemClienteContacto).now();
	}

	@Override
	public void eliminarClienteContacto(ClienteContacto itemClienteContacto) {
		ofy().delete().entity(itemClienteContacto).now();

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
	public List<ClienteCargo> consultarClienteCargos(Long clienteid) {
		Key<Cliente> k = Key.create(Cliente.class, clienteid);
		return ofy().load().type(ClienteCargo.class).filter("cliente", k).list();
	}

	@Override
	public void crearCargo(ClienteCargo cargo) {
		ofy().save().entity(cargo).now();

	}

	@Override
	public void actualizarCargo(ClienteCargo cargo) {
		ofy().save().entity(cargo).now();

	}

	@Override
	public void eliminarCargo(ClienteCargo cargo) {
		ofy().delete().entity(cargo).now();

	}

}