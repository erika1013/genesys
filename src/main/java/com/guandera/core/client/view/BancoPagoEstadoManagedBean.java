package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.BancoPagoEstadoService;
import com.guandera.core.server.service.BancoPagoEstadoServiceImpl;
import com.guandera.core.shared.model.BancoPagoEstado;

@ManagedBean(name = "bancoPagoEstadoMB")
@SessionScoped
public class BancoPagoEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private BancoPagoEstadoService bancoPagoEstadoServicio;
	private BancoPagoEstado itemSeleccionado;
	private List<BancoPagoEstado> listaItems;
	private boolean listing = false;

	public BancoPagoEstadoManagedBean() {
		bancoPagoEstadoServicio = new BancoPagoEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			bancoPagoEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("BancoPagoEstadoUpdated");
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

			bancoPagoEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("BancoPagoEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		bancoPagoEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public BancoPagoEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<BancoPagoEstado> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = bancoPagoEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public BancoPagoEstadoService getBancoPagoEstadoServicio() {
		return bancoPagoEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new BancoPagoEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarBancoPagoEstado() {
		setListing(true);
		listaItems = bancoPagoEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new BancoPagoEstado();
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

	public void setItemSeleccionado(BancoPagoEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<BancoPagoEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setBancoPagoEstadoServicio(BancoPagoEstadoService bancoPagoEstadoServicio) {
		this.bancoPagoEstadoServicio = bancoPagoEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = bancoPagoEstadoServicio.consultarTodos();
		}
	}
}
