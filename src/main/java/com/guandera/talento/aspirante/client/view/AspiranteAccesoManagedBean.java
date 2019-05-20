package com.guandera.talento.aspirante.client.view;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.Ciudad;
import com.guandera.core.shared.model.Departamento;
import com.guandera.talento.aspirante.client.service.AspiranteCompetenciaService;
import com.guandera.talento.aspirante.client.service.AspiranteExperienciaService;
import com.guandera.talento.aspirante.client.service.AspiranteFormacionService;
import com.guandera.talento.aspirante.client.service.AspiranteIdiomaService;
import com.guandera.talento.aspirante.client.service.AspiranteObservacionService;
import com.guandera.talento.aspirante.client.service.AspiranteReferenciaService;
import com.guandera.talento.aspirante.client.service.AspiranteService;
import com.guandera.talento.aspirante.server.service.AspiranteCompetenciaServiceImpl;
import com.guandera.talento.aspirante.server.service.AspiranteExperienciaServiceImpl;
import com.guandera.talento.aspirante.server.service.AspiranteFormacionServiceImpl;
import com.guandera.talento.aspirante.server.service.AspiranteIdiomaServiceImpl;
import com.guandera.talento.aspirante.server.service.AspiranteObservacionServiceImpl;
import com.guandera.talento.aspirante.server.service.AspiranteReferenciaServiceImpl;
import com.guandera.talento.aspirante.server.service.AspiranteServiceImpl;
import com.guandera.talento.aspirante.shared.model.Aspirante;
import com.guandera.talento.aspirante.shared.model.AspiranteAcceso;
import com.guandera.talento.aspirante.shared.model.AspiranteAutorizacion;
import com.guandera.talento.aspirante.shared.model.AspiranteCompetencia;
import com.guandera.talento.aspirante.shared.model.AspiranteEstado;
import com.guandera.talento.aspirante.shared.model.AspiranteExperiencia;
import com.guandera.talento.aspirante.shared.model.AspiranteFormacion;
import com.guandera.talento.aspirante.shared.model.AspiranteIdioma;
import com.guandera.talento.aspirante.shared.model.AspiranteObservacion;
import com.guandera.talento.aspirante.shared.model.AspiranteReferencia;
import com.guandera.talento.client.service.TalentoService;
import com.guandera.talento.server.service.TalentoServiceImpl;
import com.guandera.talento.shared.model.ReferenciaTipo;
import com.guandera.talento.shared.model.Competencia;
import com.guandera.talento.shared.model.EstadoCivil;
import com.guandera.talento.shared.model.FormacionEstado;
import com.guandera.talento.shared.model.Idioma;
import com.guandera.talento.shared.model.Institucion;
import com.guandera.talento.shared.model.ProgramaAcademico;

@ManagedBean(name = "AspiranteAccesoMB")
@SessionScoped
public class AspiranteAccesoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private List<SelectItem> aspiranteEstadoItems;
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
	private AspiranteCompetencia itemCompetencia;

	private AspiranteExperiencia itemExperiencia;
	private AspiranteFormacion itemFormacion;
	private AspiranteIdioma itemIdioma;

	private AspiranteObservacion itemObservacion;
	private AspiranteReferencia itemReferencia;
	private Aspirante itemSeleccionado;

	private List<AspiranteCompetencia> listaCompetencia;
	private List<AspiranteExperiencia> listaExperiencia;

	private List<AspiranteFormacion> listaFormacion;

	private List<AspiranteIdioma> listaIdioma;

	private List<Aspirante> listaItems;

	private List<AspiranteObservacion> listaObservacion;

	private List<AspiranteReferencia> listaReferencia;

	private boolean listingAspirante = false;

	private boolean listingAspiranteCompetencia = false;

	private boolean listingAspiranteEstado = false;

	private boolean listingAspiranteExperiencia = false;

	private boolean listingAspiranteFormacion = false;

	private boolean listingAspiranteIdioma = false;

	private boolean listingAspiranteObservacion = false;

	private boolean listingAspiranteReferencia = false;

	private boolean listingCiudad = false;

	private boolean listingEstadoCivil = false;

	private Long lugarNacimientoId;

	private AspiranteService servicio;

	private AspiranteCompetenciaService servicioCompetencia;

	private AspiranteExperienciaService servicioExperiencia;

	private AspiranteFormacionService servicioFormacion;

	private AspiranteIdiomaService servicioIdioma;

	private AspiranteObservacionService servicioObservacion;

	private AspiranteReferenciaService servicioReferencia;

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

	private List<AspiranteFormacion> listaFormacionTotal;
	private List<AspiranteCompetencia> listaCompetenciaTotal;

	private boolean listingFormacionTotal = false;
	private boolean listingCompetenciaTotal = false;

	private Long departamentoId;

	private String nombres;
	private String apellidos;
	private String correo;
	private String clave1;
	private String clave2;

	private boolean aceptarPolitica = false;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave1() {
		return clave1;
	}

	public void setClave1(String clave1) {
		this.clave1 = clave1;
	}

	public String getClave2() {
		return clave2;
	}

	public void setClave2(String clave2) {
		this.clave2 = clave2;
	}

	public AspiranteAccesoManagedBean() {

		servicio = new AspiranteServiceImpl();
		talentoServicio = new TalentoServiceImpl();

		servicioCompetencia = new AspiranteCompetenciaServiceImpl();
		servicioExperiencia = new AspiranteExperienciaServiceImpl();
		servicioFormacion = new AspiranteFormacionServiceImpl();
		servicioIdioma = new AspiranteIdiomaServiceImpl();
		servicioObservacion = new AspiranteObservacionServiceImpl();
		servicioReferencia = new AspiranteReferenciaServiceImpl();

	}

	public String actualizar() {
		try {

			Ciudad lugarNacimiento = new Ciudad();

			EstadoCivil estadoCivil = new EstadoCivil();

			AspiranteEstado estado = new AspiranteEstado();

			Ciudad ciudadResidencia = new Ciudad();

			Ciudad ciudadIdentificacion = new Ciudad();

			lugarNacimiento.setCiudadId(lugarNacimientoId);
			estadoCivil.setEstadoCivilId(estadoCivilId);

			// estado.setEstadoId(estadoId);

			ciudadResidencia.setCiudadId(ciudadResidenciaId);
			ciudadIdentificacion.setCiudadId(ciudadIdentificacionId);

			itemSeleccionado.setEstadoCivil(estadoCivil);
			// itemSeleccionado.setEstado(estado);
			itemSeleccionado.setLugarNacimiento(lugarNacimiento);

			itemSeleccionado.setCiudadResidencia(ciudadResidencia);
			itemSeleccionado.setCiudadIdentificacion(ciudadIdentificacion);

			// itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("AspiranteUpdated");
			listingAspirante = false;
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
			mensajeInfo("AspiranteCompetenciaUpdated");
			listingAspiranteCompetencia = false;
			return prepararConsulta();
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
			mensajeInfo("AspiranteExperienciaUpdated");
			listingAspiranteExperiencia = false;
			return prepararConsulta();
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
			mensajeInfo("AspiranteFormacionUpdated");
			listingAspiranteFormacion = false;
			return prepararConsulta();
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
			mensajeInfo("AspiranteIdiomaUpdated");
			listingAspiranteIdioma = false;
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
			mensajeInfo("AspiranteObservacionUpdated");
			listingAspiranteObservacion = false;
			return prepararConsulta();
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
			mensajeInfo("AspiranteReferenciaUpdated");
			listingAspiranteReferencia = false;
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearCuenta() {

		if (servicio.existeUsuario(correo)) {

			error("El correo electrónico ya se encuntra registrado en nuestro sistema");
			return null;
		}

		if (!clave1.equals(clave2)) {
			error("Su contraseña debe coincidir");
			setClave1("");
			return null;
		}

		if (!aceptarPolitica) {
			error("Debe Aceptar las Condiciones del Sitio, para poder crear su cuenta");
			return null;
		}

		try {

			Aspirante aspirante = new Aspirante();
			AspiranteAcceso aspiranteAcceso = new AspiranteAcceso();

			aspirante.setNombres(nombres);
			aspirante.setApellidos(apellidos);
			aspirante.setCorreo(correo);
			aspirante.setFechaCreacion(new Date());

			aspiranteAcceso.setCorreo(correo);
			aspiranteAcceso.setFechaCreacion(new Date());

			String md5 = md5Encode("X7B*" + clave1);

			aspiranteAcceso.setClave(md5);

			servicio.crearCuentaAspirante(aspirante, aspiranteAcceso);
			mensajeInfo("AspiranteCreated");

			return "login";

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {

			Ciudad lugarNacimiento = new Ciudad();

			EstadoCivil estadoCivil = new EstadoCivil();

			AspiranteEstado estado = new AspiranteEstado();

			Ciudad ciudadResidencia = new Ciudad();

			Ciudad ciudadIdentificacion = new Ciudad();

			lugarNacimiento.setCiudadId(lugarNacimientoId);
			estadoCivil.setEstadoCivilId(estadoCivilId);
			estado.setEstadoId(estadoId);
			ciudadResidencia.setCiudadId(ciudadResidenciaId);
			ciudadIdentificacion.setCiudadId(ciudadIdentificacionId);

			itemSeleccionado.setEstadoCivil(estadoCivil);
			itemSeleccionado.setEstado(estado);
			itemSeleccionado.setLugarNacimiento(lugarNacimiento);

			itemSeleccionado.setCiudadResidencia(ciudadResidencia);
			itemSeleccionado.setCiudadIdentificacion(ciudadIdentificacion);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.crear(itemSeleccionado);
			mensajeInfo("AspiranteCreated");
			listingAspirante = false;
			return prepararConsulta();
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

			itemCompetencia.setAspirante(itemSeleccionado);
			//itemCompetencia.setUsuarioCreacion(usuario());
			itemCompetencia.setFechaCreacion(new Date());
			//itemCompetencia.setUsuarioModificacion(usuario());
			itemCompetencia.setFechaModificacion(new Date());

			servicioCompetencia.crear(itemCompetencia);
			mensajeInfo("AspiranteCompetenciaCreated");
			listingAspiranteCompetencia = false;
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearExperiencia() {

		try {

			itemExperiencia.setAspirante(itemSeleccionado);
			itemExperiencia.setUsuarioCreacion(usuario());
			itemExperiencia.setFechaCreacion(new Date());
			itemExperiencia.setUsuarioModificacion(usuario());
			itemExperiencia.setFechaModificacion(new Date());

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemExperiencia.setCiudad(ciudad);

			servicioExperiencia.crear(itemExperiencia);
			mensajeInfo("AspiranteExperienciaCreated");
			listingAspiranteExperiencia = false;
			return prepararConsulta();
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

			itemFormacion.setAspirante(itemSeleccionado);

			// itemFormacion.setUsuarioCreacion(usuario());
			itemFormacion.setFechaCreacion(new Date());
			// itemFormacion.setUsuarioModificacion(usuario());
			itemFormacion.setFechaModificacion(new Date());

			servicioFormacion.crear(itemFormacion);
			mensajeInfo("AspiranteFormacionCreated");
			listingAspiranteFormacion = false;
			return prepararConsulta();
			
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearIdioma() {

		try {
			itemIdioma.setAspirante(itemSeleccionado);
			// itemIdioma.setUsuarioCreacion(usuario());
			itemIdioma.setFechaCreacion(new Date());
			// itemIdioma.setUsuarioModificacion(usuario());
			itemIdioma.setFechaModificacion(new Date());

			Idioma idioma = new Idioma();
			idioma.setIdiomaId(idiomaId);
			itemIdioma.setIdioma(idioma);

			servicioIdioma.crear(itemIdioma);
			mensajeInfo("AspiranteIdiomaCreated");
			listingAspiranteIdioma = false;
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearObservacion() {

		try {
			itemObservacion.setAspirante(itemSeleccionado);
			itemObservacion.setUsuarioCreacion(usuario());
			itemObservacion.setFechaCreacion(new Date());
			itemObservacion.setUsuarioModificacion(usuario());
			itemObservacion.setFechaModificacion(new Date());

			servicioObservacion.crear(itemObservacion);
			mensajeInfo("AspiranteObservacionCreated");
			listingAspiranteObservacion = false;
			return prepararCreacionObservacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crearReferencia() {

		try {
			itemReferencia.setAspirante(itemSeleccionado);
			// itemReferencia.setUsuarioCreacion(usuario());
			itemReferencia.setFechaCreacion(new Date());
			// itemReferencia.setUsuarioModificacion(usuario());
			itemReferencia.setFechaModificacion(new Date());

			ReferenciaTipo referenciaTipo = new ReferenciaTipo();
			referenciaTipo.setReferenciaTipoId(referenciaTipoId);
			itemReferencia.setTipo(referenciaTipo);

			Ciudad ciudad = new Ciudad();
			ciudad.setCiudadId(ciudadId);
			itemReferencia.setCiudad(ciudad);

			servicioReferencia.crear(itemReferencia);
			mensajeInfo("AspiranteReferenciaCreated");
			listingAspiranteReferencia = false;
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingAspirante = false;
		return prepararConsulta();
	}

	public String eliminarCompetencia() {
		servicioCompetencia.eliminar(itemCompetencia);
		listingAspiranteCompetencia = false;
		return prepararConsulta();
	}

	public String eliminarExperiencia() {
		servicioExperiencia.eliminar(itemExperiencia);
		listingAspiranteExperiencia = false;
		return prepararConsulta();
	}

	public String eliminarFormacion() {
		servicioFormacion.eliminar(itemFormacion);
		listingAspiranteFormacion = false;
		return prepararConsulta();
	}

	public String eliminarIdioma() {
		servicioIdioma.eliminar(itemIdioma);
		listingAspiranteIdioma = false;
		return prepararConsulta();
	}

	public String eliminarObservacion() {
		servicioObservacion.eliminar(itemObservacion);
		listingAspiranteObservacion = false;
		return prepararConsulta();
	}

	public String eliminarReferencia() {
		servicioReferencia.eliminar(itemReferencia);
		listingAspiranteReferencia = false;
		return prepararConsulta();
	}

	public List<SelectItem> getAspiranteEstadoItems() {

		if (!listingAspiranteEstado) {
			listingAspiranteEstado = true;
			aspiranteEstadoItems = new ArrayList<SelectItem>();
			List<AspiranteEstado> aspiranteEstadoList = talentoServicio.listaAspiranteEstado();

			for (AspiranteEstado aspiranteEstado : aspiranteEstadoList) {
				aspiranteEstadoItems.add(new SelectItem(aspiranteEstado.getEstadoId(), aspiranteEstado.getNombre()));

			}
		}

		return aspiranteEstadoItems;
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

	public AspiranteCompetencia getItemCompetencia() {
		return itemCompetencia;
	}

	public AspiranteExperiencia getItemExperiencia() {

		return itemExperiencia;
	}

	public AspiranteFormacion getItemFormacion() {
		return itemFormacion;
	}

	public AspiranteIdioma getItemIdioma() {
		return itemIdioma;
	}

	public AspiranteObservacion getItemObservacion() {
		return itemObservacion;
	}

	public AspiranteReferencia getItemReferencia() {
		return itemReferencia;
	}

	public Aspirante getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<AspiranteCompetencia> getListaCompetencia() {

		if (!listingAspiranteCompetencia) {
			listingAspiranteCompetencia = true;
			listaCompetencia = servicioCompetencia.consultarCompetenciasAspirante(itemSeleccionado);
		}
		return listaCompetencia;
	}

	public List<AspiranteExperiencia> getListaExperiencia() {

		if (!listingAspiranteExperiencia) {
			listingAspiranteExperiencia = true;
			listaExperiencia = servicioExperiencia.consultarExperienciaAspirante(itemSeleccionado);
		}
		return listaExperiencia;
	}

	public List<AspiranteFormacion> getListaFormacion() {

		if (!listingAspiranteFormacion) {
			listingAspiranteFormacion = true;
			listaFormacion = servicioFormacion.consultarFormacionAspirante(itemSeleccionado);
		}
		return listaFormacion;
	}

	public List<AspiranteIdioma> getListaIdioma() {

		if (!listingAspiranteIdioma) {
			listingAspiranteIdioma = true;
			listaIdioma = servicioIdioma.consultarIdiomasAspirante(itemSeleccionado);
		}
		return listaIdioma;
	}

	public List<Aspirante> getListaItems() {

		if (!listingAspirante) {
			listingAspirante = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public List<AspiranteObservacion> getListaObservacion() {

		if (!listingAspiranteObservacion) {
			listingAspiranteObservacion = true;
			listaObservacion = servicioObservacion.consultarObservacionesAspirante(itemSeleccionado);
		}
		return listaObservacion;
	}

	public List<AspiranteReferencia> getListaReferencia() {

		if (!listingAspiranteReferencia) {
			listingAspiranteReferencia = true;
			listaReferencia = servicioReferencia.consultarReferenciasAspirante(itemSeleccionado);
		}
		return listaReferencia;
	}

	public Long getLugarNacimientoId() {
		return lugarNacimientoId;
	}

	public String prepararConsulta() {

		listingAspiranteCompetencia = false;
		listingAspiranteExperiencia = false;
		listingAspiranteFormacion = false;
		listingAspiranteIdioma = false;
		listingAspiranteObservacion = false;
		listingAspiranteReferencia = false;

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
		itemSeleccionado = new Aspirante();

		Integer siguienteCodigo = servicio.siguienteCodigoAspirante();
		itemSeleccionado.setCodigo(siguienteCodigo);

		lugarNacimientoId = 0l;
		estadoCivilId = 0l;
		estadoId = 0l;
		ciudadResidenciaId = 0l;
		ciudadIdentificacionId = 0l;

		departamentoIdentificacionId = 0l;
		departamentoNacimientoId = 0l;
		departamentoResidenciaId = 0l;

		return "Crear";
	}

	public String prepararCreacionCompetencia() {
		competenciaId = 0l;

		itemCompetencia = new AspiranteCompetencia();
		return "CrearCompetencia";
	}

	public String prepararCreacionExperiencia() {
		itemExperiencia = new AspiranteExperiencia();
		ciudadId = 0l;
		return "CrearExperiencia";
	}

	public String prepararCreacionFormacion() {
		formacionEstadoId = 0l;
		programaAcademicoId = 0l;
		institucionId = 0l;

		itemFormacion = new AspiranteFormacion();
		return "CrearFormacion";
	}

	public String prepararCreacionIdioma() {
		idiomaId = 0l;
		itemIdioma = new AspiranteIdioma();
		return "CrearIdioma";
	}

	public String prepararCreacionObservacion() {
		itemObservacion = new AspiranteObservacion();
		return "CrearObservacion";
	}

	public String prepararCreacionReferencia() {
		itemReferencia = new AspiranteReferencia();
		referenciaTipoId = 0l;
		ciudadId = 0l;
		return "CrearReferencia";
	}

	public String prepararEdicion() {

		try {
			lugarNacimientoId = itemSeleccionado.getLugarNacimiento().getCiudadId();
			estadoCivilId = itemSeleccionado.getEstadoCivil().getEstadoCivilId();
			estadoId = itemSeleccionado.getEstado().getEstadoId();
			ciudadResidenciaId = itemSeleccionado.getCiudadResidencia().getCiudadId();
			ciudadIdentificacionId = itemSeleccionado.getCiudadIdentificacion().getCiudadId();

			departamentoNacimientoId = itemSeleccionado.getLugarNacimiento().getDepartamento().getDepartamentoId();
			departamentoResidenciaId = itemSeleccionado.getCiudadResidencia().getDepartamento().getDepartamentoId();

			departamentoIdentificacionId = itemSeleccionado.getCiudadIdentificacion().getDepartamento()
					.getDepartamentoId();

		} catch (Exception e) {
			lugarNacimientoId = 0l;
			estadoCivilId = 0l;
			estadoId = 0l;
			ciudadResidenciaId = 0l;
			ciudadIdentificacionId = 0l;

			departamentoIdentificacionId = 0l;
			departamentoNacimientoId = 0l;
			departamentoResidenciaId = 0l;
		}

		return "Editar";
	}

	public String cargarAspirante() {

		itemSeleccionado = cargarAspiranteSesion();

		try {
			lugarNacimientoId = itemSeleccionado.getLugarNacimiento().getCiudadId();
			estadoCivilId = itemSeleccionado.getEstadoCivil().getEstadoCivilId();
			estadoId = itemSeleccionado.getEstado().getEstadoId();
			ciudadResidenciaId = itemSeleccionado.getCiudadResidencia().getCiudadId();
			ciudadIdentificacionId = itemSeleccionado.getCiudadIdentificacion().getCiudadId();

			departamentoNacimientoId = itemSeleccionado.getLugarNacimiento().getDepartamento().getDepartamentoId();
			departamentoResidenciaId = itemSeleccionado.getCiudadResidencia().getDepartamento().getDepartamentoId();

			departamentoIdentificacionId = itemSeleccionado.getCiudadIdentificacion().getDepartamento()
					.getDepartamentoId();
		} catch (Exception e) {
			lugarNacimientoId = 0l;
			estadoCivilId = 0l;
			estadoId = 0l;
			ciudadResidenciaId = 0l;
			ciudadIdentificacionId = 0l;

			departamentoIdentificacionId = 0l;
			departamentoNacimientoId = 0l;
			departamentoResidenciaId = 0l;
		}

		return "Detalle";

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

	public String prepararEdicionIdioma() {

		idiomaId = itemIdioma.getIdioma().getIdiomaId();
		return "EditarIdioma";
	}

	public String prepararEdicionObservacion() {
		return "EditarObservcion";
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

		listingAspirante = false;
		return "Lista";
	}

	public void setAspiranteEstadoItems(List<SelectItem> aspiranteEstadoItems) {
		this.aspiranteEstadoItems = aspiranteEstadoItems;
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

	public void setItemCompetencia(AspiranteCompetencia itemCompetencia) {
		this.itemCompetencia = itemCompetencia;
	}

	public void setItemExperiencia(AspiranteExperiencia itemExperiencia) {
		this.itemExperiencia = itemExperiencia;
	}

	public void setItemFormacion(AspiranteFormacion itemFormacion) {
		this.itemFormacion = itemFormacion;
	}

	public void setItemIdioma(AspiranteIdioma itemIdioma) {
		this.itemIdioma = itemIdioma;
	}

	public void setItemObservacion(AspiranteObservacion itemObservacion) {
		this.itemObservacion = itemObservacion;
	}

	public void setItemReferencia(AspiranteReferencia itemReferencia) {
		this.itemReferencia = itemReferencia;
	}

	public void setItemSeleccionado(Aspirante itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaCompetencia(List<AspiranteCompetencia> listaCompetencia) {
		this.listaCompetencia = listaCompetencia;
	}

	public void setListaExperiencia(List<AspiranteExperiencia> listaExperiencia) {
		this.listaExperiencia = listaExperiencia;
	}

	public void setListaFormacion(List<AspiranteFormacion> listaFormacion) {
		this.listaFormacion = listaFormacion;
	}

	public void setListaIdioma(List<AspiranteIdioma> listaIdioma) {
		this.listaIdioma = listaIdioma;
	}

	public void setListaItems(List<Aspirante> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListaObservacion(List<AspiranteObservacion> listaObservacion) {
		this.listaObservacion = listaObservacion;
	}

	public void setListaReferencia(List<AspiranteReferencia> listaReferencia) {
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
			// Si estï¿½ en ese aï¿½o pero todavï¿½a no los ha cumplido
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

	public List<AspiranteFormacion> getListaFormacionTotal() {

		if (!listingFormacionTotal) {
			listingFormacionTotal = true;
			listaFormacionTotal = servicioFormacion.consultarTodos();
		}

		return listaFormacionTotal;
	}

	public void setListaFormacionTotal(List<AspiranteFormacion> listaFormacionTotal) {
		this.listaFormacionTotal = listaFormacionTotal;
	}

	public List<AspiranteCompetencia> getListaCompetenciaTotal() {

		if (!listingCompetenciaTotal) {
			listingCompetenciaTotal = true;
			listaCompetenciaTotal = servicioCompetencia.consultarTodos();
		}

		return listaCompetenciaTotal;
	}

	public void setListaCompetenciaTotal(List<AspiranteCompetencia> listaCompetenciaTotal) {
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

	public boolean isAceptarPolitica() {
		return aceptarPolitica;
	}

	public void setAceptarPolitica(boolean aceptarPolitica) {
		this.aceptarPolitica = aceptarPolitica;
	}

	public static String md5Encode(String texto) {

		String output = "";
		try {
			byte[] defaultBytes = texto.getBytes();
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}

			output = hexString + "";

		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {

		}
		return output;
	}

	public Aspirante cargarAspiranteSesion() {

		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

		LoginAspiranteManagedBean loginAspiranteMB = (LoginAspiranteManagedBean) session
				.getAttribute("LoginAspiranteMB");

		return loginAspiranteMB.getAspirante();

	}

}
