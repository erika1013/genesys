package com.guandera.core.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.TipoContrato;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoContratoService {

	public void actualizar(TipoContrato tipoContrato);

	public TipoContrato consultarPorId(Long tipoContratoid);

	public List<TipoContrato> consultarTodos();

	public long contar();

	public void crear(TipoContrato tipoContrato);

	public void eliminar(TipoContrato tipoContrato);

	public Long siguienteRegistro();

}
