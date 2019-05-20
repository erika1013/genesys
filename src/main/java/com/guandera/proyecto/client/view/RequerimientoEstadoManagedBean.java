package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.RequerimientoEstadoService;
import com.guandera.proyecto.server.service.RequerimientoEstadoServiceImpl;
import com.guandera.proyecto.shared.model.RequerimientoEstado;

@ManagedBean(name = "requerimientoEstadoMB")
@SessionScoped
public class RequerimientoEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private RequerimientoEstadoService requerimientoEstadoServicio;
	private RequerimientoEstado itemSeleccionado;
	private List<RequerimientoEstado> listaItems;
	private boolean listing = false;

	public RequerimientoEstadoManagedBean() {
		requerimientoEstadoServicio = new RequerimientoEstadoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			requerimientoEstadoServicio.actualizar(itemSeleccionado);
			mensajeInfo("RequerimientoEstadoUpdated");
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

			requerimientoEstadoServicio.crear(itemSeleccionado);
			mensajeInfo("RequerimientoEstadoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		requerimientoEstadoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public RequerimientoEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<RequerimientoEstado> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = requerimientoEstadoServicio.consultarTodos();
		}
		return listaItems;
	}

	public RequerimientoEstadoService getRequerimientoEstadoServicio() {
		return requerimientoEstadoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new RequerimientoEstado();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarRequerimientoEstado() {
		setListing(true);
		listaItems = requerimientoEstadoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new RequerimientoEstado();
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

	public void setItemSeleccionado(RequerimientoEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<RequerimientoEstado> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setRequerimientoEstadoServicio(RequerimientoEstadoService requerimientoEstadoServicio) {
		this.requerimientoEstadoServicio = requerimientoEstadoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = requerimientoEstadoServicio.consultarTodos();
		}
	}
}
