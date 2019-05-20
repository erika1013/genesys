package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.CobroEstadoService;
import com.guandera.core.server.service.CobroEstadoServiceImpl;
import com.guandera.core.shared.model.CobroEstado;

@ManagedBean(name = "cobroEstadoMB")
@SessionScoped
public class CobroEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CobroEstadoService cobroEstadoServicio;
	private CobroEstado itemSeleccionado;
	private List<CobroEstado> listaItems;
	private boolean listing = false;

	public CobroEstadoManagedBean() {
		cobroEstadoServicio = new CobroEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			cobroEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("CobroEstadoUpdated");
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

			cobroEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("CobroEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		cobroEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public CobroEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<CobroEstado> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = cobroEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public CobroEstadoService getCobroEstadoServicio() {
		return cobroEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new CobroEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCobroEstado() {
		setListing(true);
		listaItems = cobroEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CobroEstado();
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

	public void setItemSeleccionado(CobroEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<CobroEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCobroEstadoServicio(CobroEstadoService cobroEstadoServicio) {
		this.cobroEstadoServicio = cobroEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = cobroEstadoServicio.consultarTodos();
		}
	}
}
