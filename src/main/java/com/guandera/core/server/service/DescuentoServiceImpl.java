package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.DescuentoService;
import com.guandera.core.shared.model.Descuento;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class DescuentoServiceImpl implements DescuentoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Descuento descuento) {
		ofy().save().entity(descuento).now();
	}

	@Override
	public Descuento consultarPorId(Long id) {
		Key<Descuento> k = Key.create(Descuento.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Descuento> consultarTodos() {
		return ofy().load().type(Descuento.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Descuento.class).count();
		return cont;

	}

	@Override
	public void crear(Descuento descuento) {
		ofy().save().entity(descuento).now();
	}

	@Override
	public void eliminar(Descuento descuento) {
		ofy().delete().entity(descuento).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(Descuento.class).count();
		return siguiente + 1;
	}

	@Override
	public List<Sede> consultarSedes() {
		return ofy().load().type(Sede.class).list();
	}

	@Override
	public List<TipoServicio> consultarTiposDescuento() {
		return ofy().load().type(TipoServicio.class).list();
	}
}