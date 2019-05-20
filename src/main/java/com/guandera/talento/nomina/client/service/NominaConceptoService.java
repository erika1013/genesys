package com.guandera.talento.nomina.client.service;

import java.util.List;

import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface NominaConceptoService {

	public void actualizar(NominaConcepto nominaConcepto);

	public NominaConcepto consultarPorId(Long nominaconceptoid);

	public List<NominaConceptoTipo> consultarConceptoTipo();

	public List<NominaConcepto> consultarTodos();

	public long contar();

	public void crear(NominaConcepto nominaConcepto);

	public void eliminar(NominaConcepto nominaConcepto);

	public Long siguienteRegistro();

}
