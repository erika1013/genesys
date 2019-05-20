package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ProductoService;
import com.guandera.core.shared.model.Producto;
import com.guandera.core.shared.model.Sede;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProductoServiceImpl implements ProductoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Producto producto) {
		ofy().save().entity(producto).now();
	}

	@Override
	public Producto consultarPorId(Long id) {
		Key<Producto> k = Key.create(Producto.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Producto> consultarTodos() {
		return ofy().load().type(Producto.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Producto.class).count();
		return cont;

	}

	@Override
	public void crear(Producto producto) {
		ofy().save().entity(producto).now();
	}

	@Override
	public void eliminar(Producto producto) {
		ofy().delete().entity(producto).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Producto.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Sede> consultarSedes() {
		return ofy().load().type(Sede.class).list();
	}

}