package com.guandera.talento.empleado.client.service;

import java.util.List;

import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoDetalle;
import com.guandera.talento.empleado.shared.model.TipoContrato;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaNovedad;

public interface EmpleadoContratoService {

	public void actualizar(EmpleadoContrato empleadoContrato);

	public void actualizarestadoempleado(Empleado empleado);

	public EmpleadoContrato consultarPorId(Long empleadocontratoid);

	public List<EmpleadoContrato> consultarTodos();

	public long contar();

	public void crear(EmpleadoContrato empleadoContrato);

	public void eliminar(EmpleadoContrato empleadoContrato);

	public Long siguienteRegistro();

	public List<TipoContrato> consultarTipocontratos();

	public List<Empleado> consultarEmpleado();

	public void eliminarDetalleContrato(EmpleadoContratoDetalle itemDetalleContrato);

	public void actualizarDetalleContrato(EmpleadoContratoDetalle itemDetalleContrato);

	public void crearDetalleContrato(EmpleadoContratoDetalle itemDetalleContrato);

	public List<EmpleadoContratoDetalle> consultarDetalleContrato(Long empleadocontratoid);

	public List<NominaConcepto> consultarNominaConcepto();

	public List<NominaNovedad> consultarNominaNovedad(Long nominaPagoid);

	public void crearNominaNovedad(NominaNovedad itemDetalleContrato);

	public void actualizarNominaNovedad(NominaNovedad itemDetalleContrato);

	public void eliminarNominaNovedad(NominaNovedad itemDetalleContrato);

	public List<EmpleadoContrato> consultarContratoEmpleado(Empleado empleado);

}
