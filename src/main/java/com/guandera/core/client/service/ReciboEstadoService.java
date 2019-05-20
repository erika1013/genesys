package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.ReciboEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ReciboEstadoService {

	public void actualizar(ReciboEstado reciboEstado);

	public ReciboEstado consultarPorId(Long reciboEstadoid);

	public List<ReciboEstado> consultarTodos();

	public long contar();

	public void crear(ReciboEstado reciboEstado);

	public void eliminar(ReciboEstado reciboEstado);

	public Long siguienteRegistro();

}
