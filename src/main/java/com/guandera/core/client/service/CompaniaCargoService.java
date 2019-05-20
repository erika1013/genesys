package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CompaniaCargo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CompaniaCargoService {

	public void actualizar(CompaniaCargo companiaCargo);

	public CompaniaCargo consultarPorId(Long companiaCargoid);

	public List<CompaniaCargo> consultarTodos();

	public long contar();

	public void crear(CompaniaCargo companiaCargo);

	public void eliminar(CompaniaCargo companiaCargo);

	public Long siguienteRegistro();

}
