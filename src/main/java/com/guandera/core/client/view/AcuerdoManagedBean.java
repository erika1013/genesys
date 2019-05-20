package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.AcuerdoService;
import com.guandera.core.server.service.AcuerdoServiceImpl;
import com.guandera.core.shared.model.Acuerdo;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Servicio;
import com.guandera.core.shared.model.Cliente;

@ManagedBean(name = "acuerdoMB")
@SessionScoped
public class AcuerdoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private AcuerdoService acuerdoServicio;
	private Acuerdo itemSeleccionado;
	private List<Acuerdo> listaItems;
	private boolean listing = false;

	private Long clienteid;
	private List<SelectItem> clienteItems;
	private boolean listingCliente = false;

	private Long companiaid;
	private List<SelectItem> companiaItems;
	private boolean listingCompania = false;

	private Long servicioid;
	private List<SelectItem> servicioItems;

	public AcuerdoManagedBean() {
		acuerdoServicio = new AcuerdoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			Compania compania = new Compania();
			Cliente cliente = new Cliente();
			Servicio servicio = new Servicio();

			compania.setCompaniaid(companiaid);
			servicio.setServicioid(servicioid);
			cliente.setClienteid(clienteid);

			itemSeleccionado.setCompania(compania);
			itemSeleccionado.setServicio(servicio);
			itemSeleccionado.setCliente(cliente);

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			acuerdoServicio.actualizar(itemSeleccionado);

			mensajeInfo("AcuerdoUpdated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String crear() {

		listing = false;

		try {
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			Compania compania = new Compania();
			Cliente cliente = new Cliente();
			Servicio servicio = new Servicio();

			compania.setCompaniaid(companiaid);
			servicio.setServicioid(servicioid);
			cliente.setClienteid(clienteid);

			itemSeleccionado.setCompania(compania);
			itemSeleccionado.setServicio(servicio);
			itemSeleccionado.setCliente(cliente);

			acuerdoServicio.crear(itemSeleccionado);
			mensajeInfo("AcuerdoCreated");

			itemSeleccionado = new Acuerdo();

			return null;

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		acuerdoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Acuerdo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Acuerdo> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = acuerdoServicio.consultarTodos();
		}
		return listaItems;
	}

	public AcuerdoService getAcuerdoAcuerdo() {
		return acuerdoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Acuerdo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarAcuerdo() {
		setListing(true);
		listaItems = acuerdoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Acuerdo();
		return "Crear";
	}

	public String prepararEdicion() {

		companiaid = itemSeleccionado.getCompania().getCompaniaid();
		servicioid = itemSeleccionado.getServicio().getServicioid();
		clienteid = itemSeleccionado.getCliente().getClienteid();

		return "Editar";
	}

	public String prepararLista() {

		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(Acuerdo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Acuerdo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setAcuerdoAcuerdo(AcuerdoService acuerdoServicio) {
		this.acuerdoServicio = acuerdoServicio;
	}

	private void verificarLista() {
		if (!listing) {
			this.listaItems = acuerdoServicio.consultarTodos();
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
			List<Cliente> clienteList = acuerdoServicio.consultarClientes();

			for (Cliente cliente : clienteList) {
				clienteItems.add(new SelectItem(cliente.getClienteid(), cliente.getClientenombre()));

			}
		}

		return clienteItems;
	}

	public void setClienteItems(List<SelectItem> clienteItems) {
		this.clienteItems = clienteItems;
	}

	public Long getCompaniaid() {
		return companiaid;
	}

	public void setCompaniaid(Long companiaid) {
		this.companiaid = companiaid;
	}

	public List<SelectItem> getCompaniaItems() {
		if (!listingCompania) {
			listingCompania = true;
			companiaItems = new ArrayList<SelectItem>();
			List<Compania> companiaList = acuerdoServicio.consultarCompanias();

			for (Compania compania : companiaList) {
				companiaItems.add(new SelectItem(compania.getCompaniaid(), compania.getCompanianombre()));
			}
		}

		return companiaItems;
	}

	public void setCompaniaItems(List<SelectItem> companiaItems) {
		this.companiaItems = companiaItems;
	}

	public Long getServicioid() {
		return servicioid;
	}

	public void setServicioid(Long servicioid) {
		this.servicioid = servicioid;
	}

	public List<SelectItem> getServicioItems() {

		return servicioItems;
	}

	public void setServicioItems(List<SelectItem> servicioItems) {
		this.servicioItems = servicioItems;
	}

	public void cargarServicios() {

		if (companiaid != 0) {

			servicioItems = new ArrayList<SelectItem>();
			List<Servicio> servicioList = acuerdoServicio.consultarServicios(companiaid);

			for (Servicio servicio : servicioList) {
				servicioItems.add(new SelectItem(servicio.getServicioid(), servicio.getNombre()));
			}
		}

	}

}
