package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Producto;
import com.guandera.core.shared.model.Sede;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ProductoService {

	public void actualizar(Producto producto);

	public Producto consultarPorId(Long productoid);

	public List<Producto> consultarTodos();

	public long contar();

	public void crear(Producto producto);

	public void eliminar(Producto producto);

	public Long siguienteRegistro();

	public List<Sede> consultarSedes();

}
