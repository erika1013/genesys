package com.guandera.core.client.service.acceso;

import java.util.List;

import com.guandera.core.shared.model.acceso.Opcion;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface OpcionService {

	public void actualizar(Opcion opcion);

	public Opcion consultarPorId(Long opcionid);

	public List<Opcion> consultarTodos();

	public long contar();

	public void crear(Opcion opcion);

	public void eliminar(Opcion opcion);

	public Long siguienteRegistro();

}
