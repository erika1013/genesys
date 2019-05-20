package com.guandera.core.server.service.admin;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.admin.CargarBancoService;
import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoEstado;
import com.guandera.core.shared.model.Cobro;
import com.guandera.core.shared.model.CobroEstado;
import com.guandera.core.shared.model.ReciboEstado;
import com.guandera.core.shared.model.ReciboPago;
import com.guandera.core.shared.model.ReciboPagoAbono;
import com.guandera.core.shared.model.ReciboPagoDetalle;
import com.guandera.core.shared.model.TipoPago;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class CargarBancoServiceImpl implements CargarBancoService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public BancoPagoEstado cargarBancoPagoEstado(Long estadoid) {
		Key<BancoPagoEstado> k = Key.create(BancoPagoEstado.class, estadoid);
		return ofy().load().key(k).now();
	}

	@Override
	public void cargarPagoBanco(BancoPago pago) {

		ofy().save().entity(pago).now();

	}

	@Override
	public boolean exitePago(BancoPago pago) {

		BancoPago pago1 = ofy().load().type(BancoPago.class).filter("referencia1", pago.getReferencia1())
				.filter("referencia2", pago.getReferencia2()).filter("fechaSistema", pago.getFechaSistema())
				.filter("horaSistema", pago.getHoraSistema()).filter("terminal", pago.getTerminal())
				.filter("talon", pago.getTalon()).first().now();

		if (pago1 != null) {
			System.out.println("Pago ya Exite: " + pago.getReferencia2());
			return true;
		} else {

			System.out.println("Pago Cargado: " + pago.getReferencia2());

			return false;
		}

	}

	@Override
	public void aplicarPagoBanco() {

		Integer BPEstadoId = 1; // cargado
		Key<BancoPagoEstado> kBPEstado = Key.create(BancoPagoEstado.class, BPEstadoId);

		Integer BPEstadoId2 = 2; // Procesado
		Key<BancoPagoEstado> kBPEstado2 = Key.create(BancoPagoEstado.class, BPEstadoId2);
		BancoPagoEstado BPEstado2 = ofy().load().key(kBPEstado2).now();

		Integer BPEstadoId3 = 3; // aplicado
		Key<BancoPagoEstado> kBPEstado3 = Key.create(BancoPagoEstado.class, BPEstadoId3);
		BancoPagoEstado BPEstado3 = ofy().load().key(kBPEstado3).now();

		BancoPagoEstado BPEstado = ofy().load().key(kBPEstado).now();

		List<BancoPago> listaPagos = ofy().load().type(BancoPago.class).filter("estado", kBPEstado).list();

		for (BancoPago pago : listaPagos) {

			if (pago.getReferencia2() != 0) {

				if (existeReciboPendiente(pago.getReferencia2())) {

					ReciboPagoAbono abono = new ReciboPagoAbono();

					ReciboPago recibo = ReciboPendiente(pago.getReferencia2());

					Long tipoid = 1L;

					TipoPago tipoPago = new TipoPago();
					tipoPago.setTipoPagoid(tipoid);

					if (pago.getValorTotal() == recibo.getValor1()) {

						tipoPago.setTipoPagoid(tipoid);
					}

					if (pago.getValorTotal() == recibo.getValor2()) {
						tipoid = 2L;

						tipoPago.setTipoPagoid(tipoid);
					}

					if (pago.getValorTotal() == recibo.getValor3()) {
						tipoid = 3L;

						tipoPago.setTipoPagoid(tipoid);
					}

					abono.setTipoPago(tipoPago);
					abono.setReciboPago(recibo);
					abono.setValorPago(pago.getValorTotal());

					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

					Date fechapago = new Date();

					try {

						fechapago = formatter.parse(pago.getFechaProceso().toString());

					} catch (ParseException e) {
						e.printStackTrace();
					}

					abono.setFechaPago(fechapago);

					abono.setUsuarioCreacion(pago.getUsuarioCreacion());
					abono.setFechaCreacion(new Date());
					abono.setUsuarioModificacion(pago.getUsuarioCreacion());
					abono.setFechaModificacion(new Date());

					pagar(abono);

					pago.setEstado(BPEstado3);
					ofy().save().entity(pago).now();

					// System.out.println("Nombre del estado
					// 3:"+BPEstado3.getBancoPagoEstadonombre());

				} else {

					pago.setEstado(BPEstado2);
					ofy().save().entity(pago).now();
					// System.out.println("Nombre del estado
					// 2:"+BPEstado2.getBancoPagoEstadonombre());
				}

			} else {

				pago.setEstado(BPEstado2);
				ofy().save().entity(pago).now();
				// System.out.println("Nombre del estado
				// 2.1:"+BPEstado2.getBancoPagoEstadonombre());
			}
		}

	}

	private ReciboPago ReciboPendiente(Long referencia2) {
		Integer REstadoId = 1; // Pendiente
		Key<ReciboEstado> kREstado = Key.create(ReciboEstado.class, REstadoId);

		ReciboPago recibo = ofy().load().type(ReciboPago.class).filter("numero", referencia2).filter("estado", kREstado)
				.first().now();
		return recibo;
	}

	private boolean existeReciboPendiente(Long referencia2) {

		Integer REstadoId = 1; // Pendiente
		Key<ReciboEstado> kREstado = Key.create(ReciboEstado.class, REstadoId);

		ReciboPago recibo = ofy().load().type(ReciboPago.class).filter("numero", referencia2).filter("estado", kREstado)
				.first().now();

		if (recibo != null) {
			System.out.println("Recibo Exite: " + recibo.getReferencia2());
			return true;
		} else {

			System.out.println("Recibo no Exite: ");

			return false;
		}

	}

	private void pagar(ReciboPagoAbono itemPago) {

		double valorAbono = 0;

		double valorAplicar = 0;
		double valorPendiente = 0;
		double valorPagado = 0;
		valorAplicar = itemPago.getValorPago();

		Integer cobroEstadoId = 2; // Pagado
		Key<CobroEstado> kcobroEstado = Key.create(CobroEstado.class, cobroEstadoId);
		CobroEstado cobroEstado = ofy().load().key(kcobroEstado).now();

		Integer reciboEstadoId = 2; // Pagado
		Key<ReciboEstado> kreciboEstado = Key.create(ReciboEstado.class, reciboEstadoId);

		ReciboEstado reciboEstado = ofy().load().key(kreciboEstado).now();

		ofy().save().entity(itemPago).now();

		Key<ReciboPago> kreciboPago = Key.create(ReciboPago.class, itemPago.getReciboPago().getReciboid());

		ReciboPago reciboPago = ofy().load().key(kreciboPago).now();

		List<ReciboPagoAbono> listaPagos = ofy().load().type(ReciboPagoAbono.class).filter("reciboPago", kreciboPago)
				.list();

		for (ReciboPagoAbono pago : listaPagos) {

			valorAbono += pago.getValorPago();

		}

		reciboPago.setValorAbonado(valorAbono);
		reciboPago.setFechaPago(new Date());

		if (valorAbono >= reciboPago.getValor1()) {
			reciboPago.setEstado(reciboEstado);

		}

		ofy().save().entity(reciboPago).now();

		// verificaci�n Actualizacion de Cobros

		List<ReciboPagoDetalle> listaDetalle = ofy().load().type(ReciboPagoDetalle.class)
				.filter("reciboPago", kreciboPago).order("periodo").order("ordenAplicacion").list();

		for (ReciboPagoDetalle detalle : listaDetalle) {

			Key<Cobro> k = Key.create(Cobro.class, detalle.getCobro().getCobroid());

			Cobro cobro = ofy().load().key(k).now();

			if (cobro.getValorAbonado() < cobro.getValor1()) {

				valorPendiente = cobro.getValor1() - cobro.getValorAbonado();
				valorPagado = cobro.getValorAbonado();

				if (valorPendiente <= valorAplicar && valorPendiente > 0) {

					cobro.setValorAbonado(valorPendiente + valorPagado);

					valorAplicar -= valorPendiente;
					cobro.setEstado(cobroEstado);
					ofy().save().entity(cobro).now();

				} else {

					cobro.setValorAbonado(valorPagado + valorAplicar);
					valorAplicar = 0;
					ofy().save().entity(cobro).now();

				}

			}

		}

	}

}