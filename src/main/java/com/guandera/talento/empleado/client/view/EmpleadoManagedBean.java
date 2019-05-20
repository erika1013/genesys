package com.guandera.talento.empleado.client.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Departamento;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.empleado.client.service.EmpleadoCompetenciaService;
import com.guandera.talento.empleado.client.service.EmpleadoContratoService;
import com.guandera.talento.empleado.client.service.EmpleadoExperienciaService;
import com.guandera.talento.empleado.client.service.EmpleadoFormacionService;
import com.guandera.talento.empleado.client.service.EmpleadoIdiomaService;
import com.guandera.talento.empleado.client.service.EmpleadoObservacionService;
import com.guandera.talento.empleado.client.service.EmpleadoReferenciaService;
import com.guandera.talento.empleado.client.service.EmpleadoService;
import com.guandera.talento.empleado.server.service.EmpleadoCompetenciaServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoContratoServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoExperienciaServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoFormacionServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoIdiomaServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoObservacionServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoReferenciaServiceImpl;
import com.guandera.talento.empleado.server.service.EmpleadoServiceImpl;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoCompetencia;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoDetalle;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;
import com.guandera.talento.empleado.shared.model.EmpleadoExperiencia;
import com.guandera.talento.empleado.shared.model.EmpleadoFormacion;
import com.guandera.talento.empleado.shared.model.EmpleadoIdioma;
import com.guandera.talento.empleado.shared.model.EmpleadoObservacion;
import com.guandera.talento.empleado.shared.model.EmpleadoReferencia;
import com.guandera.talento.empleado.shared.model.TipoContrato;
import com.guandera.talento.client.service.TalentoService;
import com.guandera.talento.server.service.TalentoServiceImpl;
import com.guandera.talento.shared.model.ReferenciaTipo;
import com.guandera.talento.shared.model.Competencia;
import com.guandera.talento.shared.model.EstadoCivil;
import com.guandera.talento.shared.model.FormacionEstado;
import com.guandera.talento.shared.model.Idioma;
import com.guandera.talento.shared.model.Institucion;
import com.guandera.talento.shared.model.ProgramaAcademico;

@ManagedBean(name = "EmpleadoMB")
@SessionScoped
public class EmpleadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private List<SelectItem> empleadoEstadoItems;
	private Long ciudadIdentificacionId;
	private List<SelectItem> ciudadItems;
	private List<SelectItem> ciudadItems1;
	private List<SelectItem> ciudadItems2;
	private List<SelectItem> ciudadItems3;
	private Long ciudadResidenciaId;

	private Long ciudadId;

	private Long estadoCivilId;

	private List<SelectItem> estadoCivilItems;
	private Long estadoId;
	private EmpleadoCompetencia itemCompetencia;

	private EmpleadoExperiencia itemExperiencia;
	private EmpleadoFormacion itemFormacion;
	private EmpleadoIdioma itemIdioma;
	
	private EmpleadoContrato itemContrato;

	private EmpleadoObservacion itemObservacion;
	private EmpleadoReferencia itemReferencia;
	private Empleado itemSeleccionado;

	private List<EmpleadoCompetencia> listaCompetencia;
	private List<EmpleadoExperiencia> listaExperiencia;

	private List<EmpleadoFormacion> listaFormacion;
	private List<EmpleadoContrato> listaContrato;

	private List<EmpleadoIdioma> listaIdioma;

	private List<Empleado> listaItems;

	private List<EmpleadoObservacion> listaObservacion;

	private List<EmpleadoReferencia> listaReferencia;
	private List<EmpleadoContratoDetalle> listaDetalleContratoItems = new ArrayList<EmpleadoContratoDetalle>();

	private boolean listingEmpleado = false;

	private boolean listingEmpleadoCompetencia = false;

	private boolean listingEmpleadoEstado = false;

	private boolean listingEmpleadoExperiencia = false;

	private boolean listingEmpleadoFormacion = false;
	private boolean listingEmpleadoContrato = false;

	private boolean listingEmpleadoIdioma = false;

	private boolean listingEmpleadoObservacion = false;

	private boolean listingEmpleadoReferencia = false;

	private boolean listingContratoDetalle = false;

	private boolean listingEstadoCivil = false;

	private Long lugarNacimientoId;

	private EmpleadoService servicio;

	private EmpleadoCompetenciaService servicioCompetencia;

	private EmpleadoExperienciaService servicioExperiencia;

	private EmpleadoFormacionService servicioFormacion;
	
	private EmpleadoContratoService servicioContrato;
	

	private EmpleadoIdiomaService servicioIdioma;

	private EmpleadoObservacionService servicioObservacion;

	private EmpleadoReferenciaService servicioReferencia;

	private TalentoService talentoServicio;

	private Long formacionEstadoId;
	private List<SelectItem> formacionEstadoItems;
	private boolean listingFormacionEstado = false;

	private Long institucionId;
	private List<SelectItem> institucionItems;
	private boolean listingInstitucion = false;

	private Long programaAcademicoId;
	private List<SelectItem> programaAcademicoItems;
	private boolean listingProgramaAcademico = false;

	private Long competenciaId;
	private List<SelectItem> competenciaItems;
	private boolean listingCompetencia = false;

	private Long referenciaTipoId;
	private List<SelectItem> referenciaTipoItems;
	private boolean listingReferenciaTipo = false;

	private Long idiomaId;
	private List<SelectItem> idiomaItems;
	private boolean listingIdioma = false;

	private Long departamentoIdentificacionId;
	private Long departamentoNacimientoId;
	private Long departamentoResidenciaId;

	private List<SelectItem> departamentoItems;
	private boolean listingDepartamento = false;

	private List<EmpleadoFormacion> listaFormacionTotal;
	private List<EmpleadoCompetencia> listaCompetenciaTotal;

	private boolean listingFormacionTotal = false;
	private boolean listingCompetenciaTotal = false;

	private Long departamentoId;

	private Long sedeid;
	private List<SelectItem> sedeItems;
	private boolean listingSede = false;

	private Long tipoidentificacionid;
	private List<SelectItem> tipoIdentificacionItems;
	private boolean listingTipoIdentificacion = false;

	private Long cargoid;
	private List<SelectItem> cargoItems;
	private boolean listingCargo = false;

	private Long tipoContratoid;
	private List<SelectItem> tipoContratoItems;
	private boolean listingContrato;
	
	
	
	
	public EmpleadoManagedBean() {

		servicio = new EmpleadoServiceImpl();
		talentoServicio = new TalentoServiceImpl();
		servicioCompetencia = new EmpleadoCompetenciaServiceImpl();
		servicioExperiencia = new EmpleadoExperienciaServiceImpl();
		servicioFormacion = new EmpleadoFormacionServiceImpl();
		servicioContrato = new EmpleadoContratoServiceImpl();
		servicioIdioma = new EmpleadoIdiomaServiceImpl();
		servicioObservacion = new EmpleadoObservacionServiceImpl();
		servicioReferencia = new EmpleadoReferenciaServiceImpl();

	}

	public String actualizar() {
		try {

			Ciudad lugarNacimiento = new Ciudad();

			EstadoCivil estadoCivil = new EstadoCivil();

			EmpleadoEstado estado = new EmpleadoEstado();

			Ciudad ciudadResidencia = new Ciudad();

			Ciudad ciudadIdentificacion = new Ciudad();
			TipoIdentificacion tipoid = new TipoIdentificacion();
			Sede sede = new Sede();
			CompaniaCargo cargo = new CompaniaCargo();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			sede.setSedeid(sedeid);
			cargo.setCompaniacargoid(cargoid);

			lugarNacimiento.setCiudadId(lugarNacimientoId);
			estadoCivil.setEstadoCivilId(estadoCivilId);
			estado.setEstadoId(estadoId);
			ciudadResidencia.setCiudadId(ciudadResidenciaId);
			ciudadIdentificacion.setCiudadId(ciudadIdentificacionId);

			String nombreApellido = itemSeleccionado.getPrimerNombre().trim() + ' '
					+ itemSeleccionado.getSegundoNombre().trim() + ' ' + itemSeleccionado.getPrimerApellido().trim()
					+ ' ' + itemSeleccionado.getSegundoApellido().trim();

			String apellidoNombre = itemSeleccionado.getPrimerApellido().trim() + ' '
					+ itemSeleccionado.getSegundoApellido().trim() + ' ' + itemSeleccionado.getPrimerNombre().trim()
					+ ' ' + itemSeleccionado.getSegundoNombre().trim();

			itemSeleccionado.setApellidoNombre(apellidoNombre);
			itemSeleccionado.setNombreApellido(nombreApellido);

			itemSeleccionado.setEstadoCivil(estadoCivil);
			itemSeleccionado.setEstado(estado);
			itemSeleccionado.setLugarNacimiento(lugarNacimiento);

			itemSeleccionado.setCiudadResidencia(ciudadResidencia);
			itemSeleccionado.setCiudadIdentificacion(ciudadIdentificacion);

			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setSede(sede);
			itemSeleccionado.setCargo(cargo);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("EmpleadoUpdated");
			listingEmpleado = false;
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarCompetencia() {
		try {

			Competencia competencia = new Competencia();
			competencia.setCompetenciaId(competenciaId);
			itemCompetencia.setCompetencia(competencia);

			itemCompetencia.setUsuarioModificacion(usuario());
			itemCompetencia.setFechaModificacion(new Date());

			servicioCompetencia.actualizar(itemCompetencia);
			mensajeInfo("EmpleadoCompetenciaUpdated");
			listingEmpleadoCompetencia = false;
			return prepararConsultaCompetencia();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarExperiencia() {
		try {

			itemExperiencia.setUsuarioModificacion(usuario());
			itemExperiencia.setFechaModificacion(new Date());

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemExperiencia.setCiudad(ciudad);

			servicioExperiencia.actualizar(itemExperiencia);
			mensajeInfo("EmpleadoExperienciaUpdated");
			listingEmpleadoExperiencia = false;
			return prepararConsultaExperiencia();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	
	public String actualizarFormacion() {
		try {

			FormacionEstado formacionEstado = new FormacionEstado();
			formacionEstado.setFormacionEstadoId(formacionEstadoId);
			itemFormacion.setEstado(formacionEstado);

			Institucion institucion = new Institucion();
			institucion.setInstitucionId(institucionId);
			itemFormacion.setInstitucion(institucion);

			ProgramaAcademico programaAcademico = new ProgramaAcademico();
			programaAcademico.setProgramaAcademicoId(programaAcademicoId);
			itemFormacion.setProgramaAcademico(programaAcademico);

			itemFormacion.setUsuarioModificacion(usuario());
			itemFormacion.setFechaModificacion(new Date());

			servicioFormacion.actualizar(itemFormacion);
			mensajeInfo("EmpleadoFormacionUpdated");
			listingEmpleadoFormacion = false;
			return prepararConsultaFormacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}
	
	
	public String actualizarContrato() {

		try {
			
			TipoContrato tipoContrato = new TipoContrato();

			tipoContrato.setTipocontratoid(tipoContratoid);

			itemContrato.setUsuarioModificacion(usuario());
			itemContrato.setFechaModificacion(new Date());

			servicioContrato.actualizar(itemContrato);
			mensajeInfo("EmpleadoContratoUpdated");
			listingEmpleadoFormacion = false;
			return prepararConsultaContrato();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}
	

	
	
	
	
	public String actualizarIdioma() {
		try {

			itemIdioma.setUsuarioModificacion(usuario());
			itemIdioma.setFechaModificacion(new Date());

			Idioma idioma = new Idioma();
			idioma.setIdiomaId(idiomaId);
			itemIdioma.setIdioma(idioma);

			servicioIdioma.actualizar(itemIdioma);
			mensajeInfo("EmpleadoIdiomaUpdated");
			listingEmpleadoIdioma = false;
			return prepararConsultaIdioma();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarObservacion() {
		try {

			itemObservacion.setUsuarioModificacion(usuario());
			itemObservacion.setFechaModificacion(new Date());

			servicioObservacion.actualizar(itemObservacion);
			mensajeInfo("EmpleadoObservacionUpdated");
			listingEmpleadoObservacion = false;
			return prepararConsultaObservacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarReferencia() {
		try {

			itemReferencia.setUsuarioModificacion(usuario());
			itemReferencia.setFechaModificacion(new Date());

			ReferenciaTipo referenciaTipo = new ReferenciaTipo();
			referenciaTipo.setReferenciaTipoId(referenciaTipoId);
			itemReferencia.setTipo(referenciaTipo);

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemReferencia.setCiudad(ciudad);

			servicioReferencia.actualizar(itemReferencia);
			mensajeInfo("EmpleadoReferenciaUpdated");
			listingEmpleadoReferencia = false;
			return prepararConsultaReferencia();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {

			Ciudad lugarNacimiento = new Ciudad();

			EstadoCivil estadoCivil = new EstadoCivil();

			EmpleadoEstado estado = new EmpleadoEstado();

			Ciudad ciudadResidencia = new Ciudad();

			Ciudad ciudadIdentificacion = new Ciudad();
			TipoIdentificacion tipoid = new TipoIdentificacion();
			Sede sede = new Sede();
			CompaniaCargo cargo = new CompaniaCargo();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			sede.setSedeid(sedeid);
			cargo.setCompaniacargoid(cargoid);

			lugarNacimiento.setCiudadId(lugarNacimientoId);
			estadoCivil.setEstadoCivilId(estadoCivilId);
			estado.setEstadoId(estadoId);
			ciudadResidencia.setCiudadId(ciudadResidenciaId);
			ciudadIdentificacion.setCiudadId(ciudadIdentificacionId);

			
			
			
			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setSede(sede);
			itemSeleccionado.setCargo(cargo);

			itemSeleccionado.setEstadoCivil(estadoCivil);
			itemSeleccionado.setEstado(estado);
			itemSeleccionado.setLugarNacimiento(lugarNacimiento);

			itemSeleccionado.setCiudadResidencia(ciudadResidencia);
			itemSeleccionado.setCiudadIdentificacion(ciudadIdentificacion);
			
			calcularEdad() ;
			

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			String nombreApellido = itemSeleccionado.getPrimerNombre().trim() + ' '
					+ itemSeleccionado.getSegundoNombre().trim() + ' ' + itemSeleccionado.getPrimerApellido().trim()
					+ ' ' + itemSeleccionado.getSegundoApellido().trim();

			String apellidoNombre = itemSeleccionado.getPrimerApellido().trim() + ' '
					+ itemSeleccionado.getSegundoApellido().trim() + ' ' + itemSeleccionado.getPrimerNombre().trim()
					+ ' ' + itemSeleccionado.getSegundoNombre().trim();

			itemSeleccionado.setApellidoNombre(apellidoNombre);
			itemSeleccionado.setNombreApellido(nombreApellido);
			itemSeleccionado.setContratoEmpleado(false);

			servicio.crear(itemSeleccionado);
			mensajeInfo("EmpleadoCreated");
			listingEmpleado = false;
			return prepararCreacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearCompetencia() {

		try {

			Competencia competencia = new Competencia();
			competencia.setCompetenciaId(competenciaId);
			itemCompetencia.setCompetencia(competencia);

			itemCompetencia.setEmpleado(itemSeleccionado);
			itemCompetencia.setUsuarioCreacion(usuario());
			itemCompetencia.setFechaCreacion(new Date());
			itemCompetencia.setUsuarioModificacion(usuario());
			itemCompetencia.setFechaModificacion(new Date());

			servicioCompetencia.crear(itemCompetencia);
			mensajeInfo("EmpleadoCompetenciaCreated");
			listingEmpleadoCompetencia = false;
			return prepararCreacionCompetencia();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearExperiencia() {

		try {

			itemExperiencia.setEmpleado(itemSeleccionado);
			itemExperiencia.setUsuarioCreacion(usuario());
			itemExperiencia.setFechaCreacion(new Date());
			itemExperiencia.setUsuarioModificacion(usuario());
			itemExperiencia.setFechaModificacion(new Date());

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemExperiencia.setCiudad(ciudad);

			servicioExperiencia.crear(itemExperiencia);
			mensajeInfo("EmpleadoExperienciaCreated");
			listingEmpleadoExperiencia = false;
			return prepararCreacionExperiencia();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearFormacion() {

		try {

			FormacionEstado formacionEstado = new FormacionEstado();
			formacionEstado.setFormacionEstadoId(formacionEstadoId);
			itemFormacion.setEstado(formacionEstado);

			Institucion institucion = new Institucion();
			institucion.setInstitucionId(institucionId);
			itemFormacion.setInstitucion(institucion);

			ProgramaAcademico programaAcademico = new ProgramaAcademico();
			programaAcademico.setProgramaAcademicoId(programaAcademicoId);
			itemFormacion.setProgramaAcademico(programaAcademico);

			itemFormacion.setEmpleado(itemSeleccionado);

			itemFormacion.setUsuarioCreacion(usuario());
			itemFormacion.setFechaCreacion(new Date());
			itemFormacion.setUsuarioModificacion(usuario());
			itemFormacion.setFechaModificacion(new Date());

			servicioFormacion.crear(itemFormacion);
			mensajeInfo("EmpleadoFormacionCreated");
			listingEmpleadoFormacion = false;
			return prepararCreacionFormacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}
	
	
	public String crearContrato() {

		
			try {

				TipoContrato tipoContrato = new TipoContrato();

				tipoContrato.setTipocontratoid(tipoContratoid);

				itemContrato.setUsuarioCreacion(usuario());
				itemContrato.setNumeroContrato(servicioContrato.siguienteRegistro());
				itemContrato.setFechaCreacion(new Date());
				itemContrato.setUsuarioModificacion(usuario());
				itemContrato.setFechaModificacion(new Date());

				itemContrato.setTipoContrato(tipoContrato);
				itemContrato.setEmpleado(itemSeleccionado);
				
				itemSeleccionado.setContratoEmpleado(true);

				
				servicioContrato.crear(itemContrato);
				servicioContrato.actualizarestadoempleado(itemSeleccionado);
					mensajeInfo("EmpleadoContratoCreated");
				
				return prepararConsultaContrato();
			} catch (Exception e) {
						mensajeError("PersistenceErrorOccured");
						e.getMessage();
						return null;
				}
	
	}
	
	
	
	
	

	public String crearIdioma() {

		try {
			itemIdioma.setEmpleado(itemSeleccionado);
			itemIdioma.setUsuarioCreacion(usuario());
			itemIdioma.setFechaCreacion(new Date());
			itemIdioma.setUsuarioModificacion(usuario());
			itemIdioma.setFechaModificacion(new Date());

			Idioma idioma = new Idioma();
			idioma.setIdiomaId(idiomaId);
			itemIdioma.setIdioma(idioma);

			servicioIdioma.crear(itemIdioma);
			mensajeInfo("EmpleadoIdiomaCreated");
			listingEmpleadoIdioma = false;
			return prepararCreacionIdioma();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearObservacion() {

		try {
			itemObservacion.setEmpleado(itemSeleccionado);
			itemObservacion.setUsuarioCreacion(usuario());
			itemObservacion.setFechaCreacion(new Date());
			itemObservacion.setUsuarioModificacion(usuario());
			itemObservacion.setFechaModificacion(new Date());

			servicioObservacion.crear(itemObservacion);
			mensajeInfo("EmpleadoObservacionCreated");
			listingEmpleadoObservacion = false;
			return prepararCreacionObservacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearReferencia() {

		try {
			itemReferencia.setEmpleado(itemSeleccionado);
			itemReferencia.setUsuarioCreacion(usuario());
			itemReferencia.setFechaCreacion(new Date());
			itemReferencia.setUsuarioModificacion(usuario());
			itemReferencia.setFechaModificacion(new Date());

			ReferenciaTipo referenciaTipo = new ReferenciaTipo();
			referenciaTipo.setReferenciaTipoId(referenciaTipoId);
			itemReferencia.setTipo(referenciaTipo);

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemReferencia.setCiudad(ciudad);

			servicioReferencia.crear(itemReferencia);
			mensajeInfo("EmpleadoReferenciaCreated");
			listingEmpleadoReferencia = false;
			return prepararCreacionReferencia();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingEmpleado = false;
		return prepararLista();
	}

	public String eliminarCompetencia() {
		servicioCompetencia.eliminar(itemCompetencia);
		listingEmpleadoCompetencia = false;
		return prepararListaCompetencia();
	}

	public String eliminarExperiencia() {
		servicioExperiencia.eliminar(itemExperiencia);
		listingEmpleadoExperiencia = false;
		return prepararListaExperiencia();
	}

	public String eliminarFormacion() {
		servicioFormacion.eliminar(itemFormacion);
		listingEmpleadoFormacion = false;
		return prepararListaFormacion();
	}

	public String eliminarIdioma() {
		servicioIdioma.eliminar(itemIdioma);
		listingEmpleadoIdioma = false;
		return prepararListaIdioma();
	}

	public String eliminarObservacion() {
		servicioObservacion.eliminar(itemObservacion);
		listingEmpleadoObservacion = false;
		return prepararListaObservacion();
	}

	public String eliminarReferencia() {
		servicioReferencia.eliminar(itemReferencia);
		listingEmpleadoReferencia = false;
		return prepararListaReferencia();
	}
	
	
	public String eliminarContrato() {
		servicioContrato.eliminar(itemContrato);
		listingEmpleadoContrato = false;
		return prepararListaContrato();
	}
	

	public List<SelectItem> getEmpleadoEstadoItems() {

		if (!listingEmpleadoEstado) {
			listingEmpleadoEstado = true;
			empleadoEstadoItems = new ArrayList<SelectItem>();
			List<EmpleadoEstado> empleadoEstadoList = talentoServicio.listaEmpleadoEstado();

			for (EmpleadoEstado empleadoEstado : empleadoEstadoList) {
				empleadoEstadoItems.add(new SelectItem(empleadoEstado.getEstadoId(), empleadoEstado.getNombre()));

			}
		}

		return empleadoEstadoItems;
	}

	public Long getCiudadIdentificacionId() {
		return ciudadIdentificacionId;
	}

	public Long getCiudadResidenciaId() {
		return ciudadResidenciaId;
	}

	public Long getEstadoCivilId() {
		return estadoCivilId;
	}

	public List<SelectItem> getEstadoCivilItems() {

		if (!listingEstadoCivil) {
			listingEstadoCivil = true;
			estadoCivilItems = new ArrayList<SelectItem>();
			List<EstadoCivil> estadoCivilList = talentoServicio.listaEstadoCivil();

			for (EstadoCivil estadoCivil : estadoCivilList) {
				estadoCivilItems.add(new SelectItem(estadoCivil.getEstadoCivilId(), estadoCivil.getNombre()));

			}
		}

		return estadoCivilItems;
	}

	public Long getEstadoId() {
		return estadoId;
	}

	public EmpleadoCompetencia getItemCompetencia() {
		return itemCompetencia;
	}

	public EmpleadoExperiencia getItemExperiencia() {

		return itemExperiencia;
	}

	public EmpleadoFormacion getItemFormacion() {
		return itemFormacion;
	}

	public EmpleadoIdioma getItemIdioma() {
		return itemIdioma;
	}

	public EmpleadoObservacion getItemObservacion() {
		return itemObservacion;
	}

	public EmpleadoReferencia getItemReferencia() {
		return itemReferencia;
	}

	public Empleado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<EmpleadoCompetencia> getListaCompetencia() {

		if (!listingEmpleadoCompetencia) {
			listingEmpleadoCompetencia = true;
			listaCompetencia = servicioCompetencia.consultarCompetenciasEmpleado(itemSeleccionado);
		}
		return listaCompetencia;
	}

	public List<EmpleadoExperiencia> getListaExperiencia() {

		if (!listingEmpleadoExperiencia) {
			listingEmpleadoExperiencia = true;
			listaExperiencia = servicioExperiencia.consultarExperienciaEmpleado(itemSeleccionado);
		}
		return listaExperiencia;
	}

	public List<EmpleadoFormacion> getListaFormacion() {

		if (!listingEmpleadoFormacion) {
			listingEmpleadoFormacion = true;
			listaFormacion = servicioFormacion.consultarFormacionEmpleado(itemSeleccionado);
		}
		return listaFormacion;
	}

	public List<EmpleadoIdioma> getListaIdioma() {

		if (!listingEmpleadoIdioma) {
			listingEmpleadoIdioma = true;
			listaIdioma = servicioIdioma.consultarIdiomasEmpleado(itemSeleccionado);
		}
		return listaIdioma;
	}

	public List<Empleado> getListaItems() {

		if (!listingEmpleado) {
			listingEmpleado = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public List<EmpleadoObservacion> getListaObservacion() {

		if (!listingEmpleadoObservacion) {
			listingEmpleadoObservacion = true;
			listaObservacion = servicioObservacion.consultarObservacionesEmpleado(itemSeleccionado);
		}
		return listaObservacion;
	}

	public List<EmpleadoReferencia> getListaReferencia() {

		if (!listingEmpleadoReferencia) {
			listingEmpleadoReferencia = true;
			listaReferencia = servicioReferencia.consultarReferenciasEmpleado(itemSeleccionado);
		}
		return listaReferencia;
	}

	
	
	
	
	
	public EmpleadoContrato getItemContrato() {
		return itemContrato;
	}

	public void setItemContrato(EmpleadoContrato itemContrato) {
		this.itemContrato = itemContrato;
	}

	public List<EmpleadoContrato> getListaContrato() {
		
		if (!listingEmpleadoContrato) {
			listingEmpleadoContrato = true;
			listaContrato = servicioContrato.consultarContratoEmpleado(itemSeleccionado);
		}
		
		
		return listaContrato;
	}

	public void setListaContrato(List<EmpleadoContrato> listaContrato) {
		this.listaContrato = listaContrato;
	}

	public Long getLugarNacimientoId() {
		return lugarNacimientoId;
	}

	public String prepararConsulta() {

		listingEmpleadoCompetencia = false;
		listingEmpleadoExperiencia = false;
		listingEmpleadoFormacion = false;
		listingEmpleadoContrato = false;
		listingEmpleadoIdioma = false;
		listingEmpleadoObservacion = false;
		listingEmpleadoReferencia = false;

		return "Detalle";
	}

	public String prepararConsultaCompetencia() {

		return "DetalleCompetencia";
	}

	public String prepararConsultaExperiencia() {

		return "DetalleExperiencia";
	}

	public String prepararConsultaFormacion() {

		return "DetalleFormacion";
	}
	
	public String prepararConsultaContrato() {

		return "DetalleContrato";
	}
	
	

	public String prepararConsultaIdioma() {

		return "DetalleIdioma";
	}

	public String prepararConsultaObservacion() {

		return "DetalleObservacion";
	}

	public String prepararConsultaReferencia() {

		return "DetalleReferencia";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Empleado();

		Integer siguienteCodigo = servicio.siguienteCodigoEmpleado();
		itemSeleccionado.setCodigo(siguienteCodigo);

		lugarNacimientoId = 0l;
		estadoCivilId = 0l;
		estadoId = 0l;
		ciudadResidenciaId = 0l;
		ciudadIdentificacionId = 0l;

		sedeid = (long) 0;
		cargoid = (long) 0;
		tipoidentificacionid = (long) 0;

		departamentoIdentificacionId = 0l;
		departamentoNacimientoId = 0l;
		departamentoResidenciaId = 0l;

		return "Crear";
	}

	public String prepararCreacionCompetencia() {
		competenciaId = 0l;

		itemCompetencia = new EmpleadoCompetencia();
		return "CrearCompetencia";
	}

	public String prepararCreacionExperiencia() {
		itemExperiencia = new EmpleadoExperiencia();
		ciudadId = 0l;
		return "CrearExperiencia";
	}

	public String prepararCreacionFormacion() {
		formacionEstadoId = 0l;
		programaAcademicoId = 0l;
		institucionId = 0l;

		itemFormacion = new EmpleadoFormacion();
		return "CrearFormacion";
	}
	
	
	public String prepararCreacionContrato() {
		
		tipoContratoid = (long) 0;
		itemContrato = new EmpleadoContrato();
		return "CrearContrato";
	}
	
	

	public String prepararCreacionIdioma() {
		idiomaId = 0l;
		itemIdioma = new EmpleadoIdioma();
		return "CrearIdioma";
	}

	public String prepararCreacionObservacion() {
		itemObservacion = new EmpleadoObservacion();
		return "CrearObservacion";
	}

	public String prepararCreacionReferencia() {
		itemReferencia = new EmpleadoReferencia();
		referenciaTipoId = 0l;
		ciudadId = 0l;
		return "CrearReferencia";
	}

	public String prepararEdicion() {

		sedeid = itemSeleccionado.getSede().getSedeid();
		cargoid = itemSeleccionado.getCargo().getCompaniacargoid();
		tipoidentificacionid = itemSeleccionado.getTipoIdentificacion().getTipoidentificacionid();

		lugarNacimientoId = itemSeleccionado.getLugarNacimiento().getCiudadId();
		estadoCivilId = itemSeleccionado.getEstadoCivil().getEstadoCivilId();
		estadoId = itemSeleccionado.getEstado().getEstadoId();
		ciudadResidenciaId = itemSeleccionado.getCiudadResidencia().getCiudadId();
		ciudadIdentificacionId = itemSeleccionado.getCiudadIdentificacion().getCiudadId();

		departamentoNacimientoId = itemSeleccionado.getLugarNacimiento().getDepartamento().getDepartamentoId();
		departamentoResidenciaId = itemSeleccionado.getCiudadResidencia().getDepartamento().getDepartamentoId();

		departamentoIdentificacionId = itemSeleccionado.getCiudadIdentificacion().getDepartamento().getDepartamentoId();

		return "Editar";
	}

	public String prepararEdicionCompetencia() {

		competenciaId = itemCompetencia.getCompetencia().getCompetenciaId();
		return "EditarCompetencia";
	}

	public String prepararEdicionExperiencia() {

		ciudadId = itemExperiencia.getCiudad().getCiudadId();
		return "EditarExperiencia";
	}

	public String prepararEdicionFormacion() {

		formacionEstadoId = itemFormacion.getEstado().getFormacionEstadoId();
		institucionId = itemFormacion.getInstitucion().getInstitucionId();
		programaAcademicoId = itemFormacion.getProgramaAcademico().getProgramaAcademicoId();
		return "EditarFormacion";
	}

	public String prepararEdicionContrato() {

		
		tipoContratoid = itemContrato.getTipoContrato().getTipocontratoid();
		return "EditarContrato";
	}
	
	
	public String prepararEdicionIdioma() {

		idiomaId = itemIdioma.getIdioma().getIdiomaId();
		return "EditarIdioma";
	}

	public String prepararEdicionObservacion() {
		return "EditarObservacion";
	}

	public String prepararEdicionReferencia() {
		referenciaTipoId = itemReferencia.getTipo().getReferenciaTipoId();
		ciudadId = itemReferencia.getCiudad().getCiudadId();

		return "EditarReferencia";
	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararListaCompetencia() {

		return "ListaCompetencia";
	}

	public String prepararListaExperiencia() {

		return "ListaExperiencia";
	}

	public String prepararListaFormacion() {

		return "ListaFormacion";
	}
	
	public String prepararListaContrato() {

		return "ListaContrato";
	}
	

	public String prepararListaIdioma() {

		return "ListaIdioma";
	}

	public String prepararListaObservacion() {

		return "ListaObservacion";
	}

	public String prepararListaReferencia() {

		return "ListaReferencia";
	}

	public String refrescarLista() {

		listingEmpleado = false;
		return "Lista";
	}

	public void setEmpleadoEstadoItems(List<SelectItem> empleadoEstadoItems) {
		this.empleadoEstadoItems = empleadoEstadoItems;
	}

	public void setCiudadIdentificacionId(Long ciudadIdentificacionId) {
		this.ciudadIdentificacionId = ciudadIdentificacionId;
	}

	public void setCiudadResidenciaId(Long ciudadResidenciaId) {
		this.ciudadResidenciaId = ciudadResidenciaId;
	}

	public void setEstadoCivilId(Long estadoCivilId) {
		this.estadoCivilId = estadoCivilId;
	}

	public void setEstadoCivilItems(List<SelectItem> estadoCivilItems) {
		this.estadoCivilItems = estadoCivilItems;
	}

	public void setEstadoId(Long estadoId) {
		this.estadoId = estadoId;
	}

	public void setItemCompetencia(EmpleadoCompetencia itemCompetencia) {
		this.itemCompetencia = itemCompetencia;
	}

	public void setItemExperiencia(EmpleadoExperiencia itemExperiencia) {
		this.itemExperiencia = itemExperiencia;
	}

	public void setItemFormacion(EmpleadoFormacion itemFormacion) {
		this.itemFormacion = itemFormacion;
	}

	public void setItemIdioma(EmpleadoIdioma itemIdioma) {
		this.itemIdioma = itemIdioma;
	}

	public void setItemObservacion(EmpleadoObservacion itemObservacion) {
		this.itemObservacion = itemObservacion;
	}

	public void setItemReferencia(EmpleadoReferencia itemReferencia) {
		this.itemReferencia = itemReferencia;
	}

	public void setItemSeleccionado(Empleado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaCompetencia(List<EmpleadoCompetencia> listaCompetencia) {
		this.listaCompetencia = listaCompetencia;
	}

	public void setListaExperiencia(List<EmpleadoExperiencia> listaExperiencia) {
		this.listaExperiencia = listaExperiencia;
	}

	public void setListaFormacion(List<EmpleadoFormacion> listaFormacion) {
		this.listaFormacion = listaFormacion;
	}

	public void setListaIdioma(List<EmpleadoIdioma> listaIdioma) {
		this.listaIdioma = listaIdioma;
	}

	public void setListaItems(List<Empleado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListaObservacion(List<EmpleadoObservacion> listaObservacion) {
		this.listaObservacion = listaObservacion;
	}

	public void setListaReferencia(List<EmpleadoReferencia> listaReferencia) {
		this.listaReferencia = listaReferencia;
	}

	public void setLugarNacimientoId(Long lugarNacimientoId) {
		this.lugarNacimientoId = lugarNacimientoId;
	}

	public Long getFormacionEstadoId() {
		return formacionEstadoId;
	}

	public void setFormacionEstadoId(Long formacionEstadoId) {
		this.formacionEstadoId = formacionEstadoId;
	}

	public List<SelectItem> getFormacionEstadoItems() {

		if (!listingFormacionEstado) {
			listingFormacionEstado = true;
			formacionEstadoItems = new ArrayList<SelectItem>();
			List<FormacionEstado> formacionEstadoList = talentoServicio.listaFormacionEstado();

			for (FormacionEstado formacionEstado : formacionEstadoList) {
				formacionEstadoItems
						.add(new SelectItem(formacionEstado.getFormacionEstadoId(), formacionEstado.getNombre()));

			}
		}

		return formacionEstadoItems;
	}

	public void setFormacionEstadoItems(List<SelectItem> formacionEstadoItems) {
		this.formacionEstadoItems = formacionEstadoItems;
	}

	public Long getInstitucionId() {
		return institucionId;
	}

	public void setInstitucionId(Long institucionId) {
		this.institucionId = institucionId;
	}

	public List<SelectItem> getInstitucionItems() {

		if (!listingInstitucion) {
			listingInstitucion = true;
			institucionItems = new ArrayList<SelectItem>();
			List<Institucion> institucionList = talentoServicio.listaInstitucion();

			for (Institucion institucion : institucionList) {
				institucionItems.add(new SelectItem(institucion.getInstitucionId(), institucion.getNombre()));

			}
		}

		return institucionItems;
	}

	public void setInstitucionItems(List<SelectItem> institucionItems) {
		this.institucionItems = institucionItems;
	}

	public Long getProgramaAcademicoId() {
		return programaAcademicoId;
	}

	public void setProgramaAcademicoId(Long programaAcademicoId) {
		this.programaAcademicoId = programaAcademicoId;
	}

	public List<SelectItem> getProgramaAcademicoItems() {

		if (!listingProgramaAcademico) {
			listingProgramaAcademico = true;
			programaAcademicoItems = new ArrayList<SelectItem>();
			List<ProgramaAcademico> programaAcademicoList = talentoServicio.listaProgramaAcademico();

			for (ProgramaAcademico programaAcademico : programaAcademicoList) {
				programaAcademicoItems
						.add(new SelectItem(programaAcademico.getProgramaAcademicoId(), programaAcademico.getNombre()));

			}
		}

		return programaAcademicoItems;
	}

	public void setProgramaAcademicoItems(List<SelectItem> programaAcademicoItems) {
		this.programaAcademicoItems = programaAcademicoItems;
	}

	public Long getCompetenciaId() {
		return competenciaId;
	}

	public void setCompetenciaId(Long competenciaId) {
		this.competenciaId = competenciaId;
	}

	public List<SelectItem> getCompetenciaItems() {

		if (!listingCompetencia) {
			listingCompetencia = true;
			competenciaItems = new ArrayList<SelectItem>();
			List<Competencia> competenciaList = talentoServicio.listaCompetencia();

			for (Competencia competencia : competenciaList) {
				competenciaItems.add(new SelectItem(competencia.getCompetenciaId(), competencia.getNombre()));

			}
		}

		return competenciaItems;
	}

	public void setCompetenciaItems(List<SelectItem> competenciaItems) {
		this.competenciaItems = competenciaItems;
	}

	public Long getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(Long ciudadId) {
		this.ciudadId = ciudadId;
	}

	public Long getReferenciaTipoId() {
		return referenciaTipoId;
	}

	public void setReferenciaTipoId(Long referenciaTipoId) {
		this.referenciaTipoId = referenciaTipoId;
	}

	public List<SelectItem> getReferenciaTipoItems() {

		if (!listingReferenciaTipo) {
			listingReferenciaTipo = true;
			referenciaTipoItems = new ArrayList<SelectItem>();
			List<ReferenciaTipo> referenciaTipoList = talentoServicio.listaReferenciaTipo();

			for (ReferenciaTipo referenciaTipo : referenciaTipoList) {
				referenciaTipoItems
						.add(new SelectItem(referenciaTipo.getReferenciaTipoId(), referenciaTipo.getNombre()));

			}
		}

		return referenciaTipoItems;
	}

	public void setReferenciaTipoItems(List<SelectItem> referenciaTipoItems) {
		this.referenciaTipoItems = referenciaTipoItems;
	}

	public Long getIdiomaId() {
		return idiomaId;
	}

	public void setIdiomaId(Long idiomaId) {
		this.idiomaId = idiomaId;
	}

	public List<SelectItem> getIdiomaItems() {

		if (!listingIdioma) {
			listingIdioma = true;
			idiomaItems = new ArrayList<SelectItem>();
			List<Idioma> idiomaList = talentoServicio.listaIdioma();

			for (Idioma idioma : idiomaList) {
				idiomaItems.add(new SelectItem(idioma.getIdiomaId(), idioma.getNombre()));

			}
		}

		return idiomaItems;
	}

	public void setIdiomaItems(List<SelectItem> idiomaItems) {
		this.idiomaItems = idiomaItems;
	}

	public Long getDepartamentoIdentificacionId() {
		return departamentoIdentificacionId;
	}

	public void setDepartamentoIdentificacionId(Long departamentoIdentificacionId) {
		this.departamentoIdentificacionId = departamentoIdentificacionId;
	}

	public Long getDepartamentoNacimientoId() {
		return departamentoNacimientoId;
	}

	public void setDepartamentoNacimientoId(Long departamentoNacimientoId) {
		this.departamentoNacimientoId = departamentoNacimientoId;
	}

	public Long getDepartamentoResidenciaId() {
		return departamentoResidenciaId;
	}

	public void setDepartamentoResidenciaId(Long departamentoResidenciaId) {
		this.departamentoResidenciaId = departamentoResidenciaId;
	}

	public List<SelectItem> getDepartamentoItems() {

		if (!listingDepartamento) {
			listingDepartamento = true;
			departamentoItems = new ArrayList<SelectItem>();
			List<Departamento> departamentoList = talentoServicio.listaDepartamento();

			for (Departamento departamento : departamentoList) {
				departamentoItems.add(new SelectItem(departamento.getDepartamentoId(), departamento.getNombre()));

			}
		}

		return departamentoItems;
	}

	public void setDepartamentoItems(List<SelectItem> departamentoItems) {
		this.departamentoItems = departamentoItems;
	}

	public List<SelectItem> getCiudadItems1() {
		return ciudadItems1;
	}

	public void setCiudadItems1(List<SelectItem> ciudadItems1) {
		this.ciudadItems1 = ciudadItems1;
	}

	public List<SelectItem> getCiudadItems2() {
		return ciudadItems2;
	}

	public void setCiudadItems2(List<SelectItem> ciudadItems2) {
		this.ciudadItems2 = ciudadItems2;
	}

	public List<SelectItem> getCiudadItems3() {
		return ciudadItems3;
	}

	public void setCiudadItems3(List<SelectItem> ciudadItems3) {
		this.ciudadItems3 = ciudadItems3;
	}

	public void cargarCiudadesDepartamentoNacimiento() {

		if (departamentoNacimientoId != null) {
			// listingCiudad = true;
			ciudadItems2 = new ArrayList<SelectItem>();
			List<Ciudad> ciudadList = talentoServicio.listaCiudadPorDepartamentoId(departamentoNacimientoId);

			for (Ciudad ciudad : ciudadList) {
				ciudadItems2.add(new SelectItem(ciudad.getCiudadId(), ciudad.getNombre()));

			}
		}

	}

	public void cargarCiudadesDepartamentoIdentificacion() {

		if (departamentoIdentificacionId != null) {
			// listingCiudad = true;
			ciudadItems1 = new ArrayList<SelectItem>();
			List<Ciudad> ciudadList = talentoServicio.listaCiudadPorDepartamentoId(departamentoIdentificacionId);

			for (Ciudad ciudad : ciudadList) {
				ciudadItems1.add(new SelectItem(ciudad.getCiudadId(), ciudad.getNombre()));

			}
		}

	}

	public void cargarCiudadesDepartamentoResidencia() {

		if (departamentoResidenciaId != null) {
			// listingCiudad = true;
			ciudadItems3 = new ArrayList<SelectItem>();
			List<Ciudad> ciudadList = talentoServicio.listaCiudadPorDepartamentoId(departamentoResidenciaId);

			for (Ciudad ciudad : ciudadList) {
				ciudadItems3.add(new SelectItem(ciudad.getCiudadId(), ciudad.getNombre()));

			}
		}

	}

	public void calcularEdad() {

		Calendar inicio = new GregorianCalendar();
		Calendar fin = new GregorianCalendar();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		try {

			Date FechaEdad = new Date();
			Date fechaNacimiento = new Date();

			fechaNacimiento = itemSeleccionado.getFechaNacimiento();

			inicio.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(fechaNacimiento)));
			fin.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(formatoFecha.format(FechaEdad)));
			Integer difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
			Integer difM = fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);

			// Integer diasMesa = fin.get
			Integer diffDay = fin.get(Calendar.DAY_OF_MONTH) - inicio.get(Calendar.DAY_OF_MONTH);
			// Si est� en ese a�o pero todav�a no los ha cumplido
			if (difM < 0 || (difM == 0 && diffDay < 0)) {
				if (difM < 0) {
					difM = (difM * -1);
				}

				if (diffDay < 0) {
					difM = difM - 1;
					diffDay = diffDay * -1;

				}

				difA = difA - 1;
			}

			itemSeleccionado.setEdad(difA);

		} catch (Exception e) {

			itemSeleccionado.setEdad(0);
		}

	}

	public List<EmpleadoFormacion> getListaFormacionTotal() {

		if (!listingFormacionTotal) {
			listingFormacionTotal = true;
			listaFormacionTotal = servicioFormacion.consultarTodos();
		}

		return listaFormacionTotal;
	}

	public void setListaFormacionTotal(List<EmpleadoFormacion> listaFormacionTotal) {
		this.listaFormacionTotal = listaFormacionTotal;
	}

	public List<EmpleadoCompetencia> getListaCompetenciaTotal() {

		if (!listingCompetenciaTotal) {
			listingCompetenciaTotal = true;
			listaCompetenciaTotal = servicioCompetencia.consultarTodos();
		}

		return listaCompetenciaTotal;
	}

	public void setListaCompetenciaTotal(List<EmpleadoCompetencia> listaCompetenciaTotal) {
		this.listaCompetenciaTotal = listaCompetenciaTotal;
	}

	public List<SelectItem> getCiudadItems() {
		return ciudadItems;
	}

	public void setCiudadItems(List<SelectItem> ciudadItems) {
		this.ciudadItems = ciudadItems;
	}

	public void cargarCiudadesDepartamento() {

		if (departamentoId != null) {
			// listingCiudad = true;
			ciudadItems = new ArrayList<SelectItem>();
			List<Ciudad> ciudadList = talentoServicio.listaCiudadPorDepartamentoId(departamentoId);

			for (Ciudad ciudad : ciudadList) {
				ciudadItems.add(new SelectItem(ciudad.getCiudadId(), ciudad.getNombre()));

			}
		}

	}

	public Long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
	}

	public Long getSedeid() {
		return sedeid;
	}

	public void setSedeid(Long sedeid) {
		this.sedeid = sedeid;
	}

	public List<SelectItem> getSedeItems() {

		if (!listingSede) {
			listingSede = true;

			sedeItems = new ArrayList<SelectItem>();
			List<Sede> sedeList = talentoServicio.consultarSedes();

			for (Sede sede : sedeList) {
				sedeItems.add(new SelectItem(sede.getSedeid(), sede.getSedenombre()));
			}
		}

		return sedeItems;
	}

	public void setSedeItems(List<SelectItem> sedeItems) {
		this.sedeItems = sedeItems;
	}

	public Long getTipoidentificacionid() {
		return tipoidentificacionid;
	}

	public void setTipoidentificacionid(Long tipoidentificacionid) {
		this.tipoidentificacionid = tipoidentificacionid;
	}

	public List<SelectItem> getTipoIdentificacionItems() {

		if (!listingTipoIdentificacion) {
			listingTipoIdentificacion = true;

			tipoIdentificacionItems = new ArrayList<SelectItem>();
			List<TipoIdentificacion> tipoIdList = talentoServicio.consultarTiposIdentificacion();

			for (TipoIdentificacion tipoid : tipoIdList) {
				tipoIdentificacionItems
						.add(new SelectItem(tipoid.getTipoidentificacionid(), tipoid.getTipoidentificacionnombre()));

			}
		}

		return tipoIdentificacionItems;
	}

	public void setTipoIdentificacionItems(List<SelectItem> tipoIdentificacionItems) {
		this.tipoIdentificacionItems = tipoIdentificacionItems;
	}

	public Long getCargoid() {
		return cargoid;
	}

	public void setCargoid(Long cargoid) {
		this.cargoid = cargoid;
	}

	public List<SelectItem> getCargoItems() {
		if (!listingCargo) {
			listingCargo = true;

			cargoItems = new ArrayList<SelectItem>();
			List<CompaniaCargo> cargoList = talentoServicio.consultarCargos();

			for (CompaniaCargo cargo : cargoList) {
				cargoItems.add(new SelectItem(cargo.getCompaniacargoid(), cargo.getCompaniacargonombre()));

			}
		}
		return cargoItems;
	}

	public void setCargoItems(List<SelectItem> cargoItems) {
		this.cargoItems = cargoItems;
	}
	
	
	public Long getTipoContratoid() {
		return tipoContratoid;
	}

	public void setTipoContratoid(Long tipoContratoid) {
		this.tipoContratoid = tipoContratoid;
	}

	public List<SelectItem> getTipoContratoItems() {
		if (!listingContrato) {

			listingContrato = true;
			tipoContratoItems = new ArrayList<SelectItem>();
			List<TipoContrato> tipoContratoList = servicioContrato.consultarTipocontratos();

			for (TipoContrato tipoContrato : tipoContratoList) {
				tipoContratoItems
						.add(new SelectItem(tipoContrato.getTipocontratoid(), tipoContrato.getTipocontratonombre()));

			}
		}

		return tipoContratoItems;
	}

	public void setTipoContratoItems(List<SelectItem> tipoContratoItems) {
		this.tipoContratoItems = tipoContratoItems;
	}
	
	public List<EmpleadoContratoDetalle> getListaDetalleContratoItems() {
		if (!listingContratoDetalle) {
			listingContratoDetalle = true;
			listaDetalleContratoItems = servicioContrato
					.consultarDetalleContrato(itemContrato.getEmpleadocontratoid());
		
		}
		return listaDetalleContratoItems;
	}

	public void setListaDetalleContratoItems(List<EmpleadoContratoDetalle> listaDetalleContratoItems) {
		this.listaDetalleContratoItems = listaDetalleContratoItems;
	}	
	
	

}
