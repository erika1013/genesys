package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.TipoPersonaService;
import com.guandera.core.server.service.TipoPersonaServiceImpl;
import com.guandera.core.shared.model.TipoPersona;

@ManagedBean(name = "tipoPersonaMB")
@SessionScoped
public class TipoPersonaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoPersonaService tipoPersonaServicio;
	private TipoPersona itemSeleccionado;
	private List<TipoPersona> listaItems;
	private boolean listing = false;

	public TipoPersonaManagedBean() {
		tipoPersonaServicio = new TipoPersonaServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tipoPersonaServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoPersonaUpdated");
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

			tipoPersonaServicio.crear(itemSeleccionado);
			mensajeInfo("TipoPersonaCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoPersonaServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoPersona getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoPersona> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = tipoPersonaServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoPersonaService getTipoPersonaServicio() {
		return tipoPersonaServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoPersona();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoPersona() {
		setListing(true);
		listaItems = tipoPersonaServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoPersona();
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

	public void setItemSeleccionado(TipoPersona itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoPersona> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoPersonaServicio(TipoPersonaService tipoPersonaServicio) {
		this.tipoPersonaServicio = tipoPersonaServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoPersonaServicio.consultarTodos();
		}
	}
}
