package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.AspiranteEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteEstadoService {

	public void actualizar(AspiranteEstado aspiranteEstado);

	public AspiranteEstado consultarPorId(Long aspiranteEstadoid);

	public List<AspiranteEstado> consultarTodos();

	public long contar();

	public void crear(AspiranteEstado aspiranteEstado);

	public void eliminar(AspiranteEstado aspiranteEstado);

}
