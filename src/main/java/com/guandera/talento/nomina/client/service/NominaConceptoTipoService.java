package com.guandera.talento.nomina.client.service;

import java.util.List;

import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface NominaConceptoTipoService {

	public void actualizar(NominaConceptoTipo conceptoTipo);

	public NominaConceptoTipo consultarPorId(Long conceptoTipoid);

	public List<NominaConceptoTipo> consultarTodos();

	public long contar();

	public void crear(NominaConceptoTipo conceptoTipo);

	public void eliminar(NominaConceptoTipo conceptoTipo);

	public Long siguienteRegistro();

}
