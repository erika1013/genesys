package com.guandera.core.server.service.acceso;

import static com.googlecode.objectify.ObjectifyService.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.guandera.core.client.service.acceso.ConfigurarService;
import com.guandera.core.shared.model.BancoConciliacion;
import com.guandera.core.shared.model.BancoConciliacionDetalle;
import com.guandera.core.shared.model.BancoPago;
import com.guandera.core.shared.model.BancoPagoConciliacion;
import com.guandera.core.shared.model.Cobro;
import com.guandera.core.shared.model.CobroCalendario;
import com.guandera.core.shared.model.CobroControl;
import com.guandera.core.shared.model.CobroEstado;
import com.guandera.core.shared.model.ControlMensual;
import com.guandera.core.shared.model.Convenio;
import com.guandera.core.shared.model.ReciboEstado;
import com.guandera.core.shared.model.ReciboPago;
import com.guandera.core.shared.model.ReciboPagoAbono;
import com.guandera.core.shared.model.ReciboPagoControl;
import com.guandera.core.shared.model.ReciboPagoDetalle;
import com.guandera.core.shared.model.ReciboReImpresion;
import com.guandera.core.shared.model.ReciboSecuencia;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.Servicio;
import com.guandera.core.shared.model.TipoCobro;
import com.guandera.core.shared.model.TipoPago;
import com.guandera.core.shared.model.TipoServicio;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.talento.empleado.shared.model.Empleado;

/**
 * 
 * @author Fredi Javier Velasco Villarreal
 */
public class ConfigurarServiceImpl implements ConfigurarService, Serializable {

	private static final long serialVersionUID = 6997095638234553392L;

	@Override
	public void actualizarTipoServicio() {

		Integer tipoCobroId = 2; // Mensual
		Key<TipoCobro> ktipo = Key.create(TipoCobro.class, tipoCobroId);

		TipoCobro tipoCobro = ofy().load().key(ktipo).now();

		List<TipoServicio> listaServicios = ofy().load().type(TipoServicio.class).list();

		for (TipoServicio tipoServicio : listaServicios) {

			tipoServicio.setTipoCobro(tipoCobro);
			ofy().save().entity(tipoServicio).now();

		}

	}

	@Override
	public void actualizarEmpleadoNombres() {

		List<Empleado> listaEmpleado = ofy().load().type(Empleado.class).list();

		for (Empleado empleado : listaEmpleado) {

			String nombreApellido = empleado.getPrimerNombre().trim() + ' ' + empleado.getSegundoNombre().trim() + ' '
					+ empleado.getPrimerApellido().trim() + ' ' + empleado.getSegundoApellido().trim();

			String apellidoNombre = empleado.getPrimerApellido().trim() + ' ' + empleado.getSegundoApellido().trim()
					+ ' ' + empleado.getPrimerNombre().trim() + ' ' + empleado.getSegundoNombre().trim();

			empleado.setApellidoNombre(apellidoNombre);
			empleado.setNombreApellido(nombreApellido);

			ofy().save().entity(empleado).now();

		}

	}

	@Override
	public void actualizarCodigoSede() {

		List<Sede> listaSede = ofy().load().type(Sede.class).list();

		for (Sede sede : listaSede) {

			sede.setSedecodigo("01");

			ofy().save().entity(sede).now();

		}

	}

	@Override
	public void actualizarServicio() {

		Integer convenioId = 1; // Sin Convenio
		Key<Convenio> kconvenio = Key.create(Convenio.class, convenioId);

		Convenio convenio = ofy().load().key(kconvenio).now();

		List<Servicio> listaServicio = ofy().load().type(Servicio.class).list();

		for (Servicio servicio : listaServicio) {

			// servicio.setConvenio(convenio);

			ofy().save().entity(servicio).now();

		}

	}

	@Override
	public void crearRol(Rol rol) {
		ofy().save().entity(rol).now();

	}

	@Override
	public void crearVista(Opcion vista) {
		ofy().save().entity(vista).now();

	}

	@Override
	public void crearMenu(Menu menu) {
		ofy().save().entity(menu).now();

	}

	@Override
	public void crearSubmenu(Submenu submenu) {
		ofy().save().entity(submenu).now();

	}

	@Override
	public void crearAcceso(Acceso acceso) {
		ofy().save().entity(acceso).now();

	}

	@Override
	public Sede cargarSede(String codigoSede) {
		return ofy().load().type(Sede.class).filter("sedecodigo", codigoSede).first().now();
	}

	@Override
	public void crearServicio(Servicio servicio) {
		ofy().save().entity(servicio).now();

	}

	@Override
	public Servicio cargarServicio(Long tipoServicioId, Integer tarifa) {

		Key<TipoServicio> kTipoServicio = Key.create(TipoServicio.class, tipoServicioId);

		Servicio servicio = ofy().load().type(Servicio.class).filter("tipoServicio", kTipoServicio)
				.filter("codigoTarifa", tarifa).first().now();

		return servicio;
	}

	@Override
	public void reiniciarCobros() {

		// 1. Control
		List<CobroControl> listaControl = ofy().load().type(CobroControl.class).list();

		for (CobroControl control : listaControl) {

			ofy().delete().entity(control).now();

		}

		// 2. Detalle

		List<ReciboPagoDetalle> listaDetalle = ofy().load().type(ReciboPagoDetalle.class).list();

		for (ReciboPagoDetalle detalle : listaDetalle) {

			ofy().delete().entity(detalle).now();

		}

		// 3. Recibo

		List<ReciboPago> listaRecibo = ofy().load().type(ReciboPago.class).list();

		for (ReciboPago recibo : listaRecibo) {

			ofy().delete().entity(recibo).now();

		}
		// 4. Cobro

		List<Cobro> listaCobro = ofy().load().type(Cobro.class).list();

		for (Cobro cobro : listaCobro) {

			ofy().delete().entity(cobro).now();

		}

	}

	@Override
	public CobroCalendario cargarCalendarioPerido(String periodo) {
		CobroCalendario calendario = ofy().load().type(CobroCalendario.class).filter("periodo", periodo).first().now();

		return calendario;
	}

	@Override
	public Servicio cargarServicioPorCodigo(long codigoServicio) {

		Servicio servicio = ofy().load().type(Servicio.class).filter("codigoServicio", codigoServicio).first().now();

		return servicio;
	}

	@Override
	public Long siguienteNroCobro() {

		long siguiente = 0;

		Cobro cobro = new Cobro();

		try {
			cobro = ofy().load().type(Cobro.class).order("-numero").first().now();
			siguiente = cobro.getNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public Integer siguienteNroRecibo() {
		Integer nroRecibo = secuenciaRecibo();
		nroRecibo += 1;
		actualizarSecuencia(nroRecibo);
		return nroRecibo;

	}

	private Integer secuenciaRecibo() {

		Integer secuenciaId = 1; // ID Concecutivo recibo de pago

		Integer nroSiguiente = 0; // Concecutivo recibo de pago

		Key<ReciboSecuencia> ksecuencia = Key.create(ReciboSecuencia.class, secuenciaId);
		ReciboSecuencia secuencia = ofy().load().key(ksecuencia).now();

		nroSiguiente = secuencia.getReciboSecuencia();

		return nroSiguiente;
	}

	private void actualizarSecuencia(Integer nroSiguiente) {
		Integer secuenciaId = 1; // ID Concecutivo recibo de pago

		Key<ReciboSecuencia> ksecuencia = Key.create(ReciboSecuencia.class, secuenciaId);
		ReciboSecuencia secuencia = ofy().load().key(ksecuencia).now();

		secuencia.setReciboSecuencia(nroSiguiente);

		ofy().save().entity(secuencia).now();

	}

	@Override
	public void crearCobro(Cobro cobro) {

		Long nroLote = siguienteLoteCobro();
		cobro.setNumeroLote(nroLote);

		ofy().save().entity(cobro).now();

	}

	private String calcularDigito(String valorString) {

		int base1[] = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		int base2[] = { 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1 };
		int calculo[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

		char[] char1 = valorString.toCharArray();
		int longitud = 14 - char1.length;

		for (int i = 0; i < char1.length; i++) {
			calculo[longitud + i] = Character.getNumericValue(valorString.charAt(i));
		}

		int digitoUno = calcular(calculo, base1);

		int digitoDos = calcular(calculo, base2);

		// TODO Auto-generated method stub
		return "" + digitoUno + "" + digitoDos;
	}

	private int calcular(int[] calculo, int[] base1) {

		int verificacion = 0;

		for (int j = 0; j < 14; j++) {
			int valor = calculo[j] * base1[j];

			if (valor > 9) {

				int decena = valor / 10;
				int unidad = valor % 10;
				verificacion += (decena + unidad);
			} else {
				verificacion += valor;
			}
		}

		int digito = verificacion % 10;

		return digito;
	}

	private Long siguienteLoteRecibo() {
		long siguiente = 0;

		ReciboPago recibo = new ReciboPago();

		try {
			recibo = ofy().load().type(ReciboPago.class).order("-numeroLote").first().now();
			siguiente = recibo.getNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	private Long siguienteLoteCobro() {

		long siguiente = 0;

		Cobro cobro = new Cobro();

		try {
			cobro = ofy().load().type(Cobro.class).order("-numeroLote").first().now();
			siguiente = cobro.getNumero();
		} catch (Exception e) {

			siguiente = 0;
		}

		return siguiente + 1;
	}

	@Override
	public void reiniciarBaseCobros() {

		// Control cobro
		List<CobroControl> lista1 = ofy().load().type(CobroControl.class).list();

		for (CobroControl detalle : lista1) {

			ofy().delete().entity(detalle).now();

		}

		// Cobro
		List<Cobro> lista2 = ofy().load().type(Cobro.class).list();

		for (Cobro detalle : lista2) {

			ofy().delete().entity(detalle).now();

		}

		// Recibo
		List<ReciboPago> lista3 = ofy().load().type(ReciboPago.class).list();

		for (ReciboPago detalle : lista3) {

			ofy().delete().entity(detalle).now();

		}

		// ReciboDetalle
		List<ReciboPagoDetalle> lista4 = ofy().load().type(ReciboPagoDetalle.class).list();

		for (ReciboPagoDetalle detalle : lista4) {

			ofy().delete().entity(detalle).now();

		}

		// ReciboAbonos
		List<ReciboPagoAbono> lista5 = ofy().load().type(ReciboPagoAbono.class).list();

		for (ReciboPagoAbono detalle : lista5) {

			ofy().delete().entity(detalle).now();

		}

		// ReciboAbonos
		List<ReciboPagoControl> lista6 = ofy().load().type(ReciboPagoControl.class).list();

		for (ReciboPagoControl detalle : lista6) {

			ofy().delete().entity(detalle).now();

		}

		// ReciboAbonos
		List<ControlMensual> lista7 = ofy().load().type(ControlMensual.class).list();

		for (ControlMensual detalle : lista7) {

			ofy().delete().entity(detalle).now();

		}

	}

	@Override
	public void actualizarConciliacion() {

		List<BancoPagoConciliacion> listaOld = ofy().load().type(BancoPagoConciliacion.class).list();

		for (BancoPagoConciliacion old : listaOld) {

			BancoConciliacion conciliacion = new BancoConciliacion();

			BancoPago pago = old.getPago();

			ReciboPago recibo = old.getRecibo();

			if (recibo == null) {
				System.out.println("Recibo Nulo");

			}

			System.out.println("Prueba linea 1");

			// conciliacion.setEstudianteCodigo(old.getRecibo().getEstudiante()
			// .getEstudiantecodigo());

			// System.out.println("Prueba linea 2 Estudiante
			// "+old.getRecibo().getEstudiante()
			// .getEstudiantecodigo());

			conciliacion.setPago(pago);
			System.out.println("Prueba linea 2");
			conciliacion.setFechaPago(pago.getFechaProceso());
			conciliacion.setValorTotal(pago.getValorTotal());
			System.out.println("Prueba linea 3");
			try {
				conciliacion.setValorConciliado(recibo.getValorAbonado());
			} catch (Exception e) {
				conciliacion.setValorConciliado(0.0);
			}
			System.out.println("Prueba linea 4");
			conciliacion.setValorPendiente(conciliacion.getValorTotal() - conciliacion.getValorConciliado());
			System.out.println("Prueba linea 5");
			conciliacion.setUsuarioCreacion(old.getUsuarioCreacion());
			conciliacion.setFechaCreacion(old.getFechaCreacion());
			// conciliacion.setUsuarioModificacion(old.getUsuarioModificacion());
			// conciliacion.setFechaModificacion(old.getFechaModificacion());

			ofy().save().entity(conciliacion).now();

			BancoConciliacionDetalle detalle = new BancoConciliacionDetalle();

			detalle.setBancoConciliacion(conciliacion);
			detalle.setNumeroRecibo(recibo.getNumero());
			detalle.setValorAplicado(recibo.getValorAbonado());
			System.out.println("Prueba linea 6");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String fechaAlfa = formatter.format(recibo.getFechaPago());

			detalle.setFechaAplicacion(Integer.parseInt(fechaAlfa));
			System.out.println("Prueba linea 7");
			detalle.setUsuarioCreacion(old.getUsuarioCreacion());
			detalle.setFechaCreacion(old.getFechaCreacion());
			// detalle.setUsuarioModificacion(old.getUsuarioModificacion());
			// detalle.setFechaModificacion(old.getFechaModificacion());

			ofy().save().entity(detalle).now();

			System.out.println("Prueba linea 8");

		}

	}

	@Override
	public void actualizarCodigoServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizarDetalleRecibo(Usuario usuario) {
		// TODO Auto-generated method stub

	}

}