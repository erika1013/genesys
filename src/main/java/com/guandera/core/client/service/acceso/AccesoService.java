package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Acceso;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */
public interface AccesoService {
	public void actualizar(Acceso acceso);

	public Acceso consultarPorId(Long accesoid);

	public List<Acceso> consultarTodos();

	public long contar();

	public void crear(Acceso acceso);

	public void eliminar(Acceso acceso);

	public int siguienteRegistro();
}
