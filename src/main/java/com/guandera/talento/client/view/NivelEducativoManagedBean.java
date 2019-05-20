package com.guandera.talento.client.view;

import com.guandera.talento.shared.model.NivelEducativo;
import com.guandera.talento.shared.model.NivelEducativo;

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

@ManagedBean(name = "NivelEducativoMB")
@SessionScoped
public class NivelEducativoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private NivelEducativo itemSeleccionado;
	private List<NivelEducativo> listaItems;
	private boolean listingNivelEducativo = false;
	private NivelEducativoService servicio;

	public NivelEducativoManagedBean() {
		servicio = new NivelEducativoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new NivelEducativo();
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
			mensajeInfo("NivelEducativoCreated");
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
			mensajeInfo("NivelEducativoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingNivelEducativo = false;
		return prepararLista();
	}

	public NivelEducativo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(NivelEducativo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<NivelEducativo> getListaItems() {

		if (!listingNivelEducativo) {
			listingNivelEducativo = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<NivelEducativo> listaItems) {
		this.listaItems = listaItems;
	}

}
