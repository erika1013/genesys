package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.Compania;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CentroCostosService {

	public void actualizar(CentroCostos itemCentroCostos);

	public CentroCostos consultarPorId(Long itemCentroCostos);

	public List<CentroCostos> consultarTodos();

	public long contar();

	public void crear(CentroCostos itemCentroCostos);

	public void eliminar(CentroCostos itemCentroCostos);

	public Long siguienteRegistro();

	public List<Compania> consultarCompanias();

	public boolean existeCentroCostos(String codigo);

}
