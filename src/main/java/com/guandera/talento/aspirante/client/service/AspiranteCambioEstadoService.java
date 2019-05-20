package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.AspiranteCambioEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteCambioEstadoService {

	public void actualizar(AspiranteCambioEstado aspiranteCambioEstado);

	public AspiranteCambioEstado consultarPorId(Long aspiranteCambioEstadoid);

	public List<AspiranteCambioEstado> consultarTodos();

	public long contar();

	public void crear(AspiranteCambioEstado aspiranteCambioEstado);

	public void eliminar(AspiranteCambioEstado aspiranteCambioEstado);

}
