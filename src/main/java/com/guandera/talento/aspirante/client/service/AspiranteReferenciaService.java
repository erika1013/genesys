package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteReferencia;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteReferenciaService {

	public void actualizar(AspiranteReferencia aspiranteReferencia);

	public AspiranteReferencia consultarPorId(Long aspiranteReferenciaid);

	public List<AspiranteReferencia> consultarTodos();

	public long contar();

	public void crear(AspiranteReferencia aspiranteReferencia);

	public void eliminar(AspiranteReferencia aspiranteReferencia);

	public List<AspiranteReferencia> consultarReferenciasAspirante(Aspirante itemSeleccionado);

}
