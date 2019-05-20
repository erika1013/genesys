package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.EmpleadoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoEstadoService1 {

	public void actualizar(EmpleadoEstado empleadoEstado);

	public EmpleadoEstado consultarPorId(Long empleadoEstadoid);

	public List<EmpleadoEstado> consultarTodos();

	public long contar();

	public void crear(EmpleadoEstado empleadoEstado);

	public void eliminar(EmpleadoEstado empleadoEstado);

	public Long siguienteRegistro();

}
