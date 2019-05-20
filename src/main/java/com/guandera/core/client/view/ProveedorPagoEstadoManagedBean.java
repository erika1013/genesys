package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.ProveedorPagoEstadoService;
import com.guandera.core.server.service.ProveedorPagoEstadoServiceImpl;
import com.guandera.core.shared.model.ProveedorPagoEstado;

@ManagedBean(name = "proveedorPagoEstadoMB")
@SessionScoped
public class ProveedorPagoEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProveedorPagoEstadoService proveedorPagoEstadoServicio;
	private ProveedorPagoEstado itemSeleccionado;
	private List<ProveedorPagoEstado> listaItems;

	private boolean listing = false;

	public ProveedorPagoEstadoManagedBean() {
		proveedorPagoEstadoServicio = new ProveedorPagoEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proveedorPagoEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProveedorPagoEstadoUpdated");
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

			proveedorPagoEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("ProveedorPagoEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		proveedorPagoEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ProveedorPagoEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ProveedorPagoEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = proveedorPagoEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ProveedorPagoEstadoService getProveedorPagoEstadoServicio() {
		return proveedorPagoEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ProveedorPagoEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProveedorPagoEstado() {
		setListing(true);
		listaItems = proveedorPagoEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ProveedorPagoEstado();
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

	public void setItemSeleccionado(ProveedorPagoEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ProveedorPagoEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProveedorPagoEstadoServicio(ProveedorPagoEstadoService proveedorPagoEstadoServicio) {
		this.proveedorPagoEstadoServicio = proveedorPagoEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = proveedorPagoEstadoServicio.consultarTodos();
		}
	}
}
