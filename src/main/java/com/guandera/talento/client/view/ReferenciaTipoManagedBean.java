package com.guandera.talento.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.client.service.ReferenciaTipoService;
import com.guandera.talento.server.service.ReferenciaTipoServiceImpl;
import com.guandera.talento.shared.model.ReferenciaTipo;

@ManagedBean(name = "AspiranteReferenciaTipoMB")
@SessionScoped
public class ReferenciaTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ReferenciaTipo itemSeleccionado;
	private List<ReferenciaTipo> listaItems;
	private boolean listingAspiranteReferenciaTipo = false;
	private ReferenciaTipoService servicio;

	public ReferenciaTipoManagedBean() {
		servicio = new ReferenciaTipoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ReferenciaTipo();
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
			mensajeInfo("AspiranteReferenciaTipoCreated");
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
			mensajeInfo("AspiranteReferenciaTipoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingAspiranteReferenciaTipo = false;
		return prepararLista();
	}

	public ReferenciaTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(ReferenciaTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<ReferenciaTipo> getListaItems() {

		if (!listingAspiranteReferenciaTipo) {
			listingAspiranteReferenciaTipo = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<ReferenciaTipo> listaItems) {
		this.listaItems = listaItems;
	}

}
