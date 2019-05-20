package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.proyecto.client.service.ActividadService;
import com.guandera.proyecto.server.service.ActividadServiceImpl;
import com.guandera.proyecto.shared.model.Actividad;
import com.guandera.proyecto.shared.model.ActividadTipo;
import com.guandera.proyecto.shared.model.Asignacion;
import com.guandera.proyecto.shared.model.Tiempo;

@ManagedBean(name = "actividadMB")
@SessionScoped
public class ActividadManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ActividadService actividadServicio;
	private Actividad itemSeleccionado;
	private List<Actividad> listaItems = new ArrayList<Actividad>();
	private List<Actividad> listaActividadesFinalizadas = new ArrayList<Actividad>();
	private boolean listing = false;

	private List<Asignacion> listaAsignacion = new ArrayList<Asignacion>();
	private Asignacion asignacionSeleccionada;

	private Long actividadTipoid;
	private List<SelectItem> actividadTipoItems;
	private boolean listingActividadTipo = false;

	private boolean listarActividades = false;

	private Long tiempoid;

	private List<SelectItem> tiempoItems;
	private boolean listingTiempo = false;

	// Actividades por usuario

	private List<Actividad> listaactividadesUsuarioItems = new ArrayList<Actividad>();
	private List<Actividad> listaActividadesFinalizadasUsuario = new ArrayList<Actividad>();
	private List<Asignacion> listaAsignacionUsuario = new ArrayList<Asignacion>();

	private Integer periodo;
	
	private Long usuarioid;
	private List<SelectItem> usuarioItems;
	private boolean listingUsuario = false;
	
	
	

	public ActividadManagedBean() {
		actividadServicio = new ActividadServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			
			ActividadTipo actividadTipo = new ActividadTipo();

			
			actividadTipo.setTipoid(actividadTipoid);
			
			
			Integer perido = obtenerPeriodo(itemSeleccionado.getFecha());
			Integer year = obtenerYear(itemSeleccionado.getFecha());
			Integer mes  = obtenerMes(itemSeleccionado.getFecha());
			
			itemSeleccionado.setYear(year);
			itemSeleccionado.setMes(mes);
			itemSeleccionado.setPeriodo(perido);
			
			
			
		
			itemSeleccionado.setTipo(actividadTipo);
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			itemSeleccionado.setActividadFechaModificacion(new Date());
			actividadServicio.actualizar(itemSeleccionado);
			mensajeInfo("ActividadUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {
			Tiempo tiempo = new Tiempo();
			ActividadTipo actividadTipo = new ActividadTipo();

			tiempoid = (long) 1; // Horas

			tiempo.setTiempoid(tiempoid);
			actividadTipo.setTipoid(actividadTipoid);
			itemSeleccionado.setTiempo(tiempo);
			itemSeleccionado.setTipo(actividadTipo);

			itemSeleccionado.setAsignacion(asignacionSeleccionada);
			itemSeleccionado.setUsuario(asignacionSeleccionada.getUsuario());
			
			Integer perido = obtenerPeriodo(itemSeleccionado.getFecha());
			Integer year = obtenerYear(itemSeleccionado.getFecha());
			Integer mes  = obtenerMes(itemSeleccionado.getFecha());
			
			itemSeleccionado.setYear(year);
			itemSeleccionado.setMes(mes);
			itemSeleccionado.setPeriodo(perido); 
			
			itemSeleccionado.setActividadNumero(actividadServicio.siguienteActividad());

			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setActividadFechaModificacion(new Date());
			itemSeleccionado.setActividadFechaCreacion(new Date());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			actividadServicio.crear(itemSeleccionado);
			mensajeInfo("ActividadCreated");

			return "Asignacion";

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	private Integer obtenerMes(Date fecha) {
		String formato = "MM";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return Integer.parseInt(dateFormat.format(fecha));
	}

	private Integer obtenerYear(Date fecha) {
		String formato = "yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return Integer.parseInt(dateFormat.format(fecha));
	}

	private Integer obtenerPeriodo(Date fecha) {
		
		String formato = "yyyyMM";
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return Integer.parseInt(dateFormat.format(fecha));
	
	}

	public String eliminar() {

		actividadServicio.eliminar(itemSeleccionado);
		mensajeInfo("ActividadDelete");
		listing = false;
		return prepararLista();
	}

	public String finalizarActividad() {

		actividadServicio.finalizar(itemSeleccionado);
		mensaje("Actividad Finalizada Exitosamente.");
		listing = false;
		getListaAsignacionUsuario();
		return "Asignacion";
	}

	public String finalizarAsignacion() {

		actividadServicio.finalizarAsignacion(asignacionSeleccionada);
		mensaje("Tarea Finalizada Exitosamente.");
		listing = false;
		getListaAsignacionUsuario();
		return "Asignacion";
	}

	public Actividad getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Actividad> getListaItems() {
	
		return listaItems;
	}

	public ActividadService getActividadServicio() {
		return actividadServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Actividad();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarActividad() {
		setListing(true);
		listaItems = actividadServicio.consultarListaActividades();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Actividad();
		itemSeleccionado.setFecha(new Date());
		return "Crear";
	}

	public String prepararEdicion() {
		actividadTipoid = itemSeleccionado.getTipo().getTipoid();
		
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	public String buscarAsignacion() {
		return "Asignacion";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(Actividad itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Actividad> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setActividadServicio(ActividadService actividadServicio) {
		this.actividadServicio = actividadServicio;
	}

	private void verificarLista() {
		if (listing) {
			listaItems = actividadServicio.consultarTodos(1, null);
		}
	}

	public List<SelectItem> getActividadTipoItems() {

		if (!listingActividadTipo) {
			listingActividadTipo = true;
			actividadTipoItems = new ArrayList<SelectItem>();
			List<ActividadTipo> actividadTipoList = actividadServicio.consultarTipos();

			for (ActividadTipo actividadTipo : actividadTipoList) {
				actividadTipoItems.add(new SelectItem(actividadTipo.getTipoid(), actividadTipo.getNombre()));

			}
		}

		return actividadTipoItems;
	}

	public void setActividadTipoItems(List<SelectItem> actividadTipoItems) {
		this.actividadTipoItems = actividadTipoItems;
	}

	public List<SelectItem> getTiempoItems() {

		if (!listingTiempo) {
			listingTiempo = true;
			tiempoItems = new ArrayList<SelectItem>();
			List<Tiempo> tiempoList = actividadServicio.consultarTiempos();

			for (Tiempo tiempo : tiempoList) {
				tiempoItems.add(new SelectItem(tiempo.getTiempoid(), tiempo.getNombre()));

			}
		}

		return tiempoItems;
	}

	public void setTiempoItems(List<SelectItem> tiempoItems) {
		this.tiempoItems = tiempoItems;
	}

	public Long getActividadTipoid() {
		return actividadTipoid;
	}

	public void setActividadTipoid(Long actividadTipoid) {
		this.actividadTipoid = actividadTipoid;
	}

	public Long getTiempoid() {
		return tiempoid;
	}

	public void setTiempoid(Long tiempoid) {
		this.tiempoid = tiempoid;
	}

	public List<Asignacion> getListaAsignacion() {
		listaAsignacion = actividadServicio.consultarAsignacion(1, null);
		return listaAsignacion;
	}

	public void setListaAsignacion(List<Asignacion> listaAsignacion) {
		this.listaAsignacion = listaAsignacion;
	}

	public Asignacion getAsignacionSeleccionada() {
		return asignacionSeleccionada;
	}

	public void setAsignacionSeleccionada(Asignacion asignacionSeleccionada) {
		this.asignacionSeleccionada = asignacionSeleccionada;
	}

	public List<Actividad> getListaActividadesFinalizadas() {
		listaActividadesFinalizadas = actividadServicio.consultarActividadesFinalizadas(1, null);
		return listaActividadesFinalizadas;
	}

	public void setListaActividadesFinalizadas(List<Actividad> listaActividadesFinalizadas) {
		this.listaActividadesFinalizadas = listaActividadesFinalizadas;
	}

	public String listaActividadesFinalizadas() {
		return ("FinalizadasLista");
	}

	public String consultarActividadesFinalizadas() {
		return ("FinalizadasDetalle");
	}
	// Consultar lista de asignaciones en estado 1

	public List<Actividad> buscarActividadesUsuario() {

		listaactividadesUsuarioItems = actividadServicio.consultarActividadesPorUsuario(usuario());

		listarActividades = true;

		return listaactividadesUsuarioItems;

	}

	
	public List<Actividad> listaActividadesUsuarioPeriodo() {

		listaItems = actividadServicio.consultarActividadesUsuarioPeriodo(usuario(), periodo);
		

		listarActividades = true;

		return listaItems;

	}
	
	
	public List<Actividad> consultarActividadesUsuarioPeriodo() {

		listaItems = actividadServicio.consultarActividadesUsuarioPeriodo(usuarioid, periodo);
		

		listarActividades = true;

		return listaItems;

	}
	
	
	public List<Actividad> getListaactividadesUsuarioItems() {
		listaactividadesUsuarioItems = actividadServicio.consultarTodos(2, usuario());
		return listaactividadesUsuarioItems;
	}

	public void setListaactividadesUsuarioItems(List<Actividad> listaactividadesUsuarioItems) {
		this.listaactividadesUsuarioItems = listaactividadesUsuarioItems;
	}

	public List<Actividad> getListaActividadesFinalizadasUsuario() {

		listaActividadesFinalizadasUsuario = actividadServicio.consultarActividadesFinalizadas(2, usuario());
		return listaActividadesFinalizadasUsuario;
	}

	public void setListaActividadesFinalizadasUsuario(List<Actividad> listaActividadesFinalizadasUsuario) {
		this.listaActividadesFinalizadasUsuario = listaActividadesFinalizadasUsuario;
	}

	public List<Asignacion> getListaAsignacionUsuario() {
		
		listaAsignacionUsuario = actividadServicio.consultarAsignacionPorUsuario(usuario());
		return listaAsignacionUsuario;
	}

	public void setListaAsignacionUsuario(List<Asignacion> listaAsignacionUsuario) {
		this.listaAsignacionUsuario = listaAsignacionUsuario;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public boolean isListarActividades() {
		return listarActividades;
	}

	public void setListarActividades(boolean listarActividades) {
		this.listarActividades = listarActividades;
	}

	
	public String refrescarLista() {

		listing = false;
		return "Lista";
	}

	public Long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public List<SelectItem> getUsuarioItems() {
		if (!listingUsuario) {
			listingUsuario = true;
			usuarioItems = new ArrayList<SelectItem>();
			List<Usuario> usuarioList = actividadServicio.consultarUsuarios();

			for (Usuario usuario : usuarioList) {
				usuarioItems.add(new SelectItem(usuario.getUsuarioid(),
						usuario.getUsuarionombre() + "  " + usuario.getUsuarioapellido()));

			}
		}
		
		
		return usuarioItems;
	}

	public void setUsuarioItems(List<SelectItem> usuarioItems) {
		this.usuarioItems = usuarioItems;
	}

	public boolean isListingUsuario() {
		return listingUsuario;
	}

	public void setListingUsuario(boolean listingUsuario) {
		this.listingUsuario = listingUsuario;
	}
	
	
	
}
