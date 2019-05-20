package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.TipoCobro;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoCobroService {

	public void actualizar(TipoCobro tipoCobro);

	public TipoCobro consultarPorId(Long tipoCobroid);

	public List<TipoCobro> consultarTodos();

	public long contar();

	public void crear(TipoCobro tipoCobro);

	public void eliminar(TipoCobro tipoCobro);

	public Long siguienteRegistro();

}
