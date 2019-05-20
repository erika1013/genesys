package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoReferencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoReferenciaService {

	public void actualizar(EmpleadoReferencia empleadoReferencia);

	public EmpleadoReferencia consultarPorId(Long empleadoReferenciaid);

	public List<EmpleadoReferencia> consultarTodos();

	public long contar();

	public void crear(EmpleadoReferencia empleadoReferencia);

	public void eliminar(EmpleadoReferencia empleadoReferencia);

	public List<EmpleadoReferencia> consultarReferenciasEmpleado(Empleado itemSeleccionado);

}
