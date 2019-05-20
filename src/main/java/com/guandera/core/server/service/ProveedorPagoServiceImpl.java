package com.guandera.core.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.ProveedorPagoService;
import com.guandera.core.shared.model.CuentaCobro;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.core.shared.model.Proveedor;
import com.guandera.core.shared.model.ProveedorPago;
import com.guandera.core.shared.model.ProveedorPagoEstado;
import com.guandera.core.shared.model.ProveedorPagoRetencion;
import com.guandera.core.shared.model.ProveedorRetencion;

public class ProveedorPagoServiceImpl implements ProveedorPagoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(ProveedorPago proveedorPago) {
		ofy().save().entity(proveedorPago).now();
	}

	@Override
	public ProveedorPago consultarPorId(Long id) {
		Key<ProveedorPago> k = Key.create(ProveedorPago.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<ProveedorPago> consultarTodos() {
		return ofy().load().type(ProveedorPago.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(ProveedorPago.class).count();
		return cont;

	}

	@Override
	public void eliminar(ProveedorPago proveedorPago) {
		ofy().delete().entity(proveedorPago).now();

	}

	@Override
	public Long siguienteRegistro() {
		return null;
		/*
		 * ProveedorPago proveedorPago = new ProveedorPago(); long siguiente =
		 * 0;
		 * 
		 * try { proveedorPago = ofy().load().type(ProveedorPago.class)
		 * .order("-numeroNomina").first().now(); siguiente = proveedorPago.();
		 * } catch (Exception e) {
		 * 
		 * siguiente = 0; }
		 * 
		 * return siguiente + 1;
		 */

	}

	@Override
	public void crear(ProveedorPago proveedorPago, Proveedor proveedor) {

		ofy().save().entity(proveedorPago).now();

		List<ProveedorRetencion> listaProveedorRetencion = ofy().load().type(ProveedorRetencion.class)
				.filter("proveedor", proveedor).list();

		for (ProveedorRetencion proveedorRetencion : listaProveedorRetencion) {
			ProveedorPagoRetencion proveedorPagoRetencion = new ProveedorPagoRetencion();

			proveedorPagoRetencion.setFactor(proveedorRetencion.getImpuestoConcepto().getImpuestoconceptofactor());
			proveedorPagoRetencion.setImpuestoConcepto(proveedorRetencion.getImpuestoConcepto());

			Double valorTotal = proveedorPago.getPagovalortotal();
			Double valor = ((valorTotal * proveedorRetencion.getImpuestoConcepto().getImpuestoconceptofactor()));
			proveedorPagoRetencion.setValor(valor);
			proveedorPagoRetencion.setPagoproveedor(proveedorPago);

			ofy().save().entity(proveedorPagoRetencion).now();

		}

		List<ProveedorPagoRetencion> listaProveedorPagoRetencion = ofy().load().type(ProveedorPagoRetencion.class)
				.filter("pagoproveedor", proveedorPago).list();
		double valorneto = 0;

		long a = listaProveedorPagoRetencion.size();

		for (ProveedorPagoRetencion retencionPago : listaProveedorPagoRetencion) {
			a--;
			System.out.println("--a--" + a);

			long x1 =  retencionPago.getImpuestoConcepto().getObligacionde();
			long x2 = 2;
			
			//if (retencionPago.getImpuestoConcepto().getObligacionde() == 2) {
				
			if (x1 == x2) {
				valorneto += retencionPago.getValor();
				System.out.println("ValorNeto" + valorneto);
			}

		}
		
		double valorTotal2 = proveedorPago.getPagovalortotal();
		proveedorPago.setPagovalorneto(valorTotal2 - valorneto);
		System.out.println("valorneto--- " + valorneto + "--a--" + a);

		ofy().save().entity(proveedorPago).now();

	}

	@Override
	public List<ProveedorPagoEstado> consultarEstado() {
		return ofy().load().type(ProveedorPagoEstado.class).list();
	}

	@Override
	public List<PagoTipo> consultarTipoPago() {

		return ofy().load().type(PagoTipo.class).list();
	}

	@Override
	public List<Proveedor> consultarProveedores() {

		return ofy().load().type(Proveedor.class).list();
	}

	@Override
	public Proveedor consultarProveedorvalido(Long proveedoridentificacion) {

		return null;

	}

	@Override
	public List<ProveedorRetencion> consultarProveedoresconRetencion() {
		return ofy().load().type(ProveedorRetencion.class).list();
	}

	@Override
	public void crear(ProveedorPago proveedorPago) {
		// TODO Auto-generated method stub

	}

	@Override
	public CuentaCobro consultarCuentaCobroProveedor(Long proveedoridentificacion, Long numeroCuenta) {
		Proveedor proveedor = ofy().load().type(Proveedor.class)
				.filter("proveedoridentificacion", proveedoridentificacion).first().now();

		CuentaCobro cuentacobro = ofy().load().type(CuentaCobro.class).filter("proveedor", proveedor)
				.filter("numeroCuenta", numeroCuenta).first().now();

		return cuentacobro;

	}

	@Override
	public ProveedorRetencion consultarRetencion(Long proveedoridentificacion) {
		Proveedor proveedor = ofy().load().type(Proveedor.class)
				.filter("proveedoridentificacion", proveedoridentificacion).first().now();

		ProveedorRetencion proveedorR = ofy().load().type(ProveedorRetencion.class).filter("proveedor", proveedor)
				.first().now();

		return proveedorR;
	}

	@Override
	public Proveedor consultarProveedor(Long proveedoridentificacion) {
		Proveedor proveedor = ofy().load().type(Proveedor.class)
				.filter("proveedoridentificacion", proveedoridentificacion).first().now();
		return proveedor;
	}

	@Override
	public List<ProveedorPagoRetencion> consultarProveedorPagoRetencion(Long pagoproveedor) {
		Key<ProveedorPago> kProveedorPago = Key.create(ProveedorPago.class, pagoproveedor);

		List<ProveedorPagoRetencion> listaProveedorPagoRetencion = new ArrayList<ProveedorPagoRetencion>();

		listaProveedorPagoRetencion = ofy().load().type(ProveedorPagoRetencion.class)
				.filter("pagoproveedor", kProveedorPago).list();

		return listaProveedorPagoRetencion;
	}

}