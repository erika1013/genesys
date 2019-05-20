package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.ProyectoTipoService;
import com.guandera.proyecto.server.service.ProyectoTipoServiceImpl;
import com.guandera.proyecto.shared.model.ProyectoTipo;

@ManagedBean(name = "proyectoTipoMB")
@SessionScoped
public class ProyectoTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ProyectoTipoService proyectoTipoServicio;
	private ProyectoTipo itemSeleccionado;
	private List<ProyectoTipo> listaItems;
	private boolean listing = false;

	public ProyectoTipoManagedBean() {
		proyectoTipoServicio = new ProyectoTipoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			proyectoTipoServicio.actualizar(itemSeleccionado);
			mensajeInfo("ProyectoTipoUpdated");
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

			proyectoTipoServicio.crear(itemSeleccionado);
			mensajeInfo("ProyectoTipoCreated");
			inicializar();
			verificarLista();
			return prepararLista();

		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		proyectoTipoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ProyectoTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ProyectoTipo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = proyectoTipoServicio.consultarTodos();
		}
		return listaItems;
	}

	public ProyectoTipoService getProyectoTipoServicio() {
		return proyectoTipoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ProyectoTipo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarProyectoTipo() {
		setListing(true);
		listaItems = proyectoTipoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ProyectoTipo();
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

	public void setItemSeleccionado(ProyectoTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ProyectoTipo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setProyectoTipoServicio(ProyectoTipoService proyectoTipoServicio) {
		this.proyectoTipoServicio = proyectoTipoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = proyectoTipoServicio.consultarTodos();
		}
	}
}
