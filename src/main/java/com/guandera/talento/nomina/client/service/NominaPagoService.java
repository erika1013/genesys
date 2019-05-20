package com.guandera.talento.nomina.client.service;

import java.util.List;

import com.guandera.core.shared.model.Sede;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaPago;
import com.guandera.talento.nomina.shared.model.NominaPagoDetalle;
import com.guandera.talento.nomina.shared.model.NominaPagoNovedad;

/**
 * 
 * @author Fredi Javier Velasco Villarreal- GUANDERA S.A.S
 */

public interface NominaPagoService {

	public void actualizar(NominaPago nominaPago);

	public NominaPago consultarPorId(Long nominapagoid);

	public List<NominaPagoDetalle> consultarNomina(Integer numeroidentificacion, String periodo);

	public List<NominaPagoNovedad> consultarNominaNovedad(Integer numeroidentificacion, String periodo);

	public List<EmpleadoContrato> consultarEmpleadoContrato();

	public List<NominaPago> consultarTodos();

	public long contar();

	public void crear(NominaPago nominaPago, EmpleadoContrato empleadoContrato);

	public void eliminar(NominaPago nominaPago);

	public Long siguienteRegistro();

	public List<NominaConcepto> consultarNominaConcepto();

	// Nomina Detalle
	public void eliminarNominaPagoDetalle(NominaPagoDetalle itemNominaPagoDetalle);

	public void actualizarNominaPagoDetalle(NominaPagoDetalle itemNominaPagoDetalle);

	public void crearNominaPagoDetalle(NominaPagoDetalle itemNominaPagoDetalle);

	public List<NominaPagoDetalle> consultarNominaPagoDetalle(Long empleadocontratoid);

	public List<NominaPagoNovedad> consultarNominaPagoNovedad(Long nominaPagoid);

	public void eliminarNominaNovedad(NominaPagoNovedad itemNominaPagoNovedad);

	public NominaPago consultarNominaPago(Long numeroIdentificacion, String periodo);

	public Sede consultarSede(Long numeroIdentificacion);

	public Empleado consultarEmpleado(Long numeroIdentificacion);

	public void crearLiquidacion(NominaPago nominaPago);

}
