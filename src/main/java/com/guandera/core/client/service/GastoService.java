package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Gasto;
import com.guandera.core.shared.model.GastoConcepto;
import com.guandera.core.shared.model.GastoSubConcepto;
import com.guandera.core.shared.model.Proveedor;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface GastoService {

	public void actualizar(Gasto gasto);

	public Gasto consultarPorId(Long gastoid);

	public List<Gasto> consultarTodos();

	public long contar();

	public void crear(Gasto gasto);

	public void eliminar(Gasto gasto);

	public Long siguienteRegistro();

	public List<Proveedor> consultarProveedores();

	public List<GastoConcepto> consultarTiposGasto();

	public List<GastoSubConcepto> consultarSubConceptos(Long conceptoid);

}
