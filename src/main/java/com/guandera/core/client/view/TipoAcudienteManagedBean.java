package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.TipoAcudienteService;
import com.guandera.core.server.service.TipoAcudienteServiceImpl;
import com.guandera.core.shared.model.TipoAcudiente;

@ManagedBean(name = "tipoAcudienteMB")
@SessionScoped
public class TipoAcudienteManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoAcudienteService tipoAcudienteServicio;
	private TipoAcudiente itemSeleccionado;
	private List<TipoAcudiente> listaItems;

	private boolean listing = false;

	public TipoAcudienteManagedBean() {
		tipoAcudienteServicio = new TipoAcudienteServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tipoAcudienteServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoAcudienteUpdated");
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

			tipoAcudienteServicio.crear(itemSeleccionado);
			mensajeInfo("TipoAcudienteCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoAcudienteServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoAcudiente getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoAcudiente> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = tipoAcudienteServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoAcudienteService getTipoAcudienteServicio() {
		return tipoAcudienteServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoAcudiente();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoAcudiente() {
		setListing(true);
		listaItems = tipoAcudienteServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoAcudiente();
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

	public void setItemSeleccionado(TipoAcudiente itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoAcudiente> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoAcudienteServicio(TipoAcudienteService tipoAcudienteServicio) {
		this.tipoAcudienteServicio = tipoAcudienteServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoAcudienteServicio.consultarTodos();
		}
	}
}
