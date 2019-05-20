package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.core.shared.model.Ciudad;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CiudadService {

	public void actualizar(Ciudad ciudad);

	public Ciudad consultarPorId(Long ciudadid);

	public List<Ciudad> consultarTodos();

	public long contar();

	public void crear(Ciudad ciudad);

	public void eliminar(Ciudad ciudad);

}
