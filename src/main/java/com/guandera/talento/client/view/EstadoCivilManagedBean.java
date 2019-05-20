package com.guandera.talento.client.view;

import com.guandera.talento.shared.model.EstadoCivil;
import com.guandera.talento.shared.model.EstadoCivil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.client.service.*;
import com.guandera.talento.server.service.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "EstadoCivilMB")
@SessionScoped
public class EstadoCivilManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private EstadoCivil itemSeleccionado;
	private List<EstadoCivil> listaItems;
	private boolean listingEstadoCivil = false;
	private EstadoCivilService servicio;

	public EstadoCivilManagedBean() {
		servicio = new EstadoCivilServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new EstadoCivil();
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
			mensajeInfo("EstadoCivilCreated");
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
			mensajeInfo("EstadoCivilUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingEstadoCivil = false;
		return prepararLista();
	}

	public EstadoCivil getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(EstadoCivil itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<EstadoCivil> getListaItems() {

		if (!listingEstadoCivil) {
			listingEstadoCivil = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<EstadoCivil> listaItems) {
		this.listaItems = listaItems;
	}

}
