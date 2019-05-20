package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoIdioma;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoIdiomaService {

	public void actualizar(EmpleadoIdioma empleadoIdioma);

	public EmpleadoIdioma consultarPorId(Long empleadoIdiomaid);

	public List<EmpleadoIdioma> consultarTodos();

	public long contar();

	public void crear(EmpleadoIdioma empleadoIdioma);

	public void eliminar(EmpleadoIdioma empleadoIdioma);

	public List<EmpleadoIdioma> consultarIdiomasEmpleado(Empleado itemSeleccionado);

}
