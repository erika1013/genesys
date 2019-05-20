package com.guandera.talento.empleado.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.talento.empleado.client.service.EmpleadoContratoService;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoDetalle;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoEstado;
import com.guandera.talento.empleado.shared.model.EmpleadoReferencia;
import com.guandera.talento.empleado.shared.model.TipoContrato;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaNovedad;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class EmpleadoContratoServiceImpl implements EmpleadoContratoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(EmpleadoContrato empleadoContrato) {
		ofy().save().entity(empleadoContrato).now();
	}

	@Override
	public EmpleadoContrato consultarPorId(Long id) {
		Key<EmpleadoContrato> k = Key.create(EmpleadoContrato.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<EmpleadoContrato> consultarTodos() {
		return ofy().load().type(EmpleadoContrato.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(EmpleadoContrato.class).count();
		return cont;

	}

	@Override
	public void crear(EmpleadoContrato empleadoContrato) {

		Integer estadoEmpleadoId = 1; // Activo

		Key<EmpleadoContratoEstado> kEstado = Key.create(EmpleadoContratoEstado.class, estadoEmpleadoId);

		EmpleadoContratoEstado estadoContratoEstado = ofy().load().key(kEstado).now();

		empleadoContrato.setEmpleadoContratoEstado(estadoContratoEstado);

		ofy().save().entity(empleadoContrato).now();
		
		List<NominaConcepto> nominaConceptoList = ofy().load().type(NominaConcepto.class).list();
		
		
		try {
			for (NominaConcepto nominaConcepto : nominaConceptoList) {

				if (nominaConcepto.getConceptoTipo().isObligatorio() == true) {
					EmpleadoContratoDetalle empleadoContratoDetalle = new EmpleadoContratoDetalle();

					empleadoContratoDetalle.setEmpleadoContrato(empleadoContrato);
					empleadoContratoDetalle.setNominaConcepto(nominaConcepto);
					empleadoContratoDetalle.setValor(nominaConcepto.getNominaconceptoValor());
					empleadoContratoDetalle.setFactor(nominaConcepto.getNominaconceptofactor());

					empleadoContratoDetalle.setFechaCreacion(new Date());
					empleadoContratoDetalle.setFechaModificacion(new Date());

					ofy().save().entity(empleadoContratoDetalle).now();

				}

			}
		} catch (Exception e) {

		}

	}

	@Override
	public void eliminar(EmpleadoContrato empleadoContrato) {
		ofy().delete().entity(empleadoContrato).now();

	}

	@Override
	public Long siguienteRegistro() {

		EmpleadoContrato empleadoContrato = new EmpleadoContrato();
		long siguiente = 0;

		try {
			empleadoContrato = ofy().load().type(EmpleadoContrato.class).order("-numeroContrato").first().now();
			siguiente = empleadoContrato.getNumeroContrato();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;

	}

	@Override
	public List<TipoContrato> consultarTipocontratos() {

		return ofy().load().type(TipoContrato.class).list();
	}

	@Override
	public List<NominaConcepto> consultarNominaConcepto() {

		return ofy().load().type(NominaConcepto.class).order("nominaconceptocodigo").list();
	}

	@Override
	public List<Empleado> consultarEmpleado() {
		return ofy().load().type(Empleado.class).filter("contratoempleado", false).list();

	}

	// contrato Detalle.

	@Override
	public List<EmpleadoContratoDetalle> consultarDetalleContrato(Long empleadocontratoid) {
		Key<EmpleadoContrato> kEmpleadoContrato = Key.create(EmpleadoContrato.class, empleadocontratoid);

		List<EmpleadoContratoDetalle> listaDetalleContrato = new ArrayList<EmpleadoContratoDetalle>();

		listaDetalleContrato = ofy().load().type(EmpleadoContratoDetalle.class)
				.filter("empleadoContrato", kEmpleadoContrato).list();

		return listaDetalleContrato;
	}

	@Override
	public void crearDetalleContrato(EmpleadoContratoDetalle detalleContrato) {
		ofy().save().entity(detalleContrato).now();

	}

	@Override
	public void actualizarDetalleContrato(EmpleadoContratoDetalle detalleContrato) {
		ofy().save().entity(detalleContrato).now();

	}

	@Override
	public void eliminarDetalleContrato(EmpleadoContratoDetalle detalleContrato) {
		ofy().delete().entity(detalleContrato).now();

	}

	@Override
	public List<NominaNovedad> consultarNominaNovedad(Long empleadocontratoid) {
		Key<EmpleadoContrato> kEmpleadoContrato = Key.create(EmpleadoContrato.class, empleadocontratoid);

		List<NominaNovedad> listaNominaNovedad = new ArrayList<NominaNovedad>();

		listaNominaNovedad = ofy().load().type(NominaNovedad.class).filter("empleadoContrato", kEmpleadoContrato)
				.list();

		return listaNominaNovedad;
	}

	@Override
	public void crearNominaNovedad(NominaNovedad itemNominaNovedad) {
		ofy().save().entity(itemNominaNovedad).now();

	}

	@Override
	public void actualizarNominaNovedad(NominaNovedad itemNominaNovedad) {
		ofy().save().entity(itemNominaNovedad).now();

	}

	@Override
	public void eliminarNominaNovedad(NominaNovedad itemNominaNovedad) {
		ofy().delete().entity(itemNominaNovedad).now();

	}

	@Override
	public void actualizarestadoempleado(Empleado empleado) {
		ofy().save().entity(empleado).now();

	}

	@Override
	public List<EmpleadoContrato> consultarContratoEmpleado(Empleado empleado) {
		return ofy().load().type(EmpleadoContrato.class).filter("empleado", empleado).list();
	}

}