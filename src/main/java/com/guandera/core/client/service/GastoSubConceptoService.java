package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.GastoConcepto;
import com.guandera.core.shared.model.GastoSubConcepto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface GastoSubConceptoService {

	public void actualizar(GastoSubConcepto subConcepto);

	public GastoSubConcepto consultarPorId(Long subConceptoid);

	public List<GastoSubConcepto> consultarTodos();

	public long contar();

	public void crear(GastoSubConcepto subConcepto);

	public void eliminar(GastoSubConcepto subConcepto);

	public Long siguienteRegistro();

	public List<GastoConcepto> consultarConceptos();

	public Integer siguienteSubConcepto();

}
