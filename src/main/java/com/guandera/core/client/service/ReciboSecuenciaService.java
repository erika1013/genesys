package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.ReciboSecuencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ReciboSecuenciaService {

	public void actualizar(ReciboSecuencia reciboSecuencia);

	public ReciboSecuencia consultarPorId(Long reciboSecuenciaid);

	public List<ReciboSecuencia> consultarTodos();

	public long contar();

	public void crear(ReciboSecuencia reciboSecuencia);

	public void eliminar(ReciboSecuencia reciboSecuencia);

	public Long siguienteRegistro();

}
