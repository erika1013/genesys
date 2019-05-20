package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.TipoPago;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoPagoService {

	public void actualizar(TipoPago tipoPago);

	public TipoPago consultarPorId(Long tipoPagoid);

	public List<TipoPago> consultarTodos();

	public long contar();

	public void crear(TipoPago tipoPago);

	public void eliminar(TipoPago tipoPago);

	public Long siguienteRegistro();

}
