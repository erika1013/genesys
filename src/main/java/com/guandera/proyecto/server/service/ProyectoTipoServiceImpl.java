package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.ProyectoTipoService;
import com.guandera.proyecto.shared.model.ProyectoTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProyectoTipoServiceImpl implements ProyectoTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ProyectoTipo itemProyectoTipo) {
		ofy().save().entity(itemProyectoTipo).now();
	}

	@Override
	public ProyectoTipo consultarPorId(Long id) {
		Key<ProyectoTipo> k = Key.create(ProyectoTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ProyectoTipo> consultarTodos() {
		return ofy().load().type(ProyectoTipo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ProyectoTipo.class).count();
		return cont;

	}

	@Override
	public void crear(ProyectoTipo itemProyectoTipo) {
		ofy().save().entity(itemProyectoTipo).now();
	}

	@Override
	public void eliminar(ProyectoTipo itemProyectoTipo) {
		ofy().delete().entity(itemProyectoTipo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ProyectoTipo.class).count();
		return siguiente + 1;
	}

}