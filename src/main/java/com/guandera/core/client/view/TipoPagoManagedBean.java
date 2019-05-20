package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.TipoPagoService;
import com.guandera.core.server.service.TipoPagoServiceImpl;
import com.guandera.core.shared.model.TipoPago;

@ManagedBean(name = "tipoPagoMB")
@SessionScoped
public class TipoPagoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoPagoService tipoPagoServicio;
	private TipoPago itemSeleccionado;
	private List<TipoPago> listaItems;
	private boolean listing = false;

	public TipoPagoManagedBean() {
		tipoPagoServicio = new TipoPagoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tipoPagoServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoPagoUpdated");
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

			tipoPagoServicio.crear(itemSeleccionado);
			mensajeInfo("TipoPagoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoPagoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoPago getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoPago> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = tipoPagoServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoPagoService getTipoPagoServicio() {
		return tipoPagoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoPago();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoPago() {
		setListing(true);
		listaItems = tipoPagoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoPago();
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

	public void setItemSeleccionado(TipoPago itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoPago> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoPagoServicio(TipoPagoService tipoPagoServicio) {
		this.tipoPagoServicio = tipoPagoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoPagoServicio.consultarTodos();
		}
	}
}
