package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.CompaniaCargoService;
import com.guandera.core.server.service.CompaniaCargoServiceImpl;
import com.guandera.core.shared.model.CompaniaCargo;

@ManagedBean(name = "companiaCargoMB")
@SessionScoped
public class CompaniaCargoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CompaniaCargoService companiaCargoServicio;
	private CompaniaCargo itemSeleccionado;
	private List<CompaniaCargo> listaItems;
	private boolean listing = false;

	public CompaniaCargoManagedBean() {
		companiaCargoServicio = new CompaniaCargoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			companiaCargoServicio.actualizar(itemSeleccionado);
			mensajeInfo("CompaniaCargoUpdated");

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
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());
			companiaCargoServicio.crear(itemSeleccionado);
			mensajeInfo("CompaniaCargoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		companiaCargoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public CompaniaCargo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<CompaniaCargo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = companiaCargoServicio.consultarTodos();
		}
		return listaItems;
	}

	public CompaniaCargoService getCompaniaCargoServicio() {
		return companiaCargoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new CompaniaCargo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCompaniaCargo() {
		setListing(true);
		listaItems = companiaCargoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CompaniaCargo();
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

	public void setItemSeleccionado(CompaniaCargo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<CompaniaCargo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCompaniaCargoServicio(CompaniaCargoService companiaCargoServicio) {
		this.companiaCargoServicio = companiaCargoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = companiaCargoServicio.consultarTodos();
		}
	}
}
