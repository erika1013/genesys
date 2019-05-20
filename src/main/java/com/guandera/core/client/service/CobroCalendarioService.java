package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CobroCalendario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CobroCalendarioService {

	public void actualizar(CobroCalendario cobroCalendario);

	public CobroCalendario consultarPorId(Long cobroCalendarioid);

	public List<CobroCalendario> consultarTodos();

	public long contar();

	public void crear(CobroCalendario cobroCalendario);

	public void eliminar(CobroCalendario cobroCalendario);

	public Long siguienteRegistro();

	public boolean existePeriodo(String periodo);

}
