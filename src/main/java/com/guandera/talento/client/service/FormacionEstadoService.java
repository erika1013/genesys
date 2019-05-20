package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.FormacionEstado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface FormacionEstadoService {

	public void actualizar(FormacionEstado formacionEstado);

	public FormacionEstado consultarPorId(Long formacionEstadoid);

	public List<FormacionEstado> consultarTodos();

	public long contar();

	public void crear(FormacionEstado formacionEstado);

	public void eliminar(FormacionEstado formacionEstado);

}
