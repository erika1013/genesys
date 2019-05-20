package com.guandera.talento.client.view;

import com.guandera.talento.shared.model.CompetenciaTipo;
import com.guandera.talento.shared.model.CompetenciaTipo;

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

@ManagedBean(name = "CompetenciaTipoMB")
@SessionScoped
public class CompetenciaTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CompetenciaTipo itemSeleccionado;
	private List<CompetenciaTipo> listaItems;
	private boolean listingCompetenciaTipo = false;
	private CompetenciaTipoService servicio;

	public CompetenciaTipoManagedBean() {
		servicio = new CompetenciaTipoServiceImpl();

	}

	public String prepararLista() {

		return "Lista";
	}

	public String prepararConsulta() {

		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CompetenciaTipo();
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
			mensajeInfo("CompetenciaTipoCreated");
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
			mensajeInfo("CompetenciaTipoUpdated");
			return prepararConsulta();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {
		servicio.eliminar(itemSeleccionado);
		listingCompetenciaTipo = false;
		return prepararLista();
	}

	public CompetenciaTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public void setItemSeleccionado(CompetenciaTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public List<CompetenciaTipo> getListaItems() {

		if (!listingCompetenciaTipo) {
			listingCompetenciaTipo = true;
			listaItems = servicio.consultarTodos();
		}
		return listaItems;
	}

	public void setListaItems(List<CompetenciaTipo> listaItems) {
		this.listaItems = listaItems;
	}

}
