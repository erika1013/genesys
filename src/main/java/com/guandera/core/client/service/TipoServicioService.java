package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.TipoCobro;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoServicioService {

	public void actualizar(TipoServicio tipoServicio);

	public TipoServicio consultarPorId(Long tipoServicioid);

	public List<TipoServicio> consultarTodos();

	public long contar();

	public void crear(TipoServicio tipoServicio);

	public void eliminar(TipoServicio tipoServicio);

	public Long siguienteRegistro();

	public List<TipoCobro> consultarTipoCobro();

}
