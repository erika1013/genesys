package com.guandera.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;
import com.guandera.core.shared.model.*;
import com.guandera.proyecto.shared.model.*;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;
import com.guandera.talento.aspirante.shared.model.AspiranteAutorizacion;
import com.guandera.talento.aspirante.shared.model.AspiranteCambioEstado;
import com.guandera.talento.aspirante.shared.model.AspiranteCompetencia;
import com.guandera.talento.aspirante.shared.model.AspiranteEstado;
import com.guandera.talento.aspirante.shared.model.AspiranteExperiencia;
import com.guandera.talento.aspirante.shared.model.AspiranteFormacion;
import com.guandera.talento.aspirante.shared.model.AspiranteIdioma;
import com.guandera.talento.aspirante.shared.model.AspiranteObservacion;
import com.guandera.talento.aspirante.shared.model.AspiranteReferencia;
import com.guandera.talento.empleado.shared.model.*;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaConceptoTipo;
import com.guandera.talento.nomina.shared.model.NominaNovedad;
import com.guandera.talento.nomina.shared.model.NominaPago;
import com.guandera.talento.nomina.shared.model.NominaPagoDetalle;
import com.guandera.talento.nomina.shared.model.NominaPagoNovedad;
import com.guandera.talento.shared.model.*;
import com.guandera.core.shared.model.acceso.Acceso;
import com.guandera.core.shared.model.acceso.Menu;
import com.guandera.core.shared.model.acceso.Opcion;
import com.guandera.core.shared.model.acceso.Rol;
import com.guandera.core.shared.model.acceso.Submenu;
import com.guandera.core.shared.model.acceso.Usuario;

public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		// ObjectifyService.init(); activar con objectify 6.0

		ObjectifyService.register(Acceso.class);
		ObjectifyService.register(Menu.class);
		ObjectifyService.register(Rol.class);

		ObjectifyService.register(Submenu.class);
		ObjectifyService.register(Usuario.class);
		ObjectifyService.register(Opcion.class);

		// Compania

		ObjectifyService.register(Compania.class);
		ObjectifyService.register(Sede.class);
		ObjectifyService.register(Servicio.class);
		ObjectifyService.register(Producto.class);
		ObjectifyService.register(Convenio.class);
		ObjectifyService.register(Descuento.class);
		ObjectifyService.register(CentroCostos.class);
		ObjectifyService.register(Moneda.class);

		ObjectifyService.register(CompaniaImpuesto.class);
		ObjectifyService.register(CompaniaCuenta.class);
		ObjectifyService.register(Acuerdo.class);

		ObjectifyService.register(TipoPersona.class);
		ObjectifyService.register(TipoIdentificacion.class);
		ObjectifyService.register(TipoAcudiente.class);
		ObjectifyService.register(TipoServicio.class);
		ObjectifyService.register(TipoCobro.class);

		ObjectifyService.register(CompaniaCargo.class);

		ObjectifyService.register(TipoContrato.class);
		ObjectifyService.register(EmpresaSeguridadSocial.class);

		// empleado

		ObjectifyService.register(Empleado.class);
		ObjectifyService.register(EmpleadoAcudiente.class);
		
		ObjectifyService.register(EmpleadoCompetencia.class);
		ObjectifyService.register(EmpleadoExperiencia.class);
		ObjectifyService.register(EmpleadoFormacion.class);
		ObjectifyService.register(EmpleadoIdioma.class);
		ObjectifyService.register(EmpleadoObservacion.class);
		ObjectifyService.register(EmpleadoReferencia.class);

		ObjectifyService.register(EmpleadoEstado.class);
		ObjectifyService.register(EmpleadoSeguridadSocial.class);
		ObjectifyService.register(EmpleadoInformacionAcademica.class);
		ObjectifyService.register(EmpleadoInformacionLaboral.class);

		ObjectifyService.register(EmpleadoContrato.class);
		ObjectifyService.register(EmpleadoContratoDetalle.class);

		ObjectifyService.register(NominaConcepto.class);
		ObjectifyService.register(NominaConceptoTipo.class);
		ObjectifyService.register(NominaNovedad.class);
		ObjectifyService.register(NominaPago.class);
		ObjectifyService.register(NominaPagoDetalle.class);
		ObjectifyService.register(EmpleadoContratoEstado.class);
		ObjectifyService.register(NominaPagoNovedad.class);

		// Curso

		// proveedor
		ObjectifyService.register(Proveedor.class);
		ObjectifyService.register(ProveedorContacto.class);
		ObjectifyService.register(ProveedorPago.class);
		ObjectifyService.register(ProveedorPagoEstado.class);
		ObjectifyService.register(ProveedorPagoRetencion.class);
		ObjectifyService.register(ProveedorRetencion.class);

		// Control de proceso mensuales

		ObjectifyService.register(ControlMensual.class);

		// Cobros

		ObjectifyService.register(Cobro.class);
		ObjectifyService.register(CobroEstado.class);
		ObjectifyService.register(CobroControl.class);
		ObjectifyService.register(CobroCalendario.class);

		// recibos de pago

		ObjectifyService.register(ReciboSecuencia.class);
		ObjectifyService.register(ReciboEstado.class);
		ObjectifyService.register(ReciboPago.class);
		ObjectifyService.register(ReciboPagoDetalle.class);
		ObjectifyService.register(ReciboPagoAbono.class);
		ObjectifyService.register(ReciboPagoControl.class);

		ObjectifyService.register(ReciboReImpresion.class);

		// --Pagos desde el convenio

		ObjectifyService.register(BancoPagoEstado.class);
		ObjectifyService.register(BancoPago.class);
		ObjectifyService.register(BancoPagoConciliacion.class);

		ObjectifyService.register(BancoConciliacion.class);
		ObjectifyService.register(BancoConciliacionDetalle.class);

		ObjectifyService.register(TipoPago.class);

		// Gastos

		ObjectifyService.register(GastoConcepto.class);
		ObjectifyService.register(GastoSubConcepto.class);
		ObjectifyService.register(Gasto.class);

		// tarea

		// Cliente

		ObjectifyService.register(Cliente.class);
		ObjectifyService.register(ClienteCargo.class);
		ObjectifyService.register(ClienteContacto.class);

		// Cuenta Cobro

		ObjectifyService.register(CuentaCobro.class);
		ObjectifyService.register(CuentaCobroEstado.class);
		// Factura
		ObjectifyService.register(Factura.class);
		ObjectifyService.register(FacturaDetalle.class);
		ObjectifyService.register(FacturaEstado.class);

		// Impuestos
		ObjectifyService.register(ImpuestoConcepto.class);

		// Pago a proveedores

		ObjectifyService.register(PagoTipo.class);

		// Admisiones
		// Proyecto

		ObjectifyService.register(AsignacionEstado.class);
		ObjectifyService.register(Asignacion.class);
		ObjectifyService.register(Proyecto.class);
		ObjectifyService.register(ProyectoEtapa.class);
		ObjectifyService.register(ProyectoTipo.class);

		ObjectifyService.register(Requerimiento.class);
		ObjectifyService.register(RequerimientoEstado.class);
		ObjectifyService.register(RequerimientoTipo.class);

		ObjectifyService.register(Tiempo.class);
		ObjectifyService.register(Tarea.class);
		ObjectifyService.register(TareaEstado.class);
		ObjectifyService.register(ProyectoEtapaCambio.class);

		ObjectifyService.register(RolProyecto.class);
		ObjectifyService.register(EquipoCliente.class);
		ObjectifyService.register(EquipoCompania.class);

		ObjectifyService.register(ActividadTipo.class);
		ObjectifyService.register(Actividad.class);

		// Talento Humano

		ObjectifyService.register(Aspirante.class);
		ObjectifyService.register(AspiranteCambioEstado.class);
		ObjectifyService.register(AspiranteEstado.class);
		ObjectifyService.register(AspiranteCompetencia.class);
		ObjectifyService.register(AspiranteExperiencia.class);
		ObjectifyService.register(AspiranteFormacion.class);
		ObjectifyService.register(AspiranteIdioma.class);
		ObjectifyService.register(AspiranteObservacion.class);
		ObjectifyService.register(AspiranteReferencia.class);
		ObjectifyService.register(ReferenciaTipo.class);
		ObjectifyService.register(Ciudad.class);
		ObjectifyService.register(Competencia.class);
		ObjectifyService.register(CompetenciaTipo.class);
		ObjectifyService.register(Departamento.class);
		ObjectifyService.register(EstadoCivil.class);
		ObjectifyService.register(FormacionEstado.class);
		ObjectifyService.register(Idioma.class);
		ObjectifyService.register(Institucion.class);
		ObjectifyService.register(NivelEducativo.class);
		ObjectifyService.register(ProgramaAcademico.class);

		ObjectifyService.register(AspiranteAcceso.class);
		ObjectifyService.register(AspiranteAutorizacion.class);
		
		
	}
}
