package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.CuentaCobroEstado;
import com.guandera.core.shared.model.Moneda;
import com.guandera.core.shared.model.Proveedor;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface CuentaCobroService {

	public void actualizar(CuentaCobro cuentaCobro);

	public CuentaCobro consultarPorId(Long nominapagoid);

	public List<CuentaCobro> consultarTodos();

	public long contar();

	public void crear(CuentaCobro cuentaCobro);

	public void eliminar(CuentaCobro cuentaCobro);

	public Long siguienteRegistro();

	public List<Proveedor> consultarProveedores();

	public List<CuentaCobroEstado> consultarEstados();

	public List<Moneda> consultarMonedas();

}
