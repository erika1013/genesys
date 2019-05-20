package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoContratoEstadoService;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoContratoEstadoServiceImpl implements EmpleadoContratoEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoContratoEstado estado) {
		ofy().save().entity(estado).now();
	}

	@Override
	public EmpleadoContratoEstado consultarPorId(Long id) {
		Key<EmpleadoContratoEstado> k = Key.create(EmpleadoContratoEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoContratoEstado> consultarTodos() {
		return ofy().load().type(EmpleadoContratoEstado.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoContratoEstado.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoContratoEstado estado) {
		ofy().save().entity(estado).now();
	}

	@Override
	public void eliminar(EmpleadoContratoEstado estado) {
		ofy().delete().entity(estado).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(EmpleadoContratoEstado.class).count();
		return siguiente + 1;
	}
}