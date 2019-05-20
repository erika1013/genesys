package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.EmpleadoContratoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EmpleadoContratoEstadoService {

	public void actualizar(EmpleadoContratoEstado estado);

	public EmpleadoContratoEstado consultarPorId(Long estadoid);

	public List<EmpleadoContratoEstado> consultarTodos();

	public long contar();

	public void crear(EmpleadoContratoEstado estado);

	public void eliminar(EmpleadoContratoEstado estado);

	public Long siguienteRegistro();

}
