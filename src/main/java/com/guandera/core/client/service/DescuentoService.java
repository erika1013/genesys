package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Descuento;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoServicio;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface DescuentoService {

	public void actualizar(Descuento descuento);

	public Descuento consultarPorId(Long descuentoid);

	public List<Descuento> consultarTodos();

	public long contar();

	public void crear(Descuento descuento);

	public void eliminar(Descuento descuento);

	public Long siguienteRegistro();

	public List<Sede> consultarSedes();

	public List<TipoServicio> consultarTiposDescuento();

}
