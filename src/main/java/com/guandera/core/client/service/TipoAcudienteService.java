package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.TipoAcudiente;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface TipoAcudienteService {

	public void actualizar(TipoAcudiente tipoAcudiente);

	public TipoAcudiente consultarPorId(Long tipoAcudienteid);

	public List<TipoAcudiente> consultarTodos();

	public long contar();

	public void crear(TipoAcudiente tipoAcudiente);

	public void eliminar(TipoAcudiente tipoAcudiente);

	public Long siguienteRegistro();

}
