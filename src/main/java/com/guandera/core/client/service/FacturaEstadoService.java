package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.FacturaEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface FacturaEstadoService {

	public void actualizar(FacturaEstado facturaEstado);

	public FacturaEstado consultarPorId(Long facturaEstadoid);

	public List<FacturaEstado> consultarTodos();

	public long contar();

	public void crear(FacturaEstado facturaEstado);

	public void eliminar(FacturaEstado facturaEstado);

	public Long siguienteRegistro();

}
