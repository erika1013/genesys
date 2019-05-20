package com.guandera.talento.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.client.service.FormacionEstadoService;
import com.guandera.talento.server.service.FormacionEstadoServiceImpl;
import com.guandera.talento.shared.model.FormacionEstado;

@ManagedBean(name = "FormacionEstadoMB")
@SessionScoped
public class FormacionEstadoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private FormacionEstado itemSeleccionado;
	private List<FormacionEstado> listaItems;
	private boolean listingFormacionEstado = false;
	private FormacionEstadoService servicio;

	public FormacionEstadoManagedBean() {
		servicio = new FormacionEstadoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new FormacionEstado();
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
			mensajeInfo("FormacionEstadoCreated");
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
			mensajeInfo("FormacionEstadoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingFormacionEstado = false;
		return prepararLista();
	}

	public FormacionEstado getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(FormacionEstado itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<FormacionEstado> getListaItems() {

		if (!listingFormacionEstado) {
			listingFormacionEstado = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<FormacionEstado> listaItems) {
		this.listaItems = listaItems;
	}

}
