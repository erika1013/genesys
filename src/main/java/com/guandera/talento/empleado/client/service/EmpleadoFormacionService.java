package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoFormacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoFormacionService {

	public void actualizar(EmpleadoFormacion empleadoFormacion);

	public EmpleadoFormacion consultarPorId(Long empleadoFormacionid);

	public List<EmpleadoFormacion> consultarTodos();

	public long contar();

	public void crear(EmpleadoFormacion empleadoFormacion);

	public void eliminar(EmpleadoFormacion empleadoFormacion);

	public List<EmpleadoFormacion> consultarFormacionEmpleado(Empleado itemSeleccionado);

}
