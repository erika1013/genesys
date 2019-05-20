package com.guandera.proyecto.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.proyecto.client.service.RequerimientoTipoService;
import com.guandera.proyecto.server.service.RequerimientoTipoServiceImpl;
import com.guandera.proyecto.shared.model.RequerimientoTipo;

@ManagedBean(name = "requerimientoTipoMB")
@SessionScoped
public class RequerimientoTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private RequerimientoTipoService requerimientoTipoServicio;
	private RequerimientoTipo itemSeleccionado;
	private List<RequerimientoTipo> listaItems;
	private boolean listing = false;

	public RequerimientoTipoManagedBean() {
		requerimientoTipoServicio = new RequerimientoTipoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			requerimientoTipoServicio.actualizar(itemSeleccionado);
			mensajeInfo("RequerimientoTipoUpdated");
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

			requerimientoTipoServicio.crear(itemSeleccionado);
			mensajeInfo("RequerimientoTipoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		requerimientoTipoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public RequerimientoTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<RequerimientoTipo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = requerimientoTipoServicio.consultarTodos();
		}
		return listaItems;
	}

	public RequerimientoTipoService getRequerimientoTipoServicio() {
		return requerimientoTipoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new RequerimientoTipo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarRequerimientoTipo() {
		setListing(true);
		listaItems = requerimientoTipoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new RequerimientoTipo();
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

	public void setItemSeleccionado(RequerimientoTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<RequerimientoTipo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setRequerimientoTipoServicio(RequerimientoTipoService requerimientoTipoServicio) {
		this.requerimientoTipoServicio = requerimientoTipoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = requerimientoTipoServicio.consultarTodos();
		}
	}
}
