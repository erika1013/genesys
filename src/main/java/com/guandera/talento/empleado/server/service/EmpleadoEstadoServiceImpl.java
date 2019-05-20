package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoEstadoService;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoEstadoServiceImpl implements EmpleadoEstadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoEstado empleadoEstado) {
		ofy().save().entity(empleadoEstado).now();
	}

	@Override
	public EmpleadoEstado consultarPorId(Long id) {
		Key<EmpleadoEstado> k = Key.create(EmpleadoEstado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoEstado> consultarTodos() {
		return ofy().load().type(EmpleadoEstado.class).order("codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoEstado.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoEstado empleadoEstado) {
		ofy().save().entity(empleadoEstado).now();
	}

	@Override
	public void eliminar(EmpleadoEstado empleadoEstado) {
		ofy().delete().entity(empleadoEstado).now();

	}

}