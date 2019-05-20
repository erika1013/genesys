package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.TipoIdentificacionService;
import com.guandera.core.server.service.TipoIdentificacionServiceImpl;
import com.guandera.core.shared.model.TipoIdentificacion;

@ManagedBean(name = "tipoIdentificacionMB")
@SessionScoped
public class TipoIdentificacionManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoIdentificacionService tipoIdentificacionServicio;
	private TipoIdentificacion itemSeleccionado;
	private List<TipoIdentificacion> listaItems;
	private boolean listing = false;

	public TipoIdentificacionManagedBean() {
		tipoIdentificacionServicio = new TipoIdentificacionServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tipoIdentificacionServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoIdentificacionUpdated");
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

			tipoIdentificacionServicio.crear(itemSeleccionado);
			mensajeInfo("TipoIdentificacionCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoIdentificacionServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoIdentificacion getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoIdentificacion> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = tipoIdentificacionServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoIdentificacionService getTipoIdentificacionServicio() {
		return tipoIdentificacionServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoIdentificacion();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoIdentificacion() {
		setListing(true);
		listaItems = tipoIdentificacionServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoIdentificacion();
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

	public void setItemSeleccionado(TipoIdentificacion itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoIdentificacion> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoIdentificacionServicio(TipoIdentificacionService tipoIdentificacionServicio) {
		this.tipoIdentificacionServicio = tipoIdentificacionServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoIdentificacionServicio.consultarTodos();
		}
	}
}
