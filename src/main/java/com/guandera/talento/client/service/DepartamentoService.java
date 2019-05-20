package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.core.shared.model.Departamento;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface DepartamentoService {

	public void actualizar(Departamento departamento);

	public Departamento consultarPorId(Long departamentoid);

	public List<Departamento> consultarTodos();

	public long contar();

	public void crear(Departamento departamento);

	public void eliminar(Departamento departamento);

}
