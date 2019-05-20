package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.ProyectoEtapaService;
import com.guandera.proyecto.shared.model.ProyectoEtapa;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ProyectoEtapaServiceImpl implements ProyectoEtapaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ProyectoEtapa itemProyectoEtapa) {
		ofy().save().entity(itemProyectoEtapa).now();
	}

	@Override
	public ProyectoEtapa consultarPorId(Long id) {
		Key<ProyectoEtapa> k = Key.create(ProyectoEtapa.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ProyectoEtapa> consultarTodos() {
		return ofy().load().type(ProyectoEtapa.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ProyectoEtapa.class).count();
		return cont;

	}

	@Override
	public void crear(ProyectoEtapa itemProyectoEtapa) {
		ofy().save().entity(itemProyectoEtapa).now();
	}

	@Override
	public void eliminar(ProyectoEtapa itemProyectoEtapa) {
		ofy().delete().entity(itemProyectoEtapa).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(ProyectoEtapa.class).count();
		return siguiente + 1;
	}

}