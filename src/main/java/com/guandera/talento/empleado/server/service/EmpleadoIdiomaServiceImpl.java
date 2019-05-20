package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoIdiomaService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoIdioma;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoIdiomaServiceImpl implements EmpleadoIdiomaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoIdioma empleadoIdioma) {
		ofy().save().entity(empleadoIdioma).now();
	}

	@Override
	public EmpleadoIdioma consultarPorId(Long id) {
		Key<EmpleadoIdioma> k = Key.create(EmpleadoIdioma.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoIdioma> consultarTodos() {
		return ofy().load().type(EmpleadoIdioma.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoIdioma.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoIdioma empleadoIdioma) {
		ofy().save().entity(empleadoIdioma).now();
	}

	@Override
	public void eliminar(EmpleadoIdioma empleadoIdioma) {
		ofy().delete().entity(empleadoIdioma).now();

	}

	@Override
	public List<EmpleadoIdioma> consultarIdiomasEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoIdioma.class).filter("empleado", empleado).list();
	}

}