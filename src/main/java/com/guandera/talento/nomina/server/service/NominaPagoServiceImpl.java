package com.guandera.talento.nomina.server.service;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.shared.model.Sede;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoDetalle;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoEstado;
import com.guandera.talento.nomina.client.service.NominaPagoService;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaNovedad;
import com.guandera.talento.nomina.shared.model.NominaPago;
import com.guandera.talento.nomina.shared.model.NominaPagoDetalle;
import com.guandera.talento.nomina.shared.model.NominaPagoNovedad;

public class NominaPagoServiceImpl implements NominaPagoService, Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizar(NominaPago nominaPago) {
		ofy().save().entity(nominaPago).now();
	}

	@Override
	public NominaPago consultarPorId(Long id) {
		Key<NominaPago> k = Key.create(NominaPago.class, id);
		return ofy().load().key(k).now();
	}

	@Override
	public List<NominaPago> consultarTodos() {
		return ofy().load().type(NominaPago.class).list();
	}

	@Override
	public long contar() {

		long cont = ofy().load().type(NominaPago.class).count();
		return cont;

	}

	private void calcularLiquidacion(NominaPago nominaPago) {

		double valorTotalDeduccionDetalle = 0;
		double valorTotalDevengadoDetalle = 0;
		double valorTotalDeduccionNovedad = 0;
		double valorTotalDevengadoNovedad = 0;
		
		EmpleadoContrato empleadoContrato = nominaPago.getEmpleadoContrato();

		double valorIBC = empleadoContrato.getContratosalario();

		
		System.out.println("Nombre Empleado: " + nominaPago.getNumeroNomina() + " "
				+ nominaPago.getEmpleadoContrato().getEmpleado().getApellidoNombre());

		List<EmpleadoContratoDetalle> listaEmpleadoContratoDetalle = ofy().load().type(EmpleadoContratoDetalle.class)
				.filter("empleadoContrato", empleadoContrato).list();

		List<NominaNovedad> listaNovedad = ofy().load().type(NominaNovedad.class)
				.filter("empleadoContrato", empleadoContrato).filter("periodo", nominaPago.getPeriodo()).list();

		for (EmpleadoContratoDetalle empleadoContratoDetalle : listaEmpleadoContratoDetalle) {

			NominaPagoDetalle nominaPagoDetalle = new NominaPagoDetalle();
			nominaPagoDetalle.setNominaConcepto(empleadoContratoDetalle.getNominaConcepto());
			nominaPagoDetalle.setPagoNomina(nominaPago);

			double valorConcepto = 0;

			if (empleadoContratoDetalle.isFactor()) {
				valorConcepto = valorIBC * empleadoContratoDetalle.getValor();
			} else {
				valorConcepto = empleadoContratoDetalle.getValor();
			}

			if (empleadoContratoDetalle.getNominaConcepto().getConceptoTipo().getTipo() == 1) {
				nominaPagoDetalle.setValordevengado(valorConcepto);
				nominaPagoDetalle.setValordeduccion(0);
			} else {
				nominaPagoDetalle.setValordevengado(0);
				nominaPagoDetalle.setValordeduccion(valorConcepto);
			}

			if (empleadoContratoDetalle.getNominaConcepto().getConceptoTipo().getConceptopor() == 1L) {
				nominaPagoDetalle.setEmpresa("X");
				nominaPagoDetalle.setConceptopor(1L);
			} else if (empleadoContratoDetalle.getNominaConcepto().getConceptoTipo().getConceptopor() == 2L) {
				nominaPagoDetalle.setEmpleado("X");
				nominaPagoDetalle.setConceptopor(2L);

			}

			ofy().save().entity(nominaPagoDetalle).now();

		}

		for (NominaNovedad nominaNovedad : listaNovedad) {
			if (nominaNovedad.getNominanovedadvalor() > 0) {
				NominaPagoNovedad nominaPagoNovedad = new NominaPagoNovedad();
				nominaPagoNovedad.setNominaNovedad(nominaNovedad);
				nominaPagoNovedad.setNominaPago(nominaPago);

				if (nominaNovedad.getTipo() == 1) {

					nominaPagoNovedad.setValorDevengado(nominaNovedad.getNominanovedadvalor());

				} else if (nominaNovedad.getTipo() == 2) {

					nominaPagoNovedad.setValorDeduccion(nominaNovedad.getNominanovedadvalor());

				}
				ofy().save().entity(nominaPagoNovedad).now();
			}

		}

		List<NominaPagoDetalle> listaNominaPagoDetalle = ofy().load().type(NominaPagoDetalle.class)
				.filter("pagoNomina", nominaPago).filter("conceptopor", 2L).list();

		if (listaNominaPagoDetalle.size() > 0) {
			for (NominaPagoDetalle nominaPagoDetalle : listaNominaPagoDetalle) {
				// if (nominaPagoDetalle.getConceptopor() == 2L)
				valorTotalDeduccionDetalle += nominaPagoDetalle.getValordeduccion();

				valorTotalDevengadoDetalle += nominaPagoDetalle.getValordevengado();

			}
		}

		List<NominaPagoNovedad> listaNominaPagoNovedad = ofy().load().type(NominaPagoNovedad.class)
				.filter("nominaPago", nominaPago).list();

		if (listaNominaPagoNovedad.size() > 0) {
			for (NominaPagoNovedad nominaPagonovedad : listaNominaPagoNovedad) {

				valorTotalDeduccionNovedad += nominaPagonovedad.getValorDeduccion();
				valorTotalDevengadoNovedad += nominaPagonovedad.getValorDevengado();
			}
		}

		Double TotalDeducciones = valorTotalDeduccionNovedad + valorTotalDeduccionDetalle;
		Double TotalDevengado = nominaPago.getEmpleadoContrato().getContratosalario() + valorTotalDevengadoNovedad
				+ valorTotalDevengadoDetalle;
		nominaPago.setTotalDeducciones(TotalDeducciones);
		nominaPago.setTotalDevengado(TotalDevengado);
		Double SalarioTotal = TotalDevengado - TotalDeducciones;

		nominaPago.setValorNetoPago(SalarioTotal);
		ofy().save().entity(nominaPago).now();

	}

	@Override
	public void eliminar(NominaPago nominaPago) {
		ofy().delete().entity(nominaPago).now();

	}

	@Override
	public Long siguienteRegistro() {

		NominaPago nominaPago = new NominaPago();
		long siguiente = 0;

		try {
			nominaPago = ofy().load().type(NominaPago.class).order("-numeroNomina").first().now();
			siguiente = nominaPago.getNumeroNomina();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;

	}

	@Override
	public List<NominaConcepto> consultarNominaConcepto() {

		return ofy().load().type(NominaConcepto.class).order("nominaconceptocodigo").list();
	}

	@Override
	public List<EmpleadoContrato> consultarEmpleadoContrato() {

		return ofy().load().type(EmpleadoContrato.class).list();

	}

	@Override
	public List<NominaPagoDetalle> consultarNominaPagoDetalle(Long nominapagodetalleid) {
		Key<NominaPago> kNominaPago = Key.create(NominaPago.class, nominapagodetalleid);

		List<NominaPagoDetalle> listaDetalle = new ArrayList<NominaPagoDetalle>();

		listaDetalle = ofy().load().type(NominaPagoDetalle.class).filter("pagoNomina", kNominaPago).list();

		return listaDetalle;
	}

	@Override
	public void crearNominaPagoDetalle(NominaPagoDetalle nominaPagoDetalle) {
		ofy().save().entity(nominaPagoDetalle).now();

	}

	@Override
	public void actualizarNominaPagoDetalle(NominaPagoDetalle nominaPagoDetalle) {
		ofy().save().entity(nominaPagoDetalle).now();

	}

	@Override
	public void eliminarNominaPagoDetalle(NominaPagoDetalle nominaPagoDetalle) {
		ofy().delete().entity(nominaPagoDetalle).now();

	}

	@Override
	public List<NominaPagoNovedad> consultarNominaPagoNovedad(Long nominaPagoid) {
		Key<NominaPago> kNominaPago = Key.create(NominaPago.class, nominaPagoid);

		List<NominaPagoNovedad> listaNominaNovedad = new ArrayList<NominaPagoNovedad>();

		listaNominaNovedad = ofy().load().type(NominaPagoNovedad.class).filter("nominaPago", kNominaPago).list();

		return listaNominaNovedad;
	}

	@Override
	public void eliminarNominaNovedad(NominaPagoNovedad itemNominaPagoNovedad) {
		ofy().delete().entity(itemNominaPagoNovedad).now();

	}

	@Override
	public List<NominaPagoDetalle> consultarNomina(Integer numeroidentificacion, String periodo) {
		Long empleadoe = 2L;
		Empleado empleado = ofy().load().type(Empleado.class).filter("numeroidentificacion", numeroidentificacion)
				.first().now();

		EmpleadoContrato empleadoContrato = ofy().load().type(EmpleadoContrato.class).filter("empleado", empleado)
				.first().now();

		NominaPago nominaPago = ofy().load().type(NominaPago.class).filter("empleadoContrato", empleadoContrato)
				.filter("periodo", periodo).first().now();

		List<NominaPagoDetalle> listaNominaPagoDetalle = ofy().load().type(NominaPagoDetalle.class)
				.filter("pagoNomina", nominaPago).filter("conceptopor", empleadoe).list();

		return listaNominaPagoDetalle;
	}

	@Override
	public List<NominaPagoNovedad> consultarNominaNovedad(Integer numeroidentificacion, String periodo) {

		Empleado empleado = ofy().load().type(Empleado.class).filter("numeroidentificacion", numeroidentificacion)
				.first().now();

		EmpleadoContrato empleadoContrato = ofy().load().type(EmpleadoContrato.class).filter("empleado", empleado)
				.first().now();

		NominaPago nominaPago = ofy().load().type(NominaPago.class).filter("empleadoContrato", empleadoContrato)
				.filter("periodo", periodo).first().now();

		List<NominaPagoNovedad> listaNominaPagoNovedad = ofy().load().type(NominaPagoNovedad.class)
				.filter("nominaPago", nominaPago).list();

		return listaNominaPagoNovedad;
	}

	@Override
	public NominaPago consultarNominaPago(Long numeroIdentificacion, String periodo) {

		Empleado empleado = ofy().load().type(Empleado.class).filter("numeroidentificacion", numeroIdentificacion)
				.first().now();

		EmpleadoContrato empleadoContrato = ofy().load().type(EmpleadoContrato.class).filter("empleado", empleado)
				.first().now();

		NominaPago nominaPago = ofy().load().type(NominaPago.class).filter("empleadoContrato", empleadoContrato)
				.filter("periodo", periodo).first().now();

		return nominaPago;
	}

	@Override
	public Empleado consultarEmpleado(Long numeroIdentificacion) {
		Empleado empleado = ofy().load().type(Empleado.class).filter("numeroidentificacion", numeroIdentificacion)
				.first().now();
		return empleado;
	}

	@Override
	public Sede consultarSede(Long numeroIdentificacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void crearLiquidacion(NominaPago nominaPago1) {

		Integer contratoEstadoId = 1; // Activo

		Key<EmpleadoContratoEstado> kEstado = Key.create(EmpleadoContratoEstado.class, contratoEstadoId);

		EmpleadoContratoEstado contratoActivo = ofy().load().key(kEstado).now();

		List<EmpleadoContrato> listaContratos = ofy().load().type(EmpleadoContrato.class)
				.filter("empleadoContratoEstado", contratoActivo).list();

		for (EmpleadoContrato contrato : listaContratos) {

			System.out.println("FJVV: Contrato a liquidar");

			NominaPago nominaPago = new NominaPago();
			nominaPago = nominaPago1;
			nominaPago.setPagonominaid(null);
			nominaPago.setNumeroNomina(siguienteNumeroNomina());
			nominaPago.setEmpleadoContrato(contrato);
			
			ofy().save().entity(nominaPago).now();
			

			calcularLiquidacion(nominaPago);

		}

	}

	private Integer siguienteNumeroNomina() {
		NominaPago nominaPago = new NominaPago();
		Integer siguiente = 0;

		try {
			nominaPago = ofy().load().type(NominaPago.class).order("-numeroNomina").first().now();
			siguiente = nominaPago.getNumeroNomina();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public void crear(NominaPago nominaPago, EmpleadoContrato empleadoContrato) {
		// TODO Auto-generated method stub
		
	}

}