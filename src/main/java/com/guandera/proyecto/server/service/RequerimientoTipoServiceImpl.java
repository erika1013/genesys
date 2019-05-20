package com.guandera.proyecto.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.proyecto.client.service.RequerimientoTipoService;
import com.guandera.proyecto.shared.model.RequerimientoTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class RequerimientoTipoServiceImpl implements RequerimientoTipoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(RequerimientoTipo itemRequerimientoTipo) {
		ofy().save().entity(itemRequerimientoTipo).now();
	}

	@Override
	public RequerimientoTipo consultarPorId(Long id) {
		Key<RequerimientoTipo> k = Key.create(RequerimientoTipo.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<RequerimientoTipo> consultarTodos() {
		return ofy().load().type(RequerimientoTipo.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(RequerimientoTipo.class).count();
		return cont;

	}

	@Override
	public void crear(RequerimientoTipo itemRequerimientoTipo) {
		ofy().save().entity(itemRequerimientoTipo).now();
	}

	@Override
	public void eliminar(RequerimientoTipo itemRequerimientoTipo) {
		ofy().delete().entity(itemRequerimientoTipo).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(RequerimientoTipo.class).count();
		return siguiente + 1;
	}

}