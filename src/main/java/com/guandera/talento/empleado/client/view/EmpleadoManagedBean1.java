package com.guandera.talento.empleado.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.core.shared.model.CompaniaCargo;
import com.guandera.core.shared.model.Sede;
import com.guandera.core.shared.model.TipoIdentificacion;
import com.guandera.talento.empleado.client.service.EmpleadoService1;
import com.guandera.talento.empleado.server.service.EmpleadoServiceImpl1;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoAcudiente;
import com.guandera.talento.empleado.shared.model.EmpleadoEstado;
import com.guandera.talento.empleado.shared.model.EmpleadoInformacionAcademica;
import com.guandera.talento.empleado.shared.model.EmpleadoInformacionLaboral;
import com.guandera.talento.empleado.shared.model.EmpleadoSeguridadSocial;
import com.guandera.talento.empleado.shared.model.EmpresaSeguridadSocial;

@ManagedBean(name = "empleadoMB1")
@SessionScoped
public class EmpleadoManagedBean1 extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private EmpleadoService1 empleadoServicio;
	private Empleado itemSeleccionado;
	private List<Empleado> listaItems;
	private boolean listing = false;

	private Long sedeid;
	private List<SelectItem> sedeItems;
	private boolean listingSede = false;

	private Long tipoidentificacionid;
	private List<SelectItem> tipoIdentificacionItems;
	private boolean listingTipoIdentificacion = false;

	private Long cargoid;
	private List<SelectItem> cargoItems;
	private boolean listingCargo = false;



	private Long empleadoEstadoid;
	private List<SelectItem> empleadoEstadoItems;
	private boolean listingEmpleadoEstado = false;

	// informacion Academica

	private List<EmpleadoInformacionAcademica> listaInformacionAcademica;
	private EmpleadoInformacionAcademica itemInformacionAcademica;
	private boolean listingInformacionAcademica = false;

	// informacion Laboral
	private List<EmpleadoInformacionLaboral> listaInformacionLaboral;
	private EmpleadoInformacionLaboral itemInformacionLaboral;
	private boolean listingInformacionLaboral = false;

	// Empleado Acudiente
	private List<EmpleadoAcudiente> listaEmpleadoAcudiente;
	private EmpleadoAcudiente itemEmpleadoAcudiente;
	private boolean listingEmpleadoAcudiente = false;

	// Seguridad Social
	private List<EmpleadoSeguridadSocial> listaEmpleadoSeguridadSocial;
	private EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial;
	private boolean listingEmpleadoSeguridadSocial = false;

	private Long empresaseguridadsid;
	private List<SelectItem> empresaSeguridadSocialItems;
	private boolean listingEmpresaseguridadsocial = false;

	public EmpleadoManagedBean1() {
		empleadoServicio = new EmpleadoServiceImpl1();

		inicializar();
	}

	public String actualizar() {

		try {

			TipoIdentificacion tipoid = new TipoIdentificacion();
			Sede sede = new Sede();
			CompaniaCargo cargo = new CompaniaCargo();
			EmpleadoEstado empleadoEstado = new EmpleadoEstado();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			sede.setSedeid(sedeid);
			cargo.setCompaniacargoid(cargoid);

			//empleadoEstado.setEmpleadoestadoid(empleadoEstadoid);

			String nombreApellido = itemSeleccionado.getPrimerNombre().trim() + ' '
					+ itemSeleccionado.getSegundoNombre().trim() + ' ' + itemSeleccionado.getPrimerApellido().trim()
					+ ' ' + itemSeleccionado.getSegundoApellido().trim();

			String apellidoNombre = itemSeleccionado.getPrimerApellido().trim() + ' '
					+ itemSeleccionado.getSegundoApellido().trim() + ' ' + itemSeleccionado.getPrimerNombre().trim()
					+ ' ' + itemSeleccionado.getSegundoNombre().trim();

			itemSeleccionado.setApellidoNombre(apellidoNombre);
			itemSeleccionado.setNombreApellido(nombreApellido);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setSede(sede);
			itemSeleccionado.setCargo(cargo);

			itemSeleccionado.setEstado(empleadoEstado);
			empleadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("EmpleadoUpdated");

			listing = false;

			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		try {

			TipoIdentificacion tipoid = new TipoIdentificacion();
			Sede sede = new Sede();
			CompaniaCargo cargo = new CompaniaCargo();
			EmpleadoEstado empleadoEstado = new EmpleadoEstado();

			tipoid.setTipoidentificacionid(tipoidentificacionid);
			sede.setSedeid(sedeid);
			cargo.setCompaniacargoid(cargoid);

			//empleadoEstado.setEmpleadoestadoid(empleadoEstadoid);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			itemSeleccionado.setTipoIdentificacion(tipoid);
			itemSeleccionado.setSede(sede);
			itemSeleccionado.setCargo(cargo);

			itemSeleccionado.setEstado(empleadoEstado);

			String nombreApellido = itemSeleccionado.getPrimerNombre().trim() + ' '
					+ itemSeleccionado.getSegundoNombre().trim() + ' ' + itemSeleccionado.getPrimerApellido().trim()
					+ ' ' + itemSeleccionado.getSegundoApellido().trim();

			String apellidoNombre = itemSeleccionado.getPrimerApellido().trim() + ' '
					+ itemSeleccionado.getSegundoApellido().trim() + ' ' + itemSeleccionado.getPrimerNombre().trim()
					+ ' ' + itemSeleccionado.getSegundoNombre().trim();

			itemSeleccionado.setApellidoNombre(apellidoNombre);
			itemSeleccionado.setNombreApellido(nombreApellido);
			itemSeleccionado.setContratoEmpleado(false);

			empleadoServicio.crear(itemSeleccionado);
			mensajeInfo("EmpleadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		empleadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Empleado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Empleado> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = empleadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public EmpleadoService1 getEmpleadoServicio() {
		return empleadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Empleado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarEmpleado() {
		setListing(true);
		listaItems = empleadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		listingInformacionLaboral = false;
		listingInformacionAcademica = false;
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Empleado();
		sedeid = (long) 0;
		cargoid = (long) 0;
		tipoidentificacionid = (long) 0;
		empleadoEstadoid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {
		sedeid = itemSeleccionado.getSede().getSedeid();
		cargoid = itemSeleccionado.getCargo().getCompaniacargoid();
		tipoidentificacionid = itemSeleccionado.getTipoIdentificacion().getTipoidentificacionid();
		//empleadoEstadoid = itemSeleccionado.getEstado().getEmpleadoestadoid();

		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(Empleado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Empleado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setEmpleadoServicio(EmpleadoService1 empleadoServicio) {
		this.empleadoServicio = empleadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = empleadoServicio.consultarTodos();
		}
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
			List<Sede> sedeList = empleadoServicio.consultarSedes();

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
			List<TipoIdentificacion> tipoIdList = empleadoServicio.consultarTiposIdentificacion();

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
			List<CompaniaCargo> cargoList = empleadoServicio.consultarCargos();

			for (CompaniaCargo cargo : cargoList) {
				cargoItems.add(new SelectItem(cargo.getCompaniacargoid(), cargo.getCompaniacargonombre()));

			}
		}
		return cargoItems;
	}

	public void setCargoItems(List<SelectItem> cargoItems) {
		this.cargoItems = cargoItems;
	}

	




	public Long getEmpleadoEstadoid() {
		return empleadoEstadoid;
	}

	public void setEmpleadoEstadoid(Long empleadoEstadoid) {
		this.empleadoEstadoid = empleadoEstadoid;
	}

	public List<SelectItem> getEmpleadoEstadoItems() {
		if (!listingEmpleadoEstado) {
			listingEmpleadoEstado = true;

			empleadoEstadoItems = new ArrayList<SelectItem>();
			List<EmpleadoEstado> empleadoEstadoList = empleadoServicio.consultarEmpleadoEstados();

			for (EmpleadoEstado empleadoEstado : empleadoEstadoList) {
				empleadoEstadoItems.add(
						new SelectItem(empleadoEstado.getEstadoId(), empleadoEstado.getNombre()));

			}
		}
		return empleadoEstadoItems;
	}

	public void setEmpleadoEstadoItems(List<SelectItem> empleadoEstadoItems) {
		this.empleadoEstadoItems = empleadoEstadoItems;
	}

	// empleado informacion ACADEMICA //

	public List<EmpleadoInformacionAcademica> getListaInformacionAcademica() {
		if (!listingInformacionAcademica) {
			listingInformacionAcademica = true;
			listaInformacionAcademica = empleadoServicio
					.consultarInformacionAcademicaEmpleado(itemSeleccionado.getEmpleadoid());
		}
		return listaInformacionAcademica;
	}

	public void setListaInformacionAcademica(List<EmpleadoInformacionAcademica> listaInformacionAcademica) {
		this.listaInformacionAcademica = listaInformacionAcademica;
	}

	public EmpleadoInformacionAcademica getItemInformacionAcademica() {
		return itemInformacionAcademica;
	}

	public void setItemInformacionAcademica(EmpleadoInformacionAcademica itemInformacionAcademica) {
		this.itemInformacionAcademica = itemInformacionAcademica;
	}

	public String prepararConsultaInformacionAcademica() {
		return "EmpleadoInformacionAcademicaDetalle";
	}

	public String prepararCreacionInformacionAcademica() {
		itemInformacionAcademica = new EmpleadoInformacionAcademica();
		return "EmpleadoInformacionAcademicaCrear";
	}

	public String prepararEdicionInformacionAcademica() {
		return "EmpleadoInformacionAcademicaEditar";
	}

	public String crearInformacionAcademica() {

		try {
			itemInformacionAcademica.setEmpleado(itemSeleccionado);

			itemInformacionAcademica.setUsuarioCreacion(usuario());
			itemInformacionAcademica.setFechaCreacion(new Date());
			itemInformacionAcademica.setUsuarioModificacion(usuario());
			itemInformacionAcademica.setFechaModificacion(new Date());
			empleadoServicio.crearEmpleadoInformacionAcademica(itemInformacionAcademica);

			mensajeInfo("EmpleadoInformacionAcademicaCreated");
			listingInformacionAcademica = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarInformacionAcademica() {

		try {
			itemInformacionAcademica.setUsuarioModificacion(usuario());
			itemInformacionAcademica.setFechaModificacion(new Date());

			empleadoServicio.actualizarEmpleadoInformacionAcademica(itemInformacionAcademica);
			mensajeInfo("EmpleadoInformacionAcademicaUpdated");
			listingInformacionAcademica = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarInformacionAcademica() {

		try {

			empleadoServicio.eliminarEmpleadoInformacionAcademica(itemInformacionAcademica);
			mensajeInfo("EmpleadoInformacionAcademicaDeleted");
			listingInformacionAcademica = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}
	// informacion laboral

	public List<EmpleadoInformacionLaboral> getListaInformacionLaboral() {
		if (!listingInformacionLaboral) {
			listingInformacionLaboral = true;
			listaInformacionLaboral = empleadoServicio
					.consultarInformacionLaboralEmpleado(itemSeleccionado.getEmpleadoid());
		}
		return listaInformacionLaboral;
	}

	public void setListaInformacionLaboral(List<EmpleadoInformacionLaboral> listaInformacionLaboral) {
		this.listaInformacionLaboral = listaInformacionLaboral;
	}

	public EmpleadoInformacionLaboral getItemInformacionLaboral() {
		return itemInformacionLaboral;
	}

	public void setItemInformacionLaboral(EmpleadoInformacionLaboral itemInformacionLaboral) {
		this.itemInformacionLaboral = itemInformacionLaboral;
	}

	public String prepararConsultaInformacionLaboral() {
		return "EmpleadoInformacionLaboralDetalle";
	}

	public String prepararCreacionInformacionLaboral() {
		itemInformacionLaboral = new EmpleadoInformacionLaboral();
		return "EmpleadoInformacionLaboralCrear";
	}

	public String prepararEdicionInformacionLaboral() {
		return "EmpleadoInformacionLaboralEditar";
	}

	public String crearInformacionLaboral() {

		try {
			itemInformacionLaboral.setEmpleado(itemSeleccionado);

			itemInformacionLaboral.setUsuarioCreacion(usuario());
			itemInformacionLaboral.setFechaCreacion(new Date());
			itemInformacionLaboral.setUsuarioModificacion(usuario());
			itemInformacionLaboral.setFechaModificacion(new Date());
			empleadoServicio.crearEmpleadoInformacionLaboral(itemInformacionLaboral);

			mensajeInfo("EmpleadoInformacionLaboralCreated");
			listingInformacionLaboral = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarInformacionLaboral() {

		try {
			itemInformacionLaboral.setUsuarioModificacion(usuario());
			itemInformacionLaboral.setFechaModificacion(new Date());

			empleadoServicio.actualizarEmpleadoInformacionLaboral(itemInformacionLaboral);
			mensajeInfo("EmpleadoInformacionLaboralUpdated");
			listingInformacionLaboral = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarInformacionLaboral() {

		try {

			empleadoServicio.eliminarEmpleadoInformacionLaboral(itemInformacionLaboral);
			mensajeInfo("EmpleadoInformacionLaboralDeleted");
			listingInformacionLaboral = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	// Empleado Acudiente

	public List<EmpleadoAcudiente> getListaEmpleadoAcudiente() {
		if (!listingEmpleadoAcudiente) {
			listingEmpleadoAcudiente = true;
			listaEmpleadoAcudiente = empleadoServicio.consultarEmpleadoAcudiente(itemSeleccionado.getEmpleadoid());
		}
		return listaEmpleadoAcudiente;
	}

	public void setListaEmpleadoAcudiente(List<EmpleadoAcudiente> listaEmpleadoAcudiente) {
		this.listaEmpleadoAcudiente = listaEmpleadoAcudiente;
	}

	public EmpleadoAcudiente getItemEmpleadoAcudiente() {
		return itemEmpleadoAcudiente;
	}

	public void setItemEmpleadoAcudiente(EmpleadoAcudiente itemEmpleadoAcudiente) {
		this.itemEmpleadoAcudiente = itemEmpleadoAcudiente;
	}

	public String prepararConsultaEmpleadoAcudiente() {
		return "EmpleadoAcudienteDetalle";
	}

	public String prepararCreacionEmpleadoAcudiente() {
		itemEmpleadoAcudiente = new EmpleadoAcudiente();
		tipoidentificacionid = (long) 0;
		return "EmpleadoAcudienteCrear";
	}

	public String prepararEdicionEmpleadoAcudiente() {
		tipoidentificacionid = itemEmpleadoAcudiente.getTipoIdentificacion().getTipoidentificacionid();
		return "EmpleadoAcudienteEditar";
	}

	public String crearEmpleadoAcudiente() {

		try {

			TipoIdentificacion tipoidentificacion = new TipoIdentificacion();

			tipoidentificacion.setTipoidentificacionid(tipoidentificacionid);

			String nombreApellido = itemEmpleadoAcudiente.getPrimernombre().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundonombre().trim() + ' '
					+ itemEmpleadoAcudiente.getPrimerapellido().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundoapellido().trim();

			String apellidoNombre = itemEmpleadoAcudiente.getPrimerapellido().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundoapellido().trim() + ' '
					+ itemEmpleadoAcudiente.getPrimernombre().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundonombre().trim();

			itemEmpleadoAcudiente.setApellidoNombre(apellidoNombre);
			itemEmpleadoAcudiente.setNombreApellido(nombreApellido);

			itemEmpleadoAcudiente.setUsuarioModificacion(usuario());
			itemEmpleadoAcudiente.setFechaModificacion(new Date());

			itemEmpleadoAcudiente.setTipoIdentificacion(tipoidentificacion);

			itemEmpleadoAcudiente.setEmpleado(itemSeleccionado);

			itemEmpleadoAcudiente.setUsuarioCreacion(usuario());
			itemEmpleadoAcudiente.setFechaCreacion(new Date());
			itemEmpleadoAcudiente.setUsuarioModificacion(usuario());
			itemEmpleadoAcudiente.setFechaModificacion(new Date());
			empleadoServicio.crearEmpleadoAcudiente(itemEmpleadoAcudiente);

			mensajeInfo("AcudienteCreated");
			listingEmpleadoAcudiente = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			System.out.print("Este es el error");
			System.out.print(e.getMessage());
			return null;

		}

	}

	public String actualizarEmpleadoAcudiente() {

		try {

			TipoIdentificacion tipoidentificacion = new TipoIdentificacion();

			tipoidentificacion.setTipoidentificacionid(tipoidentificacionid);

			String nombreApellido = itemEmpleadoAcudiente.getPrimernombre().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundonombre().trim() + ' '
					+ itemEmpleadoAcudiente.getPrimerapellido().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundoapellido().trim();

			String apellidoNombre = itemEmpleadoAcudiente.getPrimerapellido().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundoapellido().trim() + ' '
					+ itemEmpleadoAcudiente.getPrimernombre().trim() + ' '
					+ itemEmpleadoAcudiente.getSegundonombre().trim();

			itemEmpleadoAcudiente.setApellidoNombre(apellidoNombre);
			itemEmpleadoAcudiente.setNombreApellido(nombreApellido);

			itemEmpleadoAcudiente.setUsuarioModificacion(usuario());
			itemEmpleadoAcudiente.setFechaModificacion(new Date());

			itemEmpleadoAcudiente.setTipoIdentificacion(tipoidentificacion);

			itemEmpleadoAcudiente.setUsuarioModificacion(usuario());
			itemEmpleadoAcudiente.setFechaModificacion(new Date());

			empleadoServicio.actualizarEmpleadoAcudiente(itemEmpleadoAcudiente);
			mensajeInfo("AcudienteUpdated");
			listingEmpleadoAcudiente = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarEmpleadoAcudiente() {

		try {

			empleadoServicio.eliminarEmpleadoAcudiente(itemEmpleadoAcudiente);
			mensajeInfo("AcudienteDeleted");
			listingEmpleadoAcudiente = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	// ***********************************/
	// *******Seguridad Social ********/
	public List<EmpleadoSeguridadSocial> getListaEmpleadoSeguridadSocial() {
		if (!listingEmpleadoSeguridadSocial) {
			listingEmpleadoSeguridadSocial = true;
			listaEmpleadoSeguridadSocial = empleadoServicio
					.consultarEmpleadoSeguridadSocial(itemSeleccionado.getEmpleadoid());
		}
		return listaEmpleadoSeguridadSocial;
	}

	public void setListaEmpleadoSeguridadSocial(List<EmpleadoSeguridadSocial> listaEmpleadoSeguridadSocial) {
		this.listaEmpleadoSeguridadSocial = listaEmpleadoSeguridadSocial;
	}

	public EmpleadoSeguridadSocial getItemEmpleadoSeguridadSocial() {
		return itemEmpleadoSeguridadSocial;
	}

	public void setItemEmpleadoSeguridadSocial(EmpleadoSeguridadSocial itemEmpleadoSeguridadSocial) {
		this.itemEmpleadoSeguridadSocial = itemEmpleadoSeguridadSocial;
	}

	public String prepararConsultaEmpleadoSeguridadSocial() {
		return "EmpleadoSeguridadSocialDetalle";
	}

	public String prepararCreacionEmpleadoSeguridadSocial() {
		itemEmpleadoSeguridadSocial = new EmpleadoSeguridadSocial();
		empresaseguridadsid = (long) 0;
		return "EmpleadoSeguridadSocialCrear";
	}

	public String prepararEdicionEmpleadoSeguridadSocial() {
		return "EmpleadoSeguridadSocialEditar";
	}

	public String crearEmpleadoSeguridadSocial() {

		try {
			EmpresaSeguridadSocial empresaSeguridadSocial = new EmpresaSeguridadSocial();
			empresaSeguridadSocial.setEmpresaseguridadsocialid(empresaseguridadsid);
			itemEmpleadoSeguridadSocial.setEmpresaseguridadSocial(empresaSeguridadSocial);
			itemEmpleadoSeguridadSocial.setEmpleado(itemSeleccionado);

			itemEmpleadoSeguridadSocial.setUsuarioCreacion(usuario());
			itemEmpleadoSeguridadSocial.setFechaCreacion(new Date());
			itemEmpleadoSeguridadSocial.setUsuarioModificacion(usuario());
			itemEmpleadoSeguridadSocial.setFechaModificacion(new Date());
			empleadoServicio.crearEmpleadoSeguridadSocial(itemEmpleadoSeguridadSocial);

			mensajeInfo("EmpleadoSeguridadSocialCreated");
			listingEmpleadoSeguridadSocial = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarEmpleadoSeguridadSocial() {

		try {
			itemEmpleadoSeguridadSocial.setUsuarioModificacion(usuario());
			itemEmpleadoSeguridadSocial.setFechaModificacion(new Date());

			empleadoServicio.actualizarEmpleadoSeguridadSocial(itemEmpleadoSeguridadSocial);
			mensajeInfo("EmpleadoSeguridadSocialUpdated");
			listingEmpleadoSeguridadSocial = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarEmpleadoSeguridadSocial() {

		try {

			empleadoServicio.eliminarEmpleadoSeguridadSocial(itemEmpleadoSeguridadSocial);
			mensajeInfo("EmpleadoSeguridadSocialDeleted");
			listingEmpleadoSeguridadSocial = false;
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public Long getEmpresaseguridadsid() {
		return empresaseguridadsid;
	}

	public void setempresaseguridadsid(Long empresaseguridadsid) {
		this.empresaseguridadsid = empresaseguridadsid;
	}

	public List<SelectItem> getEmpresaSeguridadSocialItems() {
		if (!listingEmpresaseguridadsocial) {
			listingEmpresaseguridadsocial = true;

			empresaSeguridadSocialItems = new ArrayList<SelectItem>();
			List<EmpresaSeguridadSocial> EmpresaSeguridadSocialList = empleadoServicio
					.consultarEmpresaSeguridadSocial();

			for (EmpresaSeguridadSocial ssocial : EmpresaSeguridadSocialList) {
				empresaSeguridadSocialItems.add(new SelectItem(ssocial.getEmpresaseguridadsocialid(),
						ssocial.getEmpresaseguridadsocialnombre()));

			}
		}
		return empresaSeguridadSocialItems;
	}

	public void setEmpresaSeguridadSocial(List<SelectItem> empresaSeguridadSocialItems) {
		this.empresaSeguridadSocialItems = empresaSeguridadSocialItems;
	}
}
