package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.NivelEducativo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface NivelEducativoService {

	public void actualizar(NivelEducativo nivelEducativo);

	public NivelEducativo consultarPorId(Long nivelEducativoid);

	public List<NivelEducativo> consultarTodos();

	public long contar();

	public void crear(NivelEducativo nivelEducativo);

	public void eliminar(NivelEducativo nivelEducativo);

}
