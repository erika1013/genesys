package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.EstadoCivil;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface EstadoCivilService {

	public void actualizar(EstadoCivil estadoCivil);

	public EstadoCivil consultarPorId(Long estadoCivilid);

	public List<EstadoCivil> consultarTodos();

	public long contar();

	public void crear(EstadoCivil estadoCivil);

	public void eliminar(EstadoCivil estadoCivil);

}
