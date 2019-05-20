package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ServicioService;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Servicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ServicioServiceImpl implements ServicioService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Servicio servicio) {
		ofy().save().entity(servicio).now();
	}

	@Override
	public Servicio consultarPorId(Long id) {
		Key<Servicio> k = Key.create(Servicio.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Servicio> consultarTodos() {
		return ofy().load().type(Servicio.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Servicio.class).count();
		return cont;

	}

	@Override
	public void crear(Servicio servicio) {
		ofy().save().entity(servicio).now();
	}

	@Override
	public void eliminar(Servicio servicio) {
		ofy().delete().entity(servicio).now();

	}

	@Override
	public Long siguienteRegistro() {
		long siguiente = 0;

		Servicio servicio = new Servicio();

		try {
			servicio = ofy().load().type(Servicio.class).order("-codigo").first().now();
			siguiente = servicio.getCodigo();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;

	}

	@Override
	public List<Compania> consultarCompanias() {
		return ofy().load().type(Compania.class).list();
	}
}