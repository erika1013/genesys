package com.guandera.core.client.view;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.shared.model.Ciudad;
import com.guandera.talento.client.service.*;
import com.guandera.talento.server.service.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "CiudadMB")
@SessionScoped
public class CiudadManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private Ciudad itemSeleccionado;
	private List<Ciudad> listaItems;
	private boolean listingCiudad = false;
	private CiudadService servicio;

	public CiudadManagedBean() {
		servicio = new CiudadServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new Ciudad();
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
			mensajeInfo("CiudadCreated");
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
			mensajeInfo("CiudadUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingCiudad = false;
		return prepararLista();
	}

	public Ciudad getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(Ciudad itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<Ciudad> getListaItems() {

		if (!listingCiudad) {
			listingCiudad = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<Ciudad> listaItems) {
		this.listaItems = listaItems;
	}

}
