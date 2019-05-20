package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.BancoPagoService;
import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoEstado;
import com.guandera.core.shared.model.ReciboPago;
import com.guandera.core.shared.model.acceso.Usuario;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class BancoPagoServiceImpl implements BancoPagoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(BancoPago bancoPago) {
		ofy().save().entity(bancoPago).now();
	}

	@Override
	public BancoPago consultarPorId(Long id) {
		Key<BancoPago> k = Key.create(BancoPago.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<BancoPago> consultarTodos() {
		return ofy().load().type(BancoPago.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(BancoPago.class).count();
		return cont;

	}

	@Override
	public void crear(BancoPago bancoPago) {
		ofy().save().entity(bancoPago).now();
	}

	@Override
	public void eliminar(BancoPago bancoPago) {
		ofy().delete().entity(bancoPago).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(BancoPago.class).count();
		return siguiente + 1;
	}

	@Override
	public boolean existeRecibo(Integer nroRecibo) {
		ReciboPago recibo = ofy().load().type(ReciboPago.class).filter("numero", nroRecibo).first().now();

		if (recibo != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<BancoPago> consultarMovimientoEstado(Integer tipoMovimiento, Long estadoid) {

		int tipom = tipoMovimiento;

		Key<BancoPagoEstado> kEstado = Key.create(BancoPagoEstado.class, estadoid);

		List<BancoPago> listaMovimiento = new ArrayList<BancoPago>();

		listaMovimiento = ofy().load().type(BancoPago.class).filter("tipo", tipom).filter("estado", kEstado)
				.order("fechaProceso").list();

		return listaMovimiento;
	}

	@Override
	public List<BancoPagoEstado> consultarBancoEstados() {

		return ofy().load().type(BancoPagoEstado.class).list();
	}

	@Override
	public void conciliarPago(BancoConciliacion conciliacion, BancoPago bancoPago, Usuario usuario) {

		Integer estadoId = 4; // CONCILIADO
		Key<BancoPagoEstado> kEstado = Key.create(BancoPagoEstado.class, estadoId);

		BancoPagoEstado estado = ofy().load().key(kEstado).now();

		bancoPago.setEstado(estado);

		ofy().save().entity(bancoPago).now();
		ofy().save().entity(conciliacion).now();

	}
}