package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteFormacion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteFormacionService {

	public void actualizar(AspiranteFormacion aspiranteFormacion);

	public AspiranteFormacion consultarPorId(Long aspiranteFormacionid);

	public List<AspiranteFormacion> consultarTodos();

	public long contar();

	public void crear(AspiranteFormacion aspiranteFormacion);

	public void eliminar(AspiranteFormacion aspiranteFormacion);

	public List<AspiranteFormacion> consultarFormacionAspirante(Aspirante itemSeleccionado);

}
