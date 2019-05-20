/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.guandera.talento.aspirante.client.service;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;

public interface LoginAspiranteService {

	boolean existeUsuario(String usuario);

	boolean verificarAspiranteAcceso(AspiranteAcceso acceso);

	public AspiranteAcceso CargarAspiranteAcceso(AspiranteAcceso aspiranteAcceso1);

	void actualizarUtimoAcceso(AspiranteAcceso aspiranteAcceso);

	Aspirante CargarAspirante(AspiranteAcceso aspiranteAcceso);
}
