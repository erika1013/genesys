package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CobroEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CobroEstadoService {

	public void actualizar(CobroEstado cobroEstado);

	public CobroEstado consultarPorId(Long cobroEstadoid);

	public List<CobroEstado> consultarTodos();

	public long contar();

	public void crear(CobroEstado cobroEstado);

	public void eliminar(CobroEstado cobroEstado);

	public Long siguienteRegistro();

}
