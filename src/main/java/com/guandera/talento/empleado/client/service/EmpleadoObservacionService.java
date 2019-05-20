package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoObservacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoObservacionService {

	public void actualizar(EmpleadoObservacion empleadoObservacion);

	public EmpleadoObservacion consultarPorId(Long empleadoObservacionid);

	public List<EmpleadoObservacion> consultarTodos();

	public long contar();

	public void crear(EmpleadoObservacion empleadoObservacion);

	public void eliminar(EmpleadoObservacion empleadoObservacion);

	public List<EmpleadoObservacion> consultarObservacionesEmpleado(Empleado itemSeleccionado);

}
