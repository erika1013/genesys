package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteExperiencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteExperienciaService {

	public void actualizar(AspiranteExperiencia aspiranteExperiencia);

	public AspiranteExperiencia consultarPorId(Long aspiranteExperienciaid);

	public List<AspiranteExperiencia> consultarTodos();

	public long contar();

	public void crear(AspiranteExperiencia aspiranteExperiencia);

	public void eliminar(AspiranteExperiencia aspiranteExperiencia);

	public List<AspiranteExperiencia> consultarExperienciaAspirante(Aspirante itemSeleccionado);

}
