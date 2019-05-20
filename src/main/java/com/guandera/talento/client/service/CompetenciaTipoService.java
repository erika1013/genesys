package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.CompetenciaTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CompetenciaTipoService {

	public void actualizar(CompetenciaTipo competenciaTipo);

	public CompetenciaTipo consultarPorId(Long competenciaTipoid);

	public List<CompetenciaTipo> consultarTodos();

	public long contar();

	public void crear(CompetenciaTipo competenciaTipo);

	public void eliminar(CompetenciaTipo competenciaTipo);

}
