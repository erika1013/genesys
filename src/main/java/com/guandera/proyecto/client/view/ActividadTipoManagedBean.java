package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.ActividadTipoService;
import com.guandera.proyecto.server.service.ActividadTipoServiceImpl;
import com.guandera.proyecto.shared.model.ActividadTipo;

@ManagedBean(name = "actividadTipoMB")
@SessionScoped
public class ActividadTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ActividadTipoService actividadTipoServicio;
	private ActividadTipo itemSeleccionado;
	private List<ActividadTipo> listaItems;
	private boolean listing = false;

	public ActividadTipoManagedBean() {
		actividadTipoServicio = new ActividadTipoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			actividadTipoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ActividadTipoUpdated");
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

			actividadTipoServicio.crear(itemSeleccionado);
			mensajeInfo("ActividadTipoCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		actividadTipoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ActividadTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ActividadTipo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = actividadTipoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ActividadTipoService getActividadTipoServicio() {
		return actividadTipoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ActividadTipo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarActividadTipo() {
		setListing(true);
		listaItems = actividadTipoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ActividadTipo();
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

	public void setItemSeleccionado(ActividadTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ActividadTipo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setActividadTipoServicio(ActividadTipoService actividadTipoServicio) {
		this.actividadTipoServicio = actividadTipoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = actividadTipoServicio.consultarTodos();
		}
	}
}
