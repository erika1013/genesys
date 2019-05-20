package com.guandera.talento.aspirante.client.service;

import java.util.List;

import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface AspiranteService {

	public void actualizar(Aspirante aspirante);

	public Aspirante consultarPorId(Long aspiranteid);

	public List<Aspirante> consultarTodos();

	public long contar();

	public void crear(Aspirante aspirante);

	public void eliminar(Aspirante aspirante);

	public Integer siguienteCodigoAspirante();

	public void crearCuentaAspirante(Aspirante aspirante, AspiranteAcceso aspiranteAcceso);

	public boolean existeUsuario(String correo);

}
