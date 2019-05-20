package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.GastoConcepto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface GastoConceptoService {

	public void actualizar(GastoConcepto gastoConcepto);

	public GastoConcepto consultarPorId(Long gastoConceptoid);

	public List<GastoConcepto> consultarTodos();

	public long contar();

	public void crear(GastoConcepto gastoConcepto);

	public void eliminar(GastoConcepto gastoConcepto);

	public Long siguienteRegistro();

	public boolean exiteCodigo(Integer gastoConceptocodigo);

}
