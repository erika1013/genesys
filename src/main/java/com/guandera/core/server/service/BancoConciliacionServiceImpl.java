package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.BancoConciliacionService;
import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoConciliacionDetalle;
import com.guandera.core.shared.model.BancoPagoConciliacion;
import com.guandera.core.shared.model.acceso.UsuarioAdm;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class BancoConciliacionServiceImpl implements BancoConciliacionService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(BancoConciliacion bancoConciliacion) {
		ofy().save().entity(bancoConciliacion).now();
	}

	@Override
	public BancoConciliacion consultarPorId(Long id) {
		Key<BancoConciliacion> k = Key.create(BancoConciliacion.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<BancoConciliacion> consultarTodos() {
		return ofy().load().type(BancoConciliacion.class).list();
	}

	@Override
	public List<BancoConciliacion> consultarPorUsuarioAdmCreador(UsuarioAdm usuarioAdmCreacion) {
		return ofy().load().type(BancoConciliacion.class).filter("usuarioAdmCreacion", usuarioAdmCreacion).list();
	}

	/*
	 * @Override public List<BancoConciliacion>
	 * consultarBancoConciliacionPorAncestor(Long usuarioAdmid) {
	 * Key<UsuarioAdm> parentKey= Key.create(UsuarioAdm.class, usuarioAdmid);
	 * System.out.println("key3...."+parentKey);// Creating the Ancestor key
	 * 
	 * 
	 * return
	 * ofy().load().type(BancoConciliacion.class).ancestor(parentKey).list(); }
	 */

	@Override
	public long contar() {

		long cont = ofy().load().type(BancoConciliacion.class).count();
		return cont;

	}

	@Override
	public void crear(BancoConciliacion bancoConciliacion) {
		ofy().save().entity(bancoConciliacion).now();
	}

	@Override
	public void eliminar(BancoConciliacion bancoConciliacion) {
		ofy().delete().entity(bancoConciliacion).now();

	}

	@Override
	public Long siguienteRegistro() {

		long siguiente = ofy().load().type(BancoConciliacion.class).count();
		return siguiente + 1;
	}

	/*
	 * @Override public List<BancoConciliacionDetalle>
	 * consultarBancoConciliacionDetallesPorBancoConciliacionAncestor(Long
	 * bancoConciliacionid) {
	 * 
	 * Key<BancoConciliacion> kBancoConciliacion =
	 * Key.create(BancoConciliacion.class, bancoConciliacionid);
	 * 
	 * 
	 * List<BancoConciliacionDetalle> listaBancoConciliacionDetalles = new
	 * ArrayList<BancoConciliacionDetalle>();
	 * 
	 * listaBancoConciliacionDetalles =
	 * ofy().load().type(BancoConciliacionDetalle.class).ancestor(
	 * kBancoConciliacion) .order("numeroRecibo").list();
	 * 
	 * return listaBancoConciliacionDetalles;
	 * 
	 * }
	 */

	@Override
	public void crearBancoConciliacionDetalle(BancoConciliacionDetalle detalle) {

		ofy().save().entity(detalle).now();

		actualizarConciliacion(detalle);

	}

	private void actualizarConciliacion(BancoConciliacionDetalle detalle) {

		List<BancoConciliacionDetalle> listaDetalle = ofy().load().type(BancoConciliacionDetalle.class)
				.filter("bancoConciliacion", detalle.getBancoConciliacion()).list();

		double valorConciliado = 0.0;

		double valorPendiente = 0.0;

		BancoConciliacion conciliacion = detalle.getBancoConciliacion();

		double valorTotal = conciliacion.getValorTotal();

		for (BancoConciliacionDetalle detalle1 : listaDetalle) {

			valorConciliado += detalle1.getValorAplicado();

		}

		valorPendiente = valorTotal - valorConciliado;

		conciliacion.setValorConciliado(valorConciliado);
		conciliacion.setValorPendiente(valorPendiente);

		ofy().save().entity(conciliacion).now();

	}

	@Override
	public void eliminarBancoConciliacionDetalle(BancoConciliacionDetalle bancoConciliacionDetalle) {
		ofy().delete().entity(bancoConciliacionDetalle).now();

		actualizarConciliacion(bancoConciliacionDetalle);

	}

	@Override
	public void actualizarBancoConciliacionDetalle(BancoConciliacionDetalle itemBancoConciliacionDetalle) {
		ofy().save().entity(itemBancoConciliacionDetalle).now();

		actualizarConciliacion(itemBancoConciliacionDetalle);

	}

	@Override
	public List<BancoConciliacionDetalle> consultarBancoConciliacionDetallesPorBancoConciliacion(
			Long bancoConciliacionid) {
		Key<BancoConciliacion> kBancoConciliacion = Key.create(BancoConciliacion.class, bancoConciliacionid);

		List<BancoConciliacionDetalle> listaBancoConciliacionDetalles = new ArrayList<BancoConciliacionDetalle>();

		listaBancoConciliacionDetalles = ofy().load().type(BancoConciliacionDetalle.class)
				.filter("bancoConciliacion", kBancoConciliacion).order("numeroRecibo").list();

		return listaBancoConciliacionDetalles;
	}

	@Override
	public List<BancoConciliacionDetalle> consultarDetalleConciliacion() {
		return ofy().load().type(BancoConciliacionDetalle.class).list();
	}

	@Override
	public List<BancoPagoConciliacion> consultarConciliacionV1() {
		return ofy().load().type(BancoPagoConciliacion.class).list();
	}

}