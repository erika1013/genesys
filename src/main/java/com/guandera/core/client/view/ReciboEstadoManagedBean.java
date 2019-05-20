package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.ReciboEstadoService;
import com.guandera.core.server.service.ReciboEstadoServiceImpl;
import com.guandera.core.shared.model.ReciboEstado;

@ManagedBean(name = "reciboEstadoMB")
@SessionScoped
public class ReciboEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ReciboEstadoService reciboEstadoServicio;
	private ReciboEstado itemSeleccionado;
	private List<ReciboEstado> listaItems;
	private boolean listing = false;

	public ReciboEstadoManagedBean() {
		reciboEstadoServicio = new ReciboEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			reciboEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ReciboEstadoUpdated");
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

			reciboEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("ReciboEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		reciboEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ReciboEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ReciboEstado> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = reciboEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ReciboEstadoService getReciboEstadoServicio() {
		return reciboEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ReciboEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarReciboEstado() {
		setListing(true);
		listaItems = reciboEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ReciboEstado();
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

	public void setItemSeleccionado(ReciboEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ReciboEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setReciboEstadoServicio(ReciboEstadoService reciboEstadoServicio) {
		this.reciboEstadoServicio = reciboEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = reciboEstadoServicio.consultarTodos();
		}
	}
}
