package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoExperienciaService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoExperiencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoExperienciaServiceImpl implements EmpleadoExperienciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoExperiencia empleadoExperiencia) {
		ofy().save().entity(empleadoExperiencia).now();
	}

	@Override
	public EmpleadoExperiencia consultarPorId(Long id) {
		Key<EmpleadoExperiencia> k = Key.create(EmpleadoExperiencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoExperiencia> consultarTodos() {
		return ofy().load().type(EmpleadoExperiencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoExperiencia.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoExperiencia empleadoExperiencia) {
		ofy().save().entity(empleadoExperiencia).now();
	}

	@Override
	public void eliminar(EmpleadoExperiencia empleadoExperiencia) {
		ofy().delete().entity(empleadoExperiencia).now();

	}

	@Override
	public List<EmpleadoExperiencia> consultarExperienciaEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoExperiencia.class).filter("empleado", empleado).list();

	}

}