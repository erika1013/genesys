package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteObservacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteObservacionService {

	public void actualizar(AspiranteObservacion aspiranteObservacion);

	public AspiranteObservacion consultarPorId(Long aspiranteObservacionid);

	public List<AspiranteObservacion> consultarTodos();

	public long contar();

	public void crear(AspiranteObservacion aspiranteObservacion);

	public void eliminar(AspiranteObservacion aspiranteObservacion);

	public List<AspiranteObservacion> consultarObservacionesAspirante(Aspirante itemSeleccionado);

}
