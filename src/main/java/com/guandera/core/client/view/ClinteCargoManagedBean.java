package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.ClienteCargoService;
import com.guandera.core.server.service.ClienteCargoServiceImpl;
import com.guandera.core.shared.model.ClienteCargo;

@ManagedBean(name = "clienteCargoMB")
@SessionScoped
public class ClinteCargoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ClienteCargoService clienteCargoServicio;
	private ClienteCargo itemSeleccionado;
	private List<ClienteCargo> listaItems;
	private boolean listing = false;

	public ClinteCargoManagedBean() {
		clienteCargoServicio = new ClienteCargoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			clienteCargoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ClienteCargoUpdated");
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

			clienteCargoServicio.crear(itemSeleccionado);
			mensajeInfo("ClienteCargoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		clienteCargoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ClienteCargo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ClienteCargo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = clienteCargoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ClienteCargoService getClienteCargoServicio() {
		return clienteCargoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ClienteCargo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarClienteCargo() {
		setListing(true);
		listaItems = clienteCargoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ClienteCargo();
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

	public void setItemSeleccionado(ClienteCargo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ClienteCargo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setClienteCargoServicio(ClienteCargoService clienteCargoServicio) {
		this.clienteCargoServicio = clienteCargoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = clienteCargoServicio.consultarTodos();
		}
	}
}
