package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.TipoPersona;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoPersonaService {

	public void actualizar(TipoPersona tipoPersona);

	public TipoPersona consultarPorId(Long tipoPersonaid);

	public List<TipoPersona> consultarTodos();

	public long contar();

	public void crear(TipoPersona tipoPersona);

	public void eliminar(TipoPersona tipoPersona);

	public Long siguienteRegistro();

}
