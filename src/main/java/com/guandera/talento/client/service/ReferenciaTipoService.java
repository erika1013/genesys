package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.ReferenciaTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ReferenciaTipoService {

	public void actualizar(ReferenciaTipo referenciaTipo);

	public ReferenciaTipo consultarPorId(Long aspiranteReferenciaTipoid);

	public List<ReferenciaTipo> consultarTodos();

	public long contar();

	public void crear(ReferenciaTipo referenciaTipo);

	public void eliminar(ReferenciaTipo referenciaTipo);

}
