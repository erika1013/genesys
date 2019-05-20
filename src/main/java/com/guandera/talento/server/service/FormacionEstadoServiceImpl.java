package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.FormacionEstadoService;
import com.guandera.talento.shared.model.FormacionEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class FormacionEstadoServiceImpl implements FormacionEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(FormacionEstado formacionEstado) {
		ofy().save().entity(formacionEstado).now();
	}

	@Override
	public FormacionEstado consultarPorId(Long id) {
		Key<FormacionEstado> k = Key.create(FormacionEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<FormacionEstado> consultarTodos() {
		return ofy().load().type(FormacionEstado.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(FormacionEstado.class).count();
		return cont;

	}

	@Override
	public void crear(FormacionEstado formacionEstado) {
		ofy().save().entity(formacionEstado).now();
	}

	@Override
	public void eliminar(FormacionEstado formacionEstado) {
		ofy().delete().entity(formacionEstado).now();

	}

}