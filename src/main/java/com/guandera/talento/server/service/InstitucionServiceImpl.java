package com.guandera.talento.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.client.service.InstitucionService;
import com.guandera.talento.shared.model.Competencia;
import com.guandera.talento.shared.model.Institucion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class InstitucionServiceImpl implements InstitucionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Institucion institucion) {
		ofy().save().entity(institucion).now();
	}

	@Override
	public Institucion consultarPorId(Long id) {
		Key<Institucion> k = Key.create(Institucion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Institucion> consultarTodos() {
		return ofy().load().type(Institucion.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Institucion.class).count();
		return cont;

	}

	@Override
	public void crear(Institucion institucion) {
		ofy().save().entity(institucion).now();
	}

	@Override
	public void eliminar(Institucion institucion) {
		ofy().delete().entity(institucion).now();

	}

	@Override
	public Integer siguienteCodigoInstitucion() {
		Integer siguiente = 0;

		Institucion institucion = new Institucion();

		try {
			institucion = ofy().load().type(Institucion.class).order("-codigo").first().now();
			siguiente = institucion.getCodigo();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

}