package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.BancoPagoEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface BancoPagoEstadoService {

	public void actualizar(BancoPagoEstado bancoPagoEstado);

	public BancoPagoEstado consultarPorId(Long bancoPagoEstadoid);

	public List<BancoPagoEstado> consultarTodos();

	public long contar();

	public void crear(BancoPagoEstado bancoPagoEstado);

	public void eliminar(BancoPagoEstado bancoPagoEstado);

	public Long siguienteRegistro();

}
