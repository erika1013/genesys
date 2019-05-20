package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteCompetencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteCompetenciaService {

	public void actualizar(AspiranteCompetencia aspirante);

	public AspiranteCompetencia consultarPorId(Long aspiranteid);

	public List<AspiranteCompetencia> consultarTodos();

	public long contar();

	public void crear(AspiranteCompetencia aspirante);

	public void eliminar(AspiranteCompetencia aspirante);

	public List<AspiranteCompetencia> consultarCompetenciasAspirante(Aspirante itemSeleccionado);

}
