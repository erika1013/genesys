package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoObservacionService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoObservacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoObservacionServiceImpl implements EmpleadoObservacionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoObservacion empleadoObservacionObservacion) {
		ofy().save().entity(empleadoObservacionObservacion).now();
	}

	@Override
	public EmpleadoObservacion consultarPorId(Long id) {
		Key<EmpleadoObservacion> k = Key.create(EmpleadoObservacion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoObservacion> consultarTodos() {
		return ofy().load().type(EmpleadoObservacion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoObservacion.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoObservacion empleadoObservacionObservacion) {
		ofy().save().entity(empleadoObservacionObservacion).now();
	}

	@Override
	public void eliminar(EmpleadoObservacion empleadoObservacionObservacion) {
		ofy().delete().entity(empleadoObservacionObservacion).now();

	}

	@Override
	public List<EmpleadoObservacion> consultarObservacionesEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoObservacion.class).filter("empleado", empleado).list();
	}

}