package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteIdioma;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteIdiomaService {

	public void actualizar(AspiranteIdioma aspiranteIdioma);

	public AspiranteIdioma consultarPorId(Long aspiranteIdiomaid);

	public List<AspiranteIdioma> consultarTodos();

	public long contar();

	public void crear(AspiranteIdioma aspiranteIdioma);

	public void eliminar(AspiranteIdioma aspiranteIdioma);

	public List<AspiranteIdioma> consultarIdiomasAspirante(Aspirante itemSeleccionado);

}
