package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.ImpuestoConcepto;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ImpuestoConceptoService {

	public void actualizar(ImpuestoConcepto impuestoConcepto);

	public ImpuestoConcepto consultarPorId(Long impuestoConceptoid);

	public List<ImpuestoConcepto> consultarTodos();

	public long contar();

	public void crear(ImpuestoConcepto impuestoConcepto);

	public void eliminar(ImpuestoConcepto impuestoConcepto);

	public Long siguienteRegistro();

}
