package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoCompetencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoCompetenciaService {

	public void actualizar(EmpleadoCompetencia empleado);

	public EmpleadoCompetencia consultarPorId(Long empleadoid);

	public List<EmpleadoCompetencia> consultarTodos();

	public long contar();

	public void crear(EmpleadoCompetencia empleado);

	public void eliminar(EmpleadoCompetencia empleado);

	public List<EmpleadoCompetencia> consultarCompetenciasEmpleado(Empleado itemSeleccionado);

}
