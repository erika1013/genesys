package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoService;
import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoServiceImpl implements EmpleadoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(Empleado empleado) {
		ofy().save().entity(empleado).now();
	}

	@Override
	public Empleado consultarPorId(Long id) {
		Key<Empleado> k = Key.create(Empleado.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<Empleado> consultarTodos() {
		return ofy().load().type(Empleado.class).order("-codigo").list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(Empleado.class).count();
		return cont;

	}

	@Override
	public void crear(Empleado empleado) {
		ofy().save().entity(empleado).now();
	}

	@Override
	public void eliminar(Empleado empleado) {
		ofy().delete().entity(empleado).now();

	}

	@Override
	public Integer siguienteCodigoEmpleado() {

		Integer siguiente = 0;

		Empleado empleado = new Empleado();

		try {
			empleado = ofy().load().type(Empleado.class).order("-codigo").first().now();
			siguiente = empleado.getCodigo();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

}