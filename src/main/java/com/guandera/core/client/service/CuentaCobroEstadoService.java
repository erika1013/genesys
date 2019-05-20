package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CuentaCobroEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CuentaCobroEstadoService {

	public void actualizar(CuentaCobroEstado cuentaCobroEstado);

	public CuentaCobroEstado consultarPorId(Long cuentaCobroEstadoid);

	public List<CuentaCobroEstado> consultarTodos();

	public long contar();

	public void crear(CuentaCobroEstado cuentaCobroEstado);

	public void eliminar(CuentaCobroEstado cuentaCobroEstado);

	public Long siguienteRegistro();

}
