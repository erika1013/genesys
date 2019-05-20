package com.guandera.talento.aspirante.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.aspirante.client.service.AspiranteCambioEstadoService;
import com.guandera.talento.aspirante.server.service.AspiranteCambioEstadoServiceImpl;
import com.guandera.talento.aspirante.shared.model.AspiranteCambioEstado;

@ManagedBean(name = "AspiranteCambioEstadoCambioEstadoMB")
@SessionScoped
public class AspiranteCambioEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private AspiranteCambioEstado itemSeleccionado;
	private List<AspiranteCambioEstado> listaItems;
	private boolean listingAspiranteCambioEstado = false;
	private AspiranteCambioEstadoService servicio;

	public AspiranteCambioEstadoManagedBean() {
		servicio = new AspiranteCambioEstadoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new AspiranteCambioEstado();
		return "Crear";
	}

	public String prepararEdicion() {

		return "Editar";
	}

	public String crear() {

		try {
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.crear(itemSeleccionado);
			mensajeInfo("AspiranteCambioEstadoCreated");
			return prepararCreacion();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String actualizar() {
		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			servicio.actualizar(itemSeleccionado);
			mensajeInfo("AspiranteCambioEstadoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingAspiranteCambioEstado = false;
		return prepararLista();
	}

	public AspiranteCambioEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(AspiranteCambioEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<AspiranteCambioEstado> getListaItems() {

		if (!listingAspiranteCambioEstado) {
			listingAspiranteCambioEstado = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<AspiranteCambioEstado> listaItems) {
		this.listaItems = listaItems;
	}

}
