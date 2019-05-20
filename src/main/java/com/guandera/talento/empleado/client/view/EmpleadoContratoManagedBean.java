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
import com.guandera.talento.empleado.client.service.EmpleadoContratoService;
import com.guandera.talento.empleado.server.service.EmpleadoContratoServiceImpl;
import com.guandera.talento.empleado.shared.model.Empleado;
import com.guandera.talento.empleado.shared.model.EmpleadoContrato;
import com.guandera.talento.empleado.shared.model.EmpleadoContratoDetalle;
import com.guandera.talento.empleado.shared.model.TipoContrato;
import com.guandera.talento.nomina.shared.model.NominaConcepto;
import com.guandera.talento.nomina.shared.model.NominaNovedad;

@ManagedBean(name = "empleadoContratoMB")
@SessionScoped
public class EmpleadoContratoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private EmpleadoContratoService empleadoContratoServicio;
	private EmpleadoContrato itemSeleccionado;
	private List<EmpleadoContrato> listaItems = new ArrayList<EmpleadoContrato>();
	private boolean listing = false;

	private List<Empleado> listaEmpleados = new ArrayList<Empleado>();
	private Empleado empleadoSeleccionado;
	private boolean listingEmpleado = false;

	private EmpleadoContratoDetalle itemDetalleContrato;
	private List<EmpleadoContratoDetalle> listaDetalleContratoItems = new ArrayList<EmpleadoContratoDetalle>();

	private boolean listingEmpleadoContrato = false;

	private Long tipoContratoid;
	private List<SelectItem> tipoContratoItems;
	private boolean listingContrato;

	private Long empleadoid;
	private List<SelectItem> empleadoItems;
	private boolean listingEmpleadoItem = false;

	private Long nominaConceptoId;

	private List<SelectItem> nominaConceptoItems;
	private boolean listingNomina = false;
	private NominaNovedad itemNominaNovedad;

	private List<NominaNovedad> listaNominaNovedadItems = new ArrayList<NominaNovedad>();
	private boolean listingNominaNovedad = false;

	public EmpleadoContratoManagedBean() {
		empleadoContratoServicio = new EmpleadoContratoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			empleadoContratoServicio.actualizar(itemSeleccionado);
			mensajeInfo("EmpleadoContratoUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		if (empleadoSeleccionado.getEstado().getEstadoId() == 1L
				&& empleadoSeleccionado.isContratoEmpleado() == false) {
			try {

				TipoContrato tipoContrato = new TipoContrato();

				tipoContrato.setTipocontratoid(tipoContratoid);

				itemSeleccionado.setUsuarioCreacion(usuario());
				itemSeleccionado.setNumeroContrato(empleadoContratoServicio.siguienteRegistro());
				itemSeleccionado.setFechaCreacion(new Date());
				itemSeleccionado.setUsuarioModificacion(usuario());
				itemSeleccionado.setFechaModificacion(new Date());

				itemSeleccionado.setTipoContrato(tipoContrato);
				itemSeleccionado.setEmpleado(empleadoSeleccionado);
				empleadoSeleccionado.setContratoEmpleado(true);

				if (itemSeleccionado.getEmpleadocontratofechainicial() != null
						|| itemSeleccionado.getContratosalario() != null) {
					empleadoContratoServicio.crear(itemSeleccionado);
					empleadoContratoServicio.actualizarestadoempleado(empleadoSeleccionado);
					mensajeInfo("EmpleadoContratoCreated");
				} else {
					mensaje("Datos incompletos");
				}
				inicializar();
				verificarLista();
				return prepararLista();
			} catch (Exception e) {
				mensajeError("PersistenceErrorOccured");
				e.getMessage();
				return null;
			}
		} else {
			mensaje("�El empleado seleccionado ya tiene contratono o no se encuentra activo!");
			return prepararLista();
		}
	}

	public String eliminar() {

		empleadoContratoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public EmpleadoContrato getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<EmpleadoContrato> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = empleadoContratoServicio.consultarTodos();

		}
		return listaItems;
	}

	public EmpleadoContratoService getEmpleadoContratoServicio() {
		return empleadoContratoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new EmpleadoContrato();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarEmpleadoContrato() {
		setListing(true);
		listaItems = empleadoContratoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		listingEmpleadoContrato = false;
		listingNominaNovedad = false;
		return "Detalle";
	}

	public String prepararCreacionContrato() {
		empleadoSeleccionado = new Empleado();

		return "CrearBuscar";

	}

	public String prepararCreacion() {
		itemSeleccionado = new EmpleadoContrato();
		tipoContratoid = (long) 0;
		empleadoid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {
		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(EmpleadoContrato itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<EmpleadoContrato> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setEmpleadoContratoServicio(EmpleadoContratoService empleadoContratoServicio) {
		this.empleadoContratoServicio = empleadoContratoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = empleadoContratoServicio.consultarTodos();
		}
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
			List<TipoContrato> tipoContratoList = empleadoContratoServicio.consultarTipocontratos();

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

	public Long getEmpleadoid() {
		return empleadoid;
	}

	public void setEmpleadoid(Long empleadoid) {
		this.empleadoid = empleadoid;
	}

	public List<SelectItem> getEmpleadoItems() {
		if (!listingEmpleadoItem) {
			listingEmpleadoItem = true;
			empleadoItems = new ArrayList<SelectItem>();
			List<Empleado> empleadoList = empleadoContratoServicio.consultarEmpleado();

			for (Empleado empleado : empleadoList) {
				empleadoItems.add(new SelectItem(empleado.getEmpleadoid(), empleado.getPrimerNombre()));

			}
		}

		return empleadoItems;
	}

	public void setEmpleadoItems(List<SelectItem> empleadoItems) {
		this.empleadoItems = empleadoItems;
	}



	public Long getNominaConceptoId() {
		return nominaConceptoId;
	}

	public void setNominaConceptoId(Long nominaConceptoId) {
		this.nominaConceptoId = nominaConceptoId;
	}

	public List<SelectItem> getNominaConceptoItems() {
		if (!listingNomina) {
			listingNomina = true;
			nominaConceptoItems = new ArrayList<SelectItem>();
			List<NominaConcepto> nominaConceptoList = empleadoContratoServicio.consultarNominaConcepto();

			for (NominaConcepto nominaConcepto : nominaConceptoList) {
				nominaConceptoItems.add(
						new SelectItem(nominaConcepto.getNominaconceptoid(), nominaConcepto.getNominaconceptonombre()));

			}
		}

		return nominaConceptoItems;
	}

	public void setNominaConceptoItems(List<SelectItem> nominaConceptoItems) {
		this.nominaConceptoItems = nominaConceptoItems;
	}

	// detalle contrato

	public EmpleadoContratoDetalle getItemDetalleContrato() {
		return itemDetalleContrato;
	}

	public void setItemDetalleContrato(EmpleadoContratoDetalle itemDetalleContrato) {
		this.itemDetalleContrato = itemDetalleContrato;
	}

	public List<EmpleadoContratoDetalle> getListaDetalleContratoItems() {
		if (!listingEmpleadoContrato) {
			listingEmpleadoContrato = true;
			listaDetalleContratoItems = empleadoContratoServicio
					.consultarDetalleContrato(itemSeleccionado.getEmpleadocontratoid());
			// Aqui
		}
		return listaDetalleContratoItems;
	}

	public void setListaDetalleContratoItems(List<EmpleadoContratoDetalle> listaDetalleContratoItems) {
		this.listaDetalleContratoItems = listaDetalleContratoItems;
	}

	public String prepararConsultaDetalleContrato() {
		return "DetalleContratoDetalle";
	}

	public String prepararCreacionDetalleContrato() {
		itemDetalleContrato = new EmpleadoContratoDetalle();
		nominaConceptoId = (long) 0;
		return "DetalleContratoCrear";
	}

	public String prepararEdicionDetalleContrato() {
		return "DetalleContratoEditar";
	}

	public String prepararListaDetalleContrato() {
		return "DetalleContratoLista";
	}

	public void cargarObligatorios() {
		List<NominaConcepto> nominaConceptoList = empleadoContratoServicio.consultarNominaConcepto();

		for (NominaConcepto nominaConcepto : nominaConceptoList) {
			if (nominaConcepto.getConceptoTipo().isObligatorio() == true) {

				if (nominaConcepto.getNominaconceptofactor() == true) {
					itemDetalleContrato
							.setValor(itemSeleccionado.getContratosalario() * nominaConcepto.getNominaconceptoValor());
				} else {
					itemDetalleContrato.setValor(nominaConcepto.getNominaconceptoValor());
				}
				itemDetalleContrato.setEmpleadoContrato(itemSeleccionado);
				itemDetalleContrato.setNominaConcepto(nominaConcepto);

				itemDetalleContrato.setUsuarioCreacion(usuario());
				itemDetalleContrato.setFechaCreacion(new Date());
				itemDetalleContrato.setUsuarioModificacion(usuario());
				itemDetalleContrato.setFechaModificacion(new Date());
				empleadoContratoServicio.crearDetalleContrato(itemDetalleContrato);

			}
		}
	}

	public String crearDetalleContrato() {

		try {

			NominaConcepto nominaConceptoC = new NominaConcepto();
			nominaConceptoC.setNominaconceptoid(nominaConceptoId);
		
			itemDetalleContrato.setEmpleadoContrato(itemSeleccionado);
			itemDetalleContrato.setNominaConcepto(nominaConceptoC);

			itemDetalleContrato.setUsuarioCreacion(usuario());
			itemDetalleContrato.setFechaCreacion(new Date());
			itemDetalleContrato.setUsuarioModificacion(usuario());
			itemDetalleContrato.setFechaModificacion(new Date());

			empleadoContratoServicio.crearDetalleContrato(itemDetalleContrato);

			mensajeInfo("DetalleContratoCreated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String actualizarDetalleContrato() {

		try {
			NominaConcepto nominaConceptoC = new NominaConcepto();
			nominaConceptoC.setNominaconceptoid(nominaConceptoId);			
			itemDetalleContrato.setUsuarioModificacion(usuario());
			itemDetalleContrato.setFechaModificacion(new Date());
			empleadoContratoServicio.actualizarDetalleContrato(itemDetalleContrato);

			mensajeInfo("DetalleContratoUpdated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarDetalleContrato() {

		try {

			empleadoContratoServicio.eliminarDetalleContrato(itemDetalleContrato);

			mensajeInfo("DetalleContratoDeleted");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public List<Empleado> getListaEmpleados() {
		if (!listingEmpleado) {

			listingEmpleado = true;
			listaEmpleados = empleadoContratoServicio.consultarEmpleado();
		}
		return listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}

	public Empleado getEmpleadoSeleccionado() {
		return empleadoSeleccionado;
	}

	public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
		this.empleadoSeleccionado = empleadoSeleccionado;
	}

	// Nomina Novedad

	public NominaNovedad getItemNominaNovedad() {
		return itemNominaNovedad;
	}

	public void setItemNominaNovedad(NominaNovedad itemNominaNovedad) {
		this.itemNominaNovedad = itemNominaNovedad;
	}

	public List<NominaNovedad> getListaNominaNovedadItems() {
		if (!listingNominaNovedad) {
			listingNominaNovedad = true;
			listaNominaNovedadItems = empleadoContratoServicio
					.consultarNominaNovedad(itemSeleccionado.getEmpleadocontratoid());
		}
		return listaNominaNovedadItems;
	}

	public void setListaNominaNovedadItems(List<NominaNovedad> listaNominaNovedadItems) {
		this.listaNominaNovedadItems = listaNominaNovedadItems;
	}

	public String prepararConsultaNominaNovedad() {
		return "NominaNovedadDetalle";
	}

	public String prepararCreacionNominaNovedad() {
		itemNominaNovedad = new NominaNovedad();
		return "NominaNovedadCrear";
	}

	public String prepararEdicionNominaNovedad() {
		return "NominaNovedadEditar";
	}

	public String prepararListaNominaNovedad() {
		return "NominaNovedadLista";
	}

	public String crearNominaNovedad() {

		try {
			itemNominaNovedad.setUsuarioCreacion(usuario());
			itemNominaNovedad.setFechaCreacion(new Date());
			itemNominaNovedad.setUsuarioModificacion(usuario());
			itemNominaNovedad.setFechaModificacion(new Date());

			itemNominaNovedad.setPeriodo(itemNominaNovedad.getAno() + "" + itemNominaNovedad.getMes());
			itemNominaNovedad.setEmpleadoContrato(itemSeleccionado);
			empleadoContratoServicio.crearNominaNovedad(itemNominaNovedad);
			mensajeInfo("NominaNovedadCreated");
			return prepararConsulta();
		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizarNominaNovedad() {

		try {
			itemNominaNovedad.setUsuarioCreacion(usuario());
			itemNominaNovedad.setFechaCreacion(new Date());
			itemNominaNovedad.setUsuarioModificacion(usuario());
			itemNominaNovedad.setFechaModificacion(new Date());
			itemNominaNovedad.setEmpleadoContrato(itemSeleccionado);

			empleadoContratoServicio.actualizarNominaNovedad(itemNominaNovedad);

			mensajeInfo("NominaNovedadUpdated");
			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

	public String eliminarNominaNovedad() {

		try {

			empleadoContratoServicio.eliminarNominaNovedad(itemNominaNovedad);
			listingNominaNovedad = false;

			return prepararConsulta();

		} catch (Exception e) {

			mensajeError("PersistenceErrorOccured");
			return null;

		}

	}

}