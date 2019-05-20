package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.TareaEstadoService;
import com.guandera.proyecto.server.service.TareaEstadoServiceImpl;
import com.guandera.proyecto.shared.model.TareaEstado;

@ManagedBean(name = "tareaEstadoMB")
@SessionScoped
public class TareaEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TareaEstadoService tareaEstadoServicio;
	private TareaEstado itemSeleccionado;
	private List<TareaEstado> listaItems;
	private boolean listing = false;

	public TareaEstadoManagedBean() {
		tareaEstadoServicio = new TareaEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tareaEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("TareaEstadoUpdated");
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

			tareaEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("TareaEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tareaEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TareaEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TareaEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = tareaEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public TareaEstadoService getTareaEstadoServicio() {
		return tareaEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TareaEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTareaEstado() {
		setListing(true);
		listaItems = tareaEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TareaEstado();
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

	public void setItemSeleccionado(TareaEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TareaEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTareaEstadoServicio(TareaEstadoService tareaEstadoServicio) {
		this.tareaEstadoServicio = tareaEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tareaEstadoServicio.consultarTodos();
		}
	}
}
