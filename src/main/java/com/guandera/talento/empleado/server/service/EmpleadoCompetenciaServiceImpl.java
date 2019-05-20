package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoCompetenciaService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoCompetencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoCompetenciaServiceImpl implements EmpleadoCompetenciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoCompetencia empleado) {
		ofy().save().entity(empleado).now();
	}

	@Override
	public EmpleadoCompetencia consultarPorId(Long id) {
		Key<EmpleadoCompetencia> k = Key.create(EmpleadoCompetencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoCompetencia> consultarTodos() {
		return ofy().load().type(EmpleadoCompetencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoCompetencia.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoCompetencia empleado) {
		ofy().save().entity(empleado).now();
	}

	@Override
	public void eliminar(EmpleadoCompetencia empleado) {
		ofy().delete().entity(empleado).now();

	}

	@Override
	public List<EmpleadoCompetencia> consultarCompetenciasEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoCompetencia.class).filter("empleado", empleado).list();
	}

}