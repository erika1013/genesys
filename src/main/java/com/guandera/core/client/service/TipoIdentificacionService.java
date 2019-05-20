package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.TipoIdentificacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoIdentificacionService {

	public void actualizar(TipoIdentificacion tipoIdentificacion);

	public TipoIdentificacion consultarPorId(Long tipoIdentificacionid);

	public List<TipoIdentificacion> consultarTodos();

	public long contar();

	public void crear(TipoIdentificacion tipoIdentificacion);

	public void eliminar(TipoIdentificacion tipoIdentificacion);

	public Long siguienteRegistro();

}
