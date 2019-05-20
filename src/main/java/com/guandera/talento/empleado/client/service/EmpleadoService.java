package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoService {

	public void actualizar(Empleado empleado);

	public Empleado consultarPorId(Long empleadoid);

	public List<Empleado> consultarTodos();

	public long contar();

	public void crear(Empleado empleado);

	public void eliminar(Empleado empleado);

	public Integer siguienteCodigoEmpleado();

}
