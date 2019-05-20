package com.guandera.core.client.service;

import java.util.List;

import com.guandera.core.shared.model.Cobro;
import com.guandera.core.shared.model.CobroCalendario;
import com.guandera.core.shared.model.ReciboEstado;
import com.guandera.core.shared.model.ReciboPago;
import com.guandera.core.shared.model.ReciboPagoAbono;
import com.guandera.core.shared.model.ReciboPagoDetalle;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoPago;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface ReciboPagoService {

	public void actualizar(ReciboPago reciboPago);

	public ReciboPago consultarPorId(Long reciboPagoid);

	public List<ReciboPago> consultarTodos();

	public long contar();

	public void crear(ReciboPago reciboPago);

	public Long siguienteRegistro();

	public List<ReciboPagoDetalle> consultarDetalleReciboPago(Long reciboid);

	public List<TipoPago> consultarTiposdePago();

	public void pagar(ReciboPagoAbono itemPago);

	public List<ReciboPago> consultarRecibosPendientes();

	public List<ReciboPagoAbono> consultarReciboPagoAbonos();

	public void eliminarDetalle(ReciboPagoDetalle itemDetalle);

	public List<CobroCalendario> consultarCobroCalendario();

	public List<Sede> consultarSedes();

	public List<ReciboPago> ConsultarPaqueteRecibos(Long calendarioid, Long sedeid, Long gradoid);

	public void validarRecibo(ReciboPago itemSeleccionado);

	public List<ReciboEstado> consultarReciboEstado();

	public Integer consultarNroPagosRecibidos();

	public List<ReciboPagoAbono> consultarRangoPagosRecibidos(Integer nroInicial, Integer nroFinal);

	public Cobro consultarCobroPorId(Long cobroid);

	public void crearDetalle(ReciboPagoDetalle itemDetalle);

	public List<ReciboPago> ConsultarRecibosReImpresion();

}
