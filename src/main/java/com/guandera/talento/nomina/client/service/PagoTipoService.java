package com.guandera.talento.nomina.client.service;

import java.util.List;

import com.guandera.core.shared.model.PagoTipo;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface PagoTipoService {

	public void actualizar(PagoTipo pagoTipo);

	public PagoTipo consultarPorId(Long pagoTipoid);

	public List<PagoTipo> consultarTodos();

	public long contar();

	public void crear(PagoTipo pagoTipo);

	public void eliminar(PagoTipo pagoTipo);

	public Long siguienteRegistro();

}
