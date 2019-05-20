package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.AsignacionEstadoService;
import com.guandera.proyecto.server.service.AsignacionEstadoServiceImpl;
import com.guandera.proyecto.shared.model.AsignacionEstado;

@ManagedBean(name = "asignacionEstadoMB")
@SessionScoped
public class AsignacionEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private AsignacionEstadoService asignacionEstadoServicio;
	private AsignacionEstado itemSeleccionado;
	private List<AsignacionEstado> listaItems;
	private boolean listing = false;

	public AsignacionEstadoManagedBean() {
		asignacionEstadoServicio = new AsignacionEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			asignacionEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("AsignacionEstadoUpdated");
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

			asignacionEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("AsignacionEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		asignacionEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public AsignacionEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<AsignacionEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = asignacionEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public AsignacionEstadoService getAsignacionEstadoServicio() {
		return asignacionEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new AsignacionEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarAsignacionEstado() {
		setListing(true);
		listaItems = asignacionEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new AsignacionEstado();
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

	public void setItemSeleccionado(AsignacionEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<AsignacionEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setAsignacionEstadoServicio(AsignacionEstadoService asignacionEstadoServicio) {
		this.asignacionEstadoServicio = asignacionEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = asignacionEstadoServicio.consultarTodos();
		}
	}
}
