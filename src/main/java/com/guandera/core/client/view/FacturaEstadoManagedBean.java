package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.FacturaEstadoService;
import com.guandera.core.server.service.FacturaEstadoServiceImpl;
import com.guandera.core.shared.model.FacturaEstado;

@ManagedBean(name = "facturaEstadoMB")
@SessionScoped
public class FacturaEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private FacturaEstadoService facturaEstadoServicio;
	private FacturaEstado itemSeleccionado;
	private List<FacturaEstado> listaItems;
	private boolean listing = false;

	public FacturaEstadoManagedBean() {
		facturaEstadoServicio = new FacturaEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			facturaEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("FacturaEstadoUpdated");
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

			facturaEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("FacturaEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		facturaEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public FacturaEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<FacturaEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = facturaEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public FacturaEstadoService getFacturaEstadoServicio() {
		return facturaEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new FacturaEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarFacturaEstado() {
		setListing(true);
		listaItems = facturaEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new FacturaEstado();
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

	public void setItemSeleccionado(FacturaEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<FacturaEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setFacturaEstadoServicio(FacturaEstadoService facturaEstadoServicio) {
		this.facturaEstadoServicio = facturaEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = facturaEstadoServicio.consultarTodos();
		}
	}
}
