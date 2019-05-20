package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoReferenciaService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoReferencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoReferenciaServiceImpl implements EmpleadoReferenciaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoReferencia empleadoReferencia) {
		ofy().save().entity(empleadoReferencia).now();
	}

	@Override
	public EmpleadoReferencia consultarPorId(Long id) {
		Key<EmpleadoReferencia> k = Key.create(EmpleadoReferencia.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoReferencia> consultarTodos() {
		return ofy().load().type(EmpleadoReferencia.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoReferencia.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoReferencia empleadoReferencia) {
		ofy().save().entity(empleadoReferencia).now();
	}

	@Override
	public void eliminar(EmpleadoReferencia empleadoReferencia) {
		ofy().delete().entity(empleadoReferencia).now();

	}

	@Override
	public List<EmpleadoReferencia> consultarReferenciasEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoReferencia.class).filter("empleado", empleado).list();
	}

}