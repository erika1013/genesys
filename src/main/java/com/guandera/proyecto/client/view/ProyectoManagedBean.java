package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.ProyectoService;
import com.guandera.proyecto.server.service.ProyectoServiceImpl;
import com.guandera.proyecto.shared.model.Asignacion;
import com.guandera.proyecto.shared.model.AsignacionEstado;
import com.guandera.proyecto.shared.model.EquipoCliente;
import com.guandera.proyecto.shared.model.EquipoCompania;
import com.guandera.proyecto.shared.model.Proyecto;
import com.guandera.proyecto.shared.model.ProyectoEtapa;
import com.guandera.proyecto.shared.model.ProyectoEtapaCambio;
import com.guandera.proyecto.shared.model.ProyectoTipo;
import com.guandera.proyecto.shared.model.RequerimientoEstado;
import com.guandera.proyecto.shared.model.RequerimientoTipo;
import com.guandera.proyecto.shared.model.RolProyecto;
import com.guandera.proyecto.shared.model.Tarea;
import com.guandera.proyecto.shared.model.TareaEstado;
import com.guandera.proyecto.shared.model.Tiempo;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.core.shared.model.Cliente;
import com.guandera.core.shared.model.ClienteContacto;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.CentroCostos;
import com.guandera.core.shared.model.acceso.Usuario;
import com.guandera.proyecto.shared.model.Requerimiento;

@ManagedBean(name = "proyectoMB")
@SessionScoped
public class ProyectoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProyectoService proyectoServicio;
	private Proyecto itemSeleccionado;
	private List<Proyecto> listaItems;
	private boolean listing = false;
	private boolean listarProyectos = false;

	private Long companiaid;
	private List<SelectItem> companiaItems;
	private boolean listingCompania = false;

	private Long clienteid;
	private List<SelectItem> clienteItems;
	private boolean listingCliente = false;

	private Long proyectoTipoid;
	private List<SelectItem> proyectoTipoItems;
	private boolean listingProyectoTipo = false;

	private Long proyectoEtapaid;
	private Long proyectoEtapaid2;
	private List<SelectItem> proyectoEtapaItems;
	private boolean listingProyectoEtapa = false;

	private Long centroCostosid;
	private List<SelectItem> centroCostosItems;

	// requerimiento
	private List<Requerimiento> listaRequerimiento;
	private Requerimiento itemRequerimiento;
	private boolean listarRequerimientos = false;

	private Long requerimientoTipoid;
	private List<SelectItem> requerimientoTipoItems;
	private boolean listingRequerimientoTipo = false;

	private Long requerimientoEstadoid;
	private List<SelectItem> requerimientoEstadoItems;
	private boolean listingRequerimientoEstado = false;

	// Tarea

	private List<Tarea> listaTarea;
	private Tarea itemTarea;
	private boolean listarTareas = false;

	private Long tiempoid;
	private List<SelectItem> tiempoItems;
	private boolean listingTiempo = false;

	private Long tareaEstadoid;
	private List<SelectItem> tareaEstadoItems;
	private boolean listingTareaEstado = false;

	// Asignacion estado

	private List<Asignacion> listaAsignacion;
	private Asignacion itemAsignacion;
	private boolean listarAsignaciones = false;

	private Long usuarioid;
	private List<SelectItem> usuarioItems;
	private boolean listingUsuario = false;

	private Long asignacionEstadoid;
	private List<SelectItem> asignacionEstadoItems;
	private boolean listingAsignacionEstado = false;

	// Equipo cliente
	private List<EquipoCliente> listaEquipoCliente;
	private EquipoCliente itemEquipoCliente;

	private Long rolProyectoid;
	private List<SelectItem> rolProyectoItems;
	private boolean listingrolProyecto = false;

	private Long clienteContactoid;
	private List<SelectItem> clienteContactoItems;

	public ProyectoManagedBean() {
		proyectoServicio = new ProyectoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			Compania compania = new Compania();
			CentroCostos centroCostos = new CentroCostos();
			Cliente cliente = new Cliente();
			ProyectoTipo tipo = new ProyectoTipo();
			ProyectoEtapa etapa = new ProyectoEtapa();

			compania.setCompaniaid(companiaid);
			centroCostos.setCentroCostosid(centroCostosid);
			cliente.setClienteid(clienteid);
			tipo.setTipoid(proyectoTipoid);
			etapa.setEtapaid(proyectoEtapaid);

			itemSeleccionado.setCompania(compania);
			itemSeleccionado.setCentroCostos(centroCostos);
			itemSeleccionado.setCliente(cliente);
			itemSeleccionado.setEtapa(etapa);
			itemSeleccionado.setProyectoTipo(tipo);

			proyectoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProyectoUpdated");
			inicializar();

			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {

			Compania compania = new Compania();
			CentroCostos centroCostos = new CentroCostos();
			Cliente cliente = new Cliente();
			ProyectoTipo tipo = new ProyectoTipo();
			ProyectoEtapa etapa = new ProyectoEtapa();

			compania.setCompaniaid(companiaid);
			centroCostos.setCentroCostosid(centroCostosid);
			cliente.setClienteid(clienteid);

			tipo.setTipoid(proyectoTipoid);
			etapa.setEtapaid(proyectoEtapaid);

			itemSeleccionado.setProyectoNumero(proyectoServicio.siguienteProyecto());

			itemSeleccionado.setCompania(compania);
			itemSeleccionado.setCentroCostos(centroCostos);
			itemSeleccionado.setCliente(cliente);
			itemSeleccionado.setEtapa(etapa);
			itemSeleccionado.setProyectoTipo(tipo);

			itemSeleccionado.setFechaInicial(new Date());
			itemSeleccionado.setFechaFinal(new Date());

			itemSeleccionado.setFechaActualizacion(new Date());
			itemSeleccionado.setFechaEtapa(new Date());

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proyectoServicio.crear(itemSeleccionado);
			mensajeInfo("ProyectoCreated");
			inicializar();

			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		proyectoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Proyecto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Proyecto> getListaItems() {
		return listaItems;
	}

	public ProyectoService getProyectoServicio() {
		return proyectoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Proyecto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProyecto() {
		setListing(true);
		listaItems = proyectoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Proyecto();
		return "Crear";
	}

	public String prepararEdicion() {
		companiaid = itemSeleccionado.getCompania().getCompaniaid();
		cargarCentroCostos();
		centroCostosid = itemSeleccionado.getCentroCostos().getCentroCostosid();
		clienteid = itemSeleccionado.getCliente().getClienteid();
		proyectoTipoid = itemSeleccionado.getTipo().getTipoid();
		proyectoEtapaid = itemSeleccionado.getEtapa().getEtapaid();

		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	public String prepararListaRequerimientos() {
		return "RequerimientoLista";
	}

	public String prepararListaTareas() {

		return buscarTareasPorRequerimiento();
		// return "TareaLista";
	}

	public String prepararListaAsignacion() {
		
		
		
		//return "AsignacionLista";
		
		return buscarAsignacionPorTarea();
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(Proyecto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Proyecto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProyectoServicio(ProyectoService proyectoServicio) {
		this.proyectoServicio = proyectoServicio;
	}

	// CONSULTAR COMPANIA Y CENTRO DE COSTOS

	public List<SelectItem> getCompaniaItems() {

		if (!listingCompania) {
			listingCompania = false;
			companiaItems = new ArrayList<SelectItem>();
			List<Compania> companiaList = proyectoServicio.consultarCompanias();

			for (Compania compania : companiaList) {
				companiaItems.add(new SelectItem(compania.getCompaniaid(),
						"Nit: " + compania.getCompanianit() + " / " + compania.getCompanianombre()));

			}
		}

		return companiaItems;
	}

	public void setCompaniaItems(List<SelectItem> companiaItems) {
		this.companiaItems = companiaItems;
	}

	public List<SelectItem> getCentroCostosItems() {

		return centroCostosItems;
	}

	public void setCentroCostosItems(List<SelectItem> centroCostosItems) {
		this.centroCostosItems = centroCostosItems;
	}

	public Long getCentroCostosid() {
		return centroCostosid;
	}

	public void setCentroCostosid(Long centroCostosid) {
		this.centroCostosid = centroCostosid;
	}

	public void cargarCentroCostos() {

		if (companiaid != 0) {

			centroCostosItems = new ArrayList<SelectItem>();

			centroCostosItems.clear();

			List<CentroCostos> centrocostosList = proyectoServicio.consultarCentroscostos(companiaid);

			for (CentroCostos centroCostos : centrocostosList) {
				centroCostosItems.add(new SelectItem(centroCostos.getCentroCostosid(),
						centroCostos.getCodigo() + " / " + centroCostos.getNombre()));

			}

		}

	}

	public void cargarCentroCostos2(ValueChangeEvent e) {

		long ciaPrueba = Long.parseLong(e.getNewValue().toString());
		companiaid = ciaPrueba;
		if (companiaid != 0) {

			centroCostosItems = new ArrayList<SelectItem>();

			centroCostosItems.clear();

			List<CentroCostos> centrocostosList = proyectoServicio.consultarCentroscostos(companiaid);

			for (CentroCostos centroCostos : centrocostosList) {
				centroCostosItems.add(new SelectItem(centroCostos.getCentroCostosid(),
						centroCostos.getCodigo() + " / " + centroCostos.getNombre()));

			}

		}

	}

	public Long getClienteid() {
		return clienteid;
	}

	public void setClienteid(Long clienteid) {
		this.clienteid = clienteid;
	}

	public List<SelectItem> getClienteItems() {

		if (!listingCliente) {
			listingCliente = true;
			clienteItems = new ArrayList<SelectItem>();
			List<Cliente> clienteList = proyectoServicio.consultarClientes();

			for (Cliente cliente : clienteList) {
				clienteItems.add(new SelectItem(cliente.getClienteid(),
						cliente.getClienteidentificacion() + " / " + cliente.getClientenombre()));

			}
		}

		return clienteItems;
	}

	public void setClienteItems(List<SelectItem> clienteItems) {
		this.clienteItems = clienteItems;
	}

	public List<SelectItem> getProyectoTipoItems() {

		if (!listingProyectoTipo) {
			listingProyectoTipo = true;
			proyectoTipoItems = new ArrayList<SelectItem>();
			List<ProyectoTipo> proyectoTipoList = proyectoServicio.consultarTipos();

			for (ProyectoTipo proyectoTipo : proyectoTipoList) {
				proyectoTipoItems.add(new SelectItem(proyectoTipo.getTipoid(), proyectoTipo.getNombre()));

			}
		}

		return proyectoTipoItems;
	}

	public void setProyectoTipoItems(List<SelectItem> proyectoTipoItems) {
		this.proyectoTipoItems = proyectoTipoItems;
	}

	public List<SelectItem> getProyectoEtapaItems() {

		if (!listingProyectoEtapa) {
			listingProyectoEtapa = true;
			proyectoEtapaItems = new ArrayList<SelectItem>();
			List<ProyectoEtapa> proyectoEtapaList = proyectoServicio.consultarEtapas();

			for (ProyectoEtapa proyectoEtapa : proyectoEtapaList) {
				proyectoEtapaItems.add(new SelectItem(proyectoEtapa.getEtapaid(), proyectoEtapa.getNombre()));

			}
		}

		return proyectoEtapaItems;
	}

	public void setProyectoEtapaItems(List<SelectItem> proyectoEtapaItems) {
		this.proyectoEtapaItems = proyectoEtapaItems;
	}

	public Long getProyectoTipoid() {
		return proyectoTipoid;
	}

	public void setProyectoTipoid(Long proyectoTipoid) {
		this.proyectoTipoid = proyectoTipoid;
	}

	public Long getProyectoEtapaid() {
		return proyectoEtapaid;
	}

	public void setProyectoEtapaid(Long proyectoEtapaid) {
		this.proyectoEtapaid = proyectoEtapaid;
	}

	public Long getCompaniaid() {
		return companiaid;
	}

	public void setCompaniaid(Long companiaid) {
		this.companiaid = companiaid;
	}

	// requerimiento

	public List<Requerimiento> getListaRequerimiento() {

		return listaRequerimiento;
	}

	public void setListaRequerimiento(List<Requerimiento> listaRequerimiento) {
		this.listaRequerimiento = listaRequerimiento;
	}

	public Requerimiento getItemRequerimiento() {
		return itemRequerimiento;
	}

	public void setItemRequerimiento(Requerimiento itemRequerimiento) {
		this.itemRequerimiento = itemRequerimiento;
	}

	public String prepararConsultaRequerimiento() {

		return "RequerimientoDetalle";
	}

	public String prepararCreacionRequerimiento() {
		itemRequerimiento = new Requerimiento();
		itemRequerimiento.setRequerimientoNumero(proyectoServicio.siguienteRequerimiento());
		itemRequerimiento.setPrioridad(10);
		return "RequerimientoCrear";
	}

	public String prepararEdicionRequerimiento() {
		requerimientoTipoid = itemRequerimiento.getTipo().getTipoid();
		requerimientoEstadoid = itemRequerimiento.getEstado().getEstadoid();
		return "RequerimientoEditar";
	}

	public String crearRequerimiento() {

		try {

		

			RequerimientoTipo tipo = new RequerimientoTipo();
			RequerimientoEstado estado = new RequerimientoEstado();

			tipo.setTipoid(requerimientoTipoid);
			estado.setEstadoid(requerimientoEstadoid);
			itemRequerimiento.setTipo(tipo);
			itemRequerimiento.setEstado(estado);

			itemRequerimiento.setProyecto(itemSeleccionado);

			itemRequerimiento.setEstadoFecha(new Date());

			itemRequerimiento.setUsuarioCreacion(usuario());
			itemRequerimiento.setFechaCreacion(new Date());
			itemRequerimiento.setUsuarioModificacion(usuario());
			itemRequerimiento.setFechaModificacion(new Date());
			proyectoServicio.crearRequerimiento(itemRequerimiento);

			mensajeInfo("RequerimientoCreated");

			return prepararListaRequerimientos();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarRequerimiento() {

		try {
			RequerimientoTipo tipo = new RequerimientoTipo();
			RequerimientoEstado estado = new RequerimientoEstado();

			tipo.setTipoid(requerimientoTipoid);
			estado.setEstadoid(requerimientoEstadoid);

			if (!requerimientoEstadoid.equals(itemRequerimiento.getEstado().getEstadoid())) {
				itemRequerimiento.setEstadoFecha(new Date());

			}

			itemRequerimiento.setTipo(tipo);
			itemRequerimiento.setEstado(estado);
			itemRequerimiento.setUsuarioModificacion(usuario());
			itemRequerimiento.setFechaModificacion(new Date());
			itemRequerimiento.setProyecto(itemSeleccionado);
			proyectoServicio.actualizarRequerimiento(itemRequerimiento);
			mensajeInfo("RequerimientoUpdated");

			return prepararListaRequerimientos();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarRequerimiento() {

		try {

			proyectoServicio.eliminarRequerimiento(itemRequerimiento);
			mensajeInfo("RequerimientoDeleted");
			return prepararListaRequerimientos();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getRequerimientoTipoid() {
		return requerimientoTipoid;
	}

	public void setRequerimientoTipoid(Long requerimientoTipoid) {
		this.requerimientoTipoid = requerimientoTipoid;
	}

	public Long getRequerimientoEstadoid() {
		return requerimientoEstadoid;
	}

	public void setRequerimientoEstadoid(Long requerimientoEstadoid) {
		this.requerimientoEstadoid = requerimientoEstadoid;
	}

	public List<SelectItem> getRequerimientoTipoItems() {

		if (!listingRequerimientoTipo) {
			listingRequerimientoTipo = true;
			requerimientoTipoItems = new ArrayList<SelectItem>();
			List<RequerimientoTipo> requerimientoTipoList = proyectoServicio.consultarTiposRequerimiento();

			for (RequerimientoTipo requerimientoTipo : requerimientoTipoList) {
				requerimientoTipoItems
						.add(new SelectItem(requerimientoTipo.getTipoid(), requerimientoTipo.getNombre()));

			}
		}

		return requerimientoTipoItems;
	}

	public void setRequerimientoTipoItems(List<SelectItem> requerimientoTipoItems) {
		this.requerimientoTipoItems = requerimientoTipoItems;
	}

	public List<SelectItem> getRequerimientoEstadoItems() {

		if (!listingRequerimientoEstado) {
			listingRequerimientoEstado = true;
			requerimientoEstadoItems = new ArrayList<SelectItem>();
			List<RequerimientoEstado> requerimientoEstadoList = proyectoServicio.consultarEstadosRequerimiento();

			for (RequerimientoEstado requerimientoEstado : requerimientoEstadoList) {
				requerimientoEstadoItems
						.add(new SelectItem(requerimientoEstado.getEstadoid(), requerimientoEstado.getNombre()));

			}
		}

		return requerimientoEstadoItems;
	}

	public void setRequerimientoEstadoItems(List<SelectItem> requerimientoEstadoItems) {
		this.requerimientoEstadoItems = requerimientoEstadoItems;
	}

	// Tarea
	public List<Tarea> getListaTarea() {

		return listaTarea;
	}

	public void setListaTarea(List<Tarea> listaTarea) {
		this.listaTarea = listaTarea;
	}

	public Tarea getItemTarea() {
		return itemTarea;
	}

	public void setItemTarea(Tarea itemTarea) {
		this.itemTarea = itemTarea;
	}

	public String prepararConsultaTarea() {
		return "TareaDetalle";
	}

	public String prepararCreacionTarea() {
		itemTarea = new Tarea();
		itemTarea.setFechaInicio(new Date());
		itemTarea.setFechaFinal(new Date());
		return "TareaCrear";
	}

	public String prepararEdicionTarea() {
		tiempoid = itemTarea.getTiempo().getTiempoid();
		tareaEstadoid = itemTarea.getEstado().getEstado();

		return "TareaEditar";
	}

	public String crearTarea() {

		try {
			Tiempo tiempo = new Tiempo();
			TareaEstado estado = new TareaEstado();
			Long tiempoidentificador = 1L;
			Long estadoIde = 1L;
			tiempo.setTiempoid(tiempoidentificador);
			estado.setEstado(estadoIde);
			itemTarea.setTiempo(tiempo);
			itemTarea.setEstado(estado);
			itemTarea.setPrioridad(10.0);
			itemTarea.setFechaInicio(new Date());
			itemTarea.setFechaFinal(new Date());
			itemTarea.setFechaActualizacion(new Date());

			itemTarea.setRequerimiento(itemRequerimiento);
			itemTarea.setTareaNumero(proyectoServicio.siguienteTarea());

			itemTarea.setUsuarioCreacion(usuario());
			itemTarea.setFechaCreacion(new Date());
			itemTarea.setUsuarioModificacion(usuario());
			itemTarea.setFechaModificacion(new Date());
			proyectoServicio.crearTarea(itemTarea);

			mensajeInfo("TareaCreated");

			return prepararListaTareas();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarTarea() {

		try {
			Tiempo tiempo = new Tiempo();
			TareaEstado estado = new TareaEstado();

			tiempo.setTiempoid(tiempoid);
			estado.setEstado(tareaEstadoid);
			itemTarea.setTiempo(tiempo);
			itemTarea.setEstado(estado);
			itemTarea.setFechaActualizacion(new Date());
			itemTarea.setRequerimiento(itemRequerimiento);
			itemTarea.setEstado(estado);
			itemTarea.setUsuarioModificacion(usuario());
			itemTarea.setFechaModificacion(new Date());
			proyectoServicio.actualizarTarea(itemTarea);
			mensajeInfo("TareaUpdated");
			return prepararConsultaRequerimiento();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarTarea() {

		try {

			proyectoServicio.eliminarTarea(itemTarea);
			mensajeInfo("TareaDeleted");
			return prepararConsultaRequerimiento();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getTiempoid() {
		return tiempoid;
	}

	public void setTiempoid(Long tiempoid) {
		this.tiempoid = tiempoid;
	}

	public Long getTareaEstadoid() {
		return tareaEstadoid;
	}

	public void setTareaEstadoid(Long tareaEstadoid) {
		this.tareaEstadoid = tareaEstadoid;
	}

	public List<SelectItem> getTiempoItems() {

		if (!listingTiempo) {
			listingTiempo = true;
			tiempoItems = new ArrayList<SelectItem>();
			List<Tiempo> tiempoList = proyectoServicio.consultarTiempos();

			for (Tiempo tiempo : tiempoList) {
				tiempoItems.add(new SelectItem(tiempo.getTiempoid(), tiempo.getNombre() + " / " + tiempo.getCodigo()));

			}
		}

		return tiempoItems;
	}

	public void setTiempoItems(List<SelectItem> tiempoItems) {
		this.tiempoItems = tiempoItems;
	}

	public List<SelectItem> getTareaEstadoItems() {

		if (!listingTareaEstado) {
			listingTareaEstado = true;
			tareaEstadoItems = new ArrayList<SelectItem>();
			List<TareaEstado> tareaEstadoList = proyectoServicio.consultarEstadosTarea();

			for (TareaEstado tareaEstado : tareaEstadoList) {
				tareaEstadoItems.add(new SelectItem(tareaEstado.getEstado(), tareaEstado.getNombre()));

			}
		}

		return tareaEstadoItems;
	}

	public void setTareaEstadoItems(List<SelectItem> tareaEstadoItems) {
		this.tareaEstadoItems = tareaEstadoItems;
	}

	// Asignacion
	public List<Asignacion> getListaAsignacion() {

		return listaAsignacion;
	}

	public void setListaAsignacion(List<Asignacion> listaAsignacion) {
		this.listaAsignacion = listaAsignacion;
	}

	public Asignacion getItemAsignacion() {
		return itemAsignacion;
	}

	public void setItemAsignacion(Asignacion itemAsignacion) {
		this.itemAsignacion = itemAsignacion;
	}

	public String prepararConsultaAsignacion() {
		return "AsignacionDetalle";
	}

	public String prepararCreacionAsignacion() {
		itemAsignacion = new Asignacion();
		return "AsignacionCrear";
	}

	public String prepararEdicionAsignacion() {
		usuarioid = itemAsignacion.getUsuario().getUsuarioid();
		asignacionEstadoid = itemAsignacion.getEstado().getEstadoid();
		return "AsignacionEditar";
	}

	public String crearAsignacion() {

		try {
			Usuario Usuario = new Usuario();
			AsignacionEstado estado = new AsignacionEstado();

			asignacionEstadoid = (long) 1;  
			
			TareaEstado estadoTarea = new TareaEstado(); 
			
			Long estadoTareaId = 2L;  // Tarea en Proceso
			estadoTarea.setEstado(estadoTareaId);
			
			itemTarea.setEstado(estadoTarea);
			itemTarea.setUsuarioModificacion(usuario());
			itemTarea.setFechaModificacion(new Date());
			
			
			

			Usuario.setUsuarioid(usuarioid);
			estado.setEstadoid(asignacionEstadoid);
			itemAsignacion.setUsuario(Usuario);
			itemAsignacion.setEstado(estado);

			itemAsignacion.setTarea(itemTarea);

			itemAsignacion.setAsignacionNumero(proyectoServicio.siguienteAsignacion());

			itemAsignacion.setFechaAsignacion(new Date());
			itemAsignacion.setFechaEstado(new Date());

			itemAsignacion.setUsuarioCreacion(usuario());
			itemAsignacion.setFechaCreacion(new Date());
			itemAsignacion.setUsuarioModificacion(usuario());
			itemAsignacion.setFechaModificacion(new Date());

			proyectoServicio.crearAsignacion(itemAsignacion);
			proyectoServicio.actualizarTarea(itemTarea);

			mensajeInfo("AsignacionCreated");

			return prepararListaAsignacion();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarAsignacion() {

		try {
			Usuario Usuario = new Usuario();
			AsignacionEstado estado = new AsignacionEstado();

			Usuario.setUsuarioid(usuarioid);
			estado.setEstadoid(asignacionEstadoid);
			itemAsignacion.setUsuario(Usuario);
			itemAsignacion.setEstado(estado);

			itemAsignacion.setTarea(itemTarea);
			itemAsignacion.setEstado(estado);
			itemAsignacion.setUsuarioModificacion(usuario());
			itemAsignacion.setFechaModificacion(new Date());
			proyectoServicio.actualizarAsignacion(itemAsignacion);
			mensajeInfo("AsignacionUpdated");
			return prepararConsultaTarea();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarAsignacion() {

		try {

			proyectoServicio.eliminarAsignacion(itemAsignacion);
			mensajeInfo("AsignacionDeleted");
			return prepararConsultaTarea();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public Long getAsignacionEstadoid() {
		return asignacionEstadoid;
	}

	public void setAsignacionEstadoid(Long asignacionEstadoid) {
		this.asignacionEstadoid = asignacionEstadoid;
	}

	public List<SelectItem> getUsuarioItems() {

		if (!listingUsuario) {
			listingUsuario = true;
			usuarioItems = new ArrayList<SelectItem>();
			List<Usuario> usuarioList = proyectoServicio.consultarUsuarios();

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

	public List<SelectItem> getAsignacionEstadoItems() {

		if (!listingAsignacionEstado) {
			listingAsignacionEstado = true;
			asignacionEstadoItems = new ArrayList<SelectItem>();
			List<AsignacionEstado> asignacionEstadoList = proyectoServicio.consultarEstadosAsignacion();

			for (AsignacionEstado asignacionEstado : asignacionEstadoList) {
				asignacionEstadoItems.add(new SelectItem(asignacionEstado.getEstadoid(), asignacionEstado.getNombre()));

			}
		}

		return asignacionEstadoItems;
	}

	public void setAsignacionEstadoItems(List<SelectItem> asignacionEstadoItems) {
		this.asignacionEstadoItems = asignacionEstadoItems;
	}

	// equipo cliente

	public List<EquipoCliente> getListaEquipoCliente() {

		listaEquipoCliente = proyectoServicio.consultarEquipoCliente(itemSeleccionado.getProyectoid());

		return listaEquipoCliente;
	}

	public void setListaEquipoCliente(List<EquipoCliente> listaEquipoCliente) {
		this.listaEquipoCliente = listaEquipoCliente;
	}

	public EquipoCliente getItemEquipoCliente() {
		return itemEquipoCliente;
	}

	public void setItemEquipoCliente(EquipoCliente itemEquipoCliente) {
		this.itemEquipoCliente = itemEquipoCliente;
	}

	public String prepararConsultaEquipoCliente() {
		return "EquipoClienteDetalle";
	}

	public String prepararCreacionEquipoCliente() {
		itemEquipoCliente = new EquipoCliente();
		return "EquipoClienteCrear";
	}

	public String prepararEdicionEquipoCliente() {
		rolProyectoid = itemEquipoCliente.getRol().getRolid();
		clienteContactoid = itemEquipoCliente.getContacto().getContactoid();
		return "EquipoClienteEditar";
	}

	public String crearEquipoCliente() {

		try {
			RolProyecto rolProyecto = new RolProyecto();
			ClienteContacto contacto = new ClienteContacto();

			rolProyecto.setRolid(rolProyectoid);
			contacto.setContactoid(clienteContactoid);
			itemEquipoCliente.setRol(rolProyecto);
			itemEquipoCliente.setContacto(contacto);

			itemEquipoCliente.setProyecto(itemSeleccionado);

			itemEquipoCliente.setUsuarioCreacion(usuario());
			itemEquipoCliente.setFechaCreacion(new Date());
			itemEquipoCliente.setUsuarioModificacion(usuario());
			itemEquipoCliente.setFechaModificacion(new Date());
			proyectoServicio.crearEquipoCliente(itemEquipoCliente);

			mensajeInfo("EquipoClienteCreated");
			return prepararConsulta();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarEquipoCliente() {

		try {
			RolProyecto rolProyecto = new RolProyecto();
			ClienteContacto contacto = new ClienteContacto();

			rolProyecto.setRolid(rolProyectoid);
			contacto.setContactoid(clienteContactoid);
			itemEquipoCliente.setRol(rolProyecto);
			itemEquipoCliente.setContacto(contacto);

			itemEquipoCliente.setProyecto(itemSeleccionado);

			itemEquipoCliente.setUsuarioModificacion(usuario());
			itemEquipoCliente.setFechaModificacion(new Date());

			proyectoServicio.actualizarEquipoCliente(itemEquipoCliente);
			mensajeInfo("EquipoClienteUpdated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarEquipoCliente() {

		try {

			proyectoServicio.eliminarEquipoCliente(itemEquipoCliente);
			mensajeInfo("EquipoClienteDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getRolProyectoid() {
		return rolProyectoid;
	}

	public void setRolProyectoid(Long rolProyectoid) {
		this.rolProyectoid = rolProyectoid;
	}

	public Long getClienteContactoid() {
		return clienteContactoid;
	}

	public void setClienteContactoid(Long clienteContactoid) {
		this.clienteContactoid = clienteContactoid;
	}

	public List<SelectItem> getRolProyectoItems() {

		if (!listingrolProyecto) {
			listingrolProyecto = true;
			rolProyectoItems = new ArrayList<SelectItem>();
			List<RolProyecto> rolProyectoList = proyectoServicio.consultarRolProyecto();

			for (RolProyecto rolProyecto : rolProyectoList) {
				rolProyectoItems.add(new SelectItem(rolProyecto.getRolid(), rolProyecto.getNombre()));

			}
		}

		return rolProyectoItems;
	}

	public void setRolProyectoItems(List<SelectItem> rolProyectoItems) {
		this.rolProyectoItems = rolProyectoItems;
	}

	public List<SelectItem> getClienteContactoItems() {

		clienteContactoItems = new ArrayList<SelectItem>();
		List<ClienteContacto> clienteContactoList = proyectoServicio
				.consultarClienteContacto(itemSeleccionado.getCliente().getClienteid());

		for (ClienteContacto clienteContacto : clienteContactoList) {
			clienteContactoItems
					.add(new SelectItem(clienteContacto.getContactoid(), clienteContacto.getApellidoNombre()));

		}

		return clienteContactoItems;
	}

	public void setclienteContactoItems(List<SelectItem> clienteContactoItems) {
		this.clienteContactoItems = clienteContactoItems;
	}

	// equipo compania

	private List<EquipoCompania> listaEquipoCompania;
	private EquipoCompania itemEquipoCompania;

	private Long empleadoid;
	private List<SelectItem> empleadoItems;

	public List<EquipoCompania> getListaEquipoCompania() {

		listaEquipoCompania = proyectoServicio.consultarEquipoCompania(itemSeleccionado.getProyectoid());

		return listaEquipoCompania;
	}

	public void setListaEquipoCompania(List<EquipoCompania> listaEquipoCompania) {
		this.listaEquipoCompania = listaEquipoCompania;
	}

	public EquipoCompania getItemEquipoCompania() {
		return itemEquipoCompania;
	}

	public void setItemEquipoCompania(EquipoCompania itemEquipoCompania) {
		this.itemEquipoCompania = itemEquipoCompania;
	}

	public String prepararConsultaEquipoCompania() {
		return "EquipoCompaniaDetalle";
	}

	public String prepararCreacionEquipoCompania() {
		itemEquipoCompania = new EquipoCompania();
		return "EquipoCompaniaCrear";
	}

	public String prepararEdicionEquipoCompania() {
		rolProyectoid = itemEquipoCompania.getRol().getRolid();
		empleadoid = itemEquipoCompania.getEmpleado().getEmpleadoid();
		return "EquipoCompaniaEditar";
	}

	public String crearEquipoCompania() {

		try {
			RolProyecto rolProyecto = new RolProyecto();
			Empleado empleado = new Empleado();

			rolProyecto.setRolid(rolProyectoid);
			empleado.setEmpleadoid(empleadoid);
			itemEquipoCompania.setRol(rolProyecto);
			itemEquipoCompania.setEmpleado(empleado);

			itemEquipoCompania.setProyecto(itemSeleccionado);

			itemEquipoCompania.setUsuarioCreacion(usuario());
			itemEquipoCompania.setFechaCreacion(new Date());
			itemEquipoCompania.setUsuarioModificacion(usuario());
			itemEquipoCompania.setFechaModificacion(new Date());
			proyectoServicio.crearEquipoCompania(itemEquipoCompania);

			mensajeInfo("EquipoCompaniaCreated");
			return prepararConsulta();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarEquipoCompania() {

		try {
			RolProyecto rolProyecto = new RolProyecto();
			Empleado empleado = new Empleado();

			rolProyecto.setRolid(rolProyectoid);
			empleado.setEmpleadoid(empleadoid);
			itemEquipoCompania.setRol(rolProyecto);
			itemEquipoCompania.setEmpleado(empleado);

			itemEquipoCompania.setProyecto(itemSeleccionado);

			itemEquipoCompania.setUsuarioModificacion(usuario());
			itemEquipoCompania.setFechaModificacion(new Date());

			proyectoServicio.actualizarEquipoCompania(itemEquipoCompania);
			mensajeInfo("EquipoCompaniaUpdated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarEquipoCompania() {

		try {

			proyectoServicio.eliminarEquipoCompania(itemEquipoCompania);
			mensajeInfo("EquipoCompaniaDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getempleadoid() {
		return empleadoid;
	}

	public void setempleadoid(Long empleadoid) {
		this.empleadoid = empleadoid;
	}

	public List<SelectItem> getempleadoItems() {

		empleadoItems = new ArrayList<SelectItem>();
		List<Empleado> empleadoList = proyectoServicio
				.consultarEmpleados(itemSeleccionado.getCompania().getCompaniaid());

		for (Empleado empleado : empleadoList) {
			empleadoItems.add(new SelectItem(empleado.getEmpleadoid(), empleado.getApellidoNombre()));

		}

		return empleadoItems;
	}

	public void setempleadoItems(List<SelectItem> empleadoItems) {
		this.empleadoItems = empleadoItems;
	}

	// Proyecto Etapa Cambio

	private List<ProyectoEtapaCambio> listaProyectoEtapaCambio;
	private ProyectoEtapaCambio itemProyectoEtapaCambio;

	public List<ProyectoEtapaCambio> getListaProyectoEtapaCambio() {

		listaProyectoEtapaCambio = proyectoServicio.consultarProyectoEtapaCambio(itemSeleccionado.getProyectoid());

		return listaProyectoEtapaCambio;
	}

	public void setListaProyectoEtapaCambio(List<ProyectoEtapaCambio> listaProyectoEtapaCambio) {
		this.listaProyectoEtapaCambio = listaProyectoEtapaCambio;
	}

	public ProyectoEtapaCambio getItemProyectoEtapaCambio() {
		return itemProyectoEtapaCambio;
	}

	public void setItemProyectoEtapaCambio(ProyectoEtapaCambio itemProyectoEtapaCambio) {
		this.itemProyectoEtapaCambio = itemProyectoEtapaCambio;
	}

	public String prepararConsultaProyectoEtapaCambio() {
		return "ProyectoEtapaCambioDetalle";
	}

	public String prepararCreacionProyectoEtapaCambio() {
		itemProyectoEtapaCambio = new ProyectoEtapaCambio();
		return "ProyectoEtapaCambioCrear";
	}

	public String prepararEdicionProyectoEtapaCambio() {

		proyectoEtapaid = itemProyectoEtapaCambio.getEtapaActual().getEtapaid();
		proyectoEtapaid2 = itemProyectoEtapaCambio.getEtapaAnterior().getEtapaid();
		return "ProyectoEtapaCambioEditar";
	}

	public String crearProyectoEtapaCambio() {

		try {
			ProyectoEtapa etapaActual = new ProyectoEtapa();
			ProyectoEtapa etapaAnterior = new ProyectoEtapa();

			etapaActual.setEtapaid(proyectoEtapaid);
			etapaAnterior.setEtapaid(proyectoEtapaid2);

			itemProyectoEtapaCambio.setEtapaActual(etapaActual);
			itemProyectoEtapaCambio.setEtapaAnterior(etapaAnterior);

			itemProyectoEtapaCambio.setProyecto(itemSeleccionado);

			itemProyectoEtapaCambio.setUsuarioCreacion(usuario());
			itemProyectoEtapaCambio.setFechaCreacion(new Date());
			itemProyectoEtapaCambio.setUsuarioModificacion(usuario());
			itemProyectoEtapaCambio.setFechaModificacion(new Date());
			proyectoServicio.crearProyectoEtapaCambio(itemProyectoEtapaCambio);

			mensajeInfo("ProyectoEtapaCambioCreated");
			return prepararConsulta();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarProyectoEtapaCambio() {

		try {
			ProyectoEtapa etapaActual = new ProyectoEtapa();
			ProyectoEtapa etapaAnterior = new ProyectoEtapa();

			etapaActual.setEtapaid(proyectoEtapaid);
			etapaAnterior.setEtapaid(proyectoEtapaid2);

			itemProyectoEtapaCambio.setEtapaActual(etapaActual);
			itemProyectoEtapaCambio.setEtapaAnterior(etapaAnterior);

			itemProyectoEtapaCambio.setProyecto(itemSeleccionado);

			itemProyectoEtapaCambio.setUsuarioModificacion(usuario());
			itemProyectoEtapaCambio.setFechaModificacion(new Date());

			proyectoServicio.actualizarProyectoEtapaCambio(itemProyectoEtapaCambio);
			mensajeInfo("ProyectoEtapaCambioUpdated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarProyectoEtapaCambio() {

		try {

			proyectoServicio.eliminarProyectoEtapaCambio(itemProyectoEtapaCambio);
			mensajeInfo("ProyectoEtapaCambioDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String inicializarNumero() {

		try {

			proyectoServicio.inicializarNumero();

			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getProyectoEtapaid2() {
		return proyectoEtapaid2;
	}

	public void setProyectoEtapaid2(Long proyectoEtapaid2) {
		this.proyectoEtapaid2 = proyectoEtapaid2;
	}

	public boolean isListarProyectos() {
		return listarProyectos;
	}

	public void setListarProyectos(boolean listarProyectos) {
		this.listarProyectos = listarProyectos;
	}

	public String buscarProyectos() {

		listarProyectos = false;
		listaItems = new ArrayList<Proyecto>();

		try {
			if (proyectoEtapaid != null && clienteid != null) {

				this.listaItems = proyectoServicio.consultarProyectosPorClienteEtapa(clienteid, proyectoEtapaid);
				listarProyectos = true;
				return "Lista";

			} else {
				listarProyectos = false;
				mensaje("No existen Datos  para la  busqueda. Favor Verifique");
				return null;
			}
		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}
	
	
	public String buscarProyectosCliente() {

		listarProyectos = false;
		listaItems = new ArrayList<Proyecto>();

		try {
			if (clienteid != null) {

				this.listaItems = proyectoServicio.consultarProyectosPorCliente(clienteid);
				listarProyectos = true;
				return "Lista";

			} else {
				listarProyectos = false;
				mensaje("No existen Datos  para la  busqueda. Favor Verifique");
				return null;
			}
		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}
	
	
	
	

	public boolean isListarRequerimientos() {
		return listarRequerimientos;
	}

	public void setListarRequerimientos(boolean listarRequerimientos) {
		this.listarRequerimientos = listarRequerimientos;
	}

	public String buscarRequerimientos() {

		listarRequerimientos = false;
		listaRequerimiento = new ArrayList<Requerimiento>();

		try {
			if (requerimientoTipoid != null && requerimientoEstadoid != null) {

				this.listaRequerimiento = proyectoServicio.consultarRequerimientosPorProyectoTipoEstado(
						itemSeleccionado.getProyectoid(), requerimientoTipoid, requerimientoEstadoid);
				listarRequerimientos = true;
				return "RequerimientoLista";

			} else {
				listarRequerimientos = false;
				mensaje("No existen Datos  para la  busqueda. Favor Verifique");
				return null;
			}
		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}

	public String listarTodosRequerimientos() {

		listarRequerimientos = false;
		listaRequerimiento = new ArrayList<Requerimiento>();

		try {

			this.listaRequerimiento = proyectoServicio.consultarRequerimiento(itemSeleccionado.getProyectoid());
			listarRequerimientos = true;
			return "RequerimientoLista";

		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}

	public String buscarRequerimientosTotal() {

		listarRequerimientos = false;
		listaRequerimiento = new ArrayList<Requerimiento>();

		try {
			if (requerimientoTipoid != null && requerimientoEstadoid != null) {

				this.listaRequerimiento = proyectoServicio.consultarRequerimientosPorTipoEstado(requerimientoTipoid,
						requerimientoEstadoid);
				listarRequerimientos = true;
				return "RequerimientoLista";

			} else {
				listarRequerimientos = false;
				mensaje("No existen Datos  para la  busqueda. Favor Verifique");
				return null;
			}
		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}

	public boolean isListarTareas() {
		return listarTareas;
	}

	public void setListarTareas(boolean listarTareas) {
		this.listarTareas = listarTareas;
	}

	public String buscarTareasPorRequerimiento() {

		listarTareas = false;
		listaTarea = new ArrayList<Tarea>();

		this.listaTarea = proyectoServicio.consultarTarea(itemRequerimiento.getRequerimientoid());
		listarTareas = true;
		return "TareaLista";

	}

	public String buscarTareasTotal() {

		listarTareas = false;
		listaTarea = new ArrayList<Tarea>();

		try {
			if (tareaEstadoid != null) {

				this.listaTarea = proyectoServicio.consultarTareasPorEstado(tareaEstadoid);
				listarTareas = true;
				return "TareaLista";

			} else {
				listarTareas = false;
				mensaje("No existen Datos  para la  busqueda. Favor Verifique");
				return null;
			}
		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}

	public String buscarAsignacionTotal() {

		listarAsignaciones = false;
		listaAsignacion = new ArrayList<Asignacion>();

		try {
			if (usuarioid != null && asignacionEstadoid != null) {

				this.listaAsignacion = proyectoServicio.consultarAsignacionPorUsuarioEstado(usuarioid,
						asignacionEstadoid);
				listarAsignaciones = true;
				return "AsignacionLista";

			} else {
				listarAsignaciones = false;
				mensaje("No existen Datos  para la  busqueda. Favor Verifique");
				return null;
			}
		} catch (Exception e) {
			mensaje("No existen Datos  para la  busqueda. Favor Verifique");
			return null;
		}

	}

	public boolean isListarAsignaciones() {
		return listarAsignaciones;
	}

	public void setListarAsignaciones(boolean listarAsignaciones) {
		this.listarAsignaciones = listarAsignaciones;
	}

	public String buscarAsignacionPorTarea() {

		listaAsignacion = new ArrayList<Asignacion>();

		listaAsignacion = proyectoServicio.consultarAsignacionPorTarea(itemTarea.getTareaid());

		return "AsignacionLista";

	}

}
