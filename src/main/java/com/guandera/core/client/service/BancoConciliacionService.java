package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoConciliacionDetalle;
import com.guandera.core.shared.model.BancoPagoConciliacion;
import com.guandera.core.shared.model.acceso.UsuarioAdm;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface BancoConciliacionService {

	public void actualizar(BancoConciliacion bancoConciliacion);

	public BancoConciliacion consultarPorId(Long bancoConciliacionid);

	public List<BancoConciliacion> consultarTodos();

	// public List<BancoConciliacion> consultarBancoConciliacionPorAncestor(Long
	// usuarioAdmid);

	public long contar();

	public void crear(BancoConciliacion bancoConciliacion);

	public void eliminar(BancoConciliacion bancoConciliacion);

	public Long siguienteRegistro();

	public void crearBancoConciliacionDetalle(BancoConciliacionDetalle itemBancoConciliacionDetalle);

	public void eliminarBancoConciliacionDetalle(BancoConciliacionDetalle itemBancoConciliacionDetalle);

	public void actualizarBancoConciliacionDetalle(BancoConciliacionDetalle itemBancoConciliacionDetalle);

	public List<BancoConciliacionDetalle> consultarBancoConciliacionDetallesPorBancoConciliacion(
			Long bancoConciliacionid);

	// public List<BancoConciliacionDetalle>
	// consultarBancoConciliacionDetallesPorBancoConciliacionAncestor(Long
	// bancoConciliacionid);

	public List<BancoConciliacion> consultarPorUsuarioAdmCreador(UsuarioAdm usuarioSessionAdmId);

	public List<BancoConciliacionDetalle> consultarDetalleConciliacion();

	public List<BancoPagoConciliacion> consultarConciliacionV1();

}
