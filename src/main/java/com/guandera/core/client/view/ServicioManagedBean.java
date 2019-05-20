package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.ServicioService;
import com.guandera.core.server.service.ServicioServiceImpl;
import com.guandera.core.shared.model.Compania;
import com.guandera.core.shared.model.Servicio;

@ManagedBean(name = "servicioMB")
@SessionScoped
public class ServicioManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ServicioService servicioServicio;
	private Servicio itemSeleccionado;
	private List<Servicio> listaItems;
	private boolean listing = false;

	private Long companiaid;
	private List<SelectItem> companiaItems;
	private boolean listingCompania = false;

	public ServicioManagedBean() {
		servicioServicio = new ServicioServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicioServicio.actualizar(itemSeleccionado);
			mensajeInfo("ServicioUpdated");
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

			Compania compania = new Compania();
			compania.setCompaniaid(companiaid);

			itemSeleccionado.setCompania(compania);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicioServicio.crear(itemSeleccionado);
			mensajeInfo("ServicioCreated");
			inicializar();
			verificarLista();
			listing = false;
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		servicioServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Servicio getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Servicio> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = servicioServicio.consultarTodos();
		}
		return listaItems;
	}

	public ServicioService getServicioServicio() {
		return servicioServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Servicio();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarServicio() {

		listaItems = servicioServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Servicio();

		itemSeleccionado.setCodigo(servicioServicio.siguienteRegistro());
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

	public void setItemSeleccionado(Servicio itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Servicio> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setServicioServicio(ServicioService servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = servicioServicio.consultarTodos();
		}
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
			List<Compania> companiaList = servicioServicio.consultarCompanias();

			for (Compania compania : companiaList) {
				companiaItems.add(new SelectItem(compania.getCompaniaid(), compania.getCompanianombre()));

			}
		}

		return companiaItems;
	}

	public void setCompaniaItems(List<SelectItem> companiaItems) {
		this.companiaItems = companiaItems;
	}

}
