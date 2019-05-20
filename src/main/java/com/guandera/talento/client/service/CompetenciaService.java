package com.guandera.talento.client.service;

import java.util.List;

import com.guandera.talento.shared.model.Competencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CompetenciaService {

	public void actualizar(Competencia competencia);

	public Competencia consultarPorId(Long competenciaid);

	public List<Competencia> consultarTodos();

	public long contar();

	public void crear(Competencia competencia);

	public void eliminar(Competencia competencia);

	public Integer siguienteCodigoCompetencia();

}
