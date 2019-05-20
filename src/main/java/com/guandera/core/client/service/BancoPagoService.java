package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoEstado;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface BancoPagoService {

	public void actualizar(BancoPago bancoPago);

	public BancoPago consultarPorId(Long bancoPagoid);

	public List<BancoPago> consultarTodos();

	public long contar();

	public void crear(BancoPago bancoPago);

	public void eliminar(BancoPago bancoPago);

	public Long siguienteRegistro();

	public boolean existeRecibo(Integer nroRecibo);

	public void conciliarPago(BancoConciliacion itemConciliacion, BancoPago itemSeleccionado, Usuario usuario);

	public List<BancoPago> consultarMovimientoEstado(Integer tipoMovimiento, Long estadoid);

	public List<BancoPagoEstado> consultarBancoEstados();

}
