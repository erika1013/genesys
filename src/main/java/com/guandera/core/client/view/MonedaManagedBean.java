package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.MonedaService;
import com.guandera.core.server.service.MonedaServiceImpl;
import com.guandera.core.shared.model.Moneda;

@ManagedBean(name = "monedaMB")
@SessionScoped
public class MonedaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private MonedaService monedaServicio;
	private Moneda itemSeleccionado;
	private List<Moneda> listaItems;
	private boolean listing = false;

	public MonedaManagedBean() {
		monedaServicio = new MonedaServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			monedaServicio.actualizar(itemSeleccionado);
			mensajeInfo("MonedaUpdated");
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

			monedaServicio.crear(itemSeleccionado);
			mensajeInfo("MonedaCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		monedaServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Moneda getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Moneda> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = monedaServicio.consultarTodos();
		}
		return listaItems;
	}

	public MonedaService getMonedaServicio() {
		return monedaServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Moneda();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarMoneda() {
		setListing(true);
		listaItems = monedaServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Moneda();
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

	public void setItemSeleccionado(Moneda itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Moneda> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setMonedaServicio(MonedaService monedaServicio) {
		this.monedaServicio = monedaServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = monedaServicio.consultarTodos();
		}
	}
}
