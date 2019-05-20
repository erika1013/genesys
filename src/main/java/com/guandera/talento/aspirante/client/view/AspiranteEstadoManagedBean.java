package com.guandera.talento.aspirante.client.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.aspirante.client.service.AspiranteEstadoService;
import com.guandera.talento.aspirante.server.service.AspiranteEstadoServiceImpl;
import com.guandera.talento.aspirante.shared.model.AspiranteEstado;
import com.guandera.talento.client.service.*;
import com.guandera.talento.server.service.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "AspiranteEstadoMB")
@SessionScoped
public class AspiranteEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private AspiranteEstado itemSeleccionado;
	private List<AspiranteEstado> listaItems;
	private boolean listingAspiranteEstado = false;
	private AspiranteEstadoService servicio;

	public AspiranteEstadoManagedBean() {
		servicio = new AspiranteEstadoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new AspiranteEstado();
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
			mensajeInfo("AspiranteEstadoCreated");
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
			mensajeInfo("AspiranteEstadoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingAspiranteEstado = false;
		return prepararLista();
	}

	public AspiranteEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(AspiranteEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<AspiranteEstado> getListaItems() {

		if (!listingAspiranteEstado) {
			listingAspiranteEstado = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<AspiranteEstado> listaItems) {
		this.listaItems = listaItems;
	}

}
