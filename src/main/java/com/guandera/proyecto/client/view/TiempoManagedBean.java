package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.TiempoService;
import com.guandera.proyecto.server.service.TiempoServiceImpl;
import com.guandera.proyecto.shared.model.Tiempo;

@ManagedBean(name = "tiempoMB")
@SessionScoped
public class TiempoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TiempoService tiempoServicio;
	private Tiempo itemSeleccionado;
	private List<Tiempo> listaItems;
	private boolean listing = false;

	public TiempoManagedBean() {
		tiempoServicio = new TiempoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tiempoServicio.actualizar(itemSeleccionado);
			mensajeInfo("TiempoUpdated");
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

			tiempoServicio.crear(itemSeleccionado);
			mensajeInfo("TiempoCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tiempoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public Tiempo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<Tiempo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = tiempoServicio.consultarTodos();
		}
		return listaItems;
	}

	public TiempoService getTiempoServicio() {
		return tiempoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new Tiempo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTiempo() {
		setListing(true);
		listaItems = tiempoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Tiempo();
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

	public void setItemSeleccionado(Tiempo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<Tiempo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTiempoServicio(TiempoService tiempoServicio) {
		this.tiempoServicio = tiempoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tiempoServicio.consultarTodos();
		}
	}
}
