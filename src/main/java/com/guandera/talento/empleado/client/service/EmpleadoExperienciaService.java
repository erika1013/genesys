package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoExperiencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoExperienciaService {

	public void actualizar(EmpleadoExperiencia empleadoExperiencia);

	public EmpleadoExperiencia consultarPorId(Long empleadoExperienciaid);

	public List<EmpleadoExperiencia> consultarTodos();

	public long contar();

	public void crear(EmpleadoExperiencia empleadoExperiencia);

	public void eliminar(EmpleadoExperiencia empleadoExperiencia);

	public List<EmpleadoExperiencia> consultarExperienciaEmpleado(Empleado itemSeleccionado);

}
