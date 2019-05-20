package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoFormacionService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoFormacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoFormacionServiceImpl implements EmpleadoFormacionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoFormacion empleadoFormacion) {
		ofy().save().entity(empleadoFormacion).now();
	}

	@Override
	public EmpleadoFormacion consultarPorId(Long id) {
		Key<EmpleadoFormacion> k = Key.create(EmpleadoFormacion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoFormacion> consultarTodos() {
		return ofy().load().type(EmpleadoFormacion.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoFormacion.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoFormacion empleadoFormacion) {
		ofy().save().entity(empleadoFormacion).now();
	}

	@Override
	public void eliminar(EmpleadoFormacion empleadoFormacion) {
		ofy().delete().entity(empleadoFormacion).now();

	}

	@Override
	public List<EmpleadoFormacion> consultarFormacionEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoFormacion.class).filter("empleado", empleado).list();
	}

}