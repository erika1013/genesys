package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Moneda;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface MonedaService {

	public void actualizar(Moneda moneda);

	public Moneda consultarPorId(Long monedaid);

	public List<Moneda> consultarTodos();

	public long contar();

	public void crear(Moneda moneda);

	public void eliminar(Moneda moneda);

	public Long siguienteRegistro();

}
