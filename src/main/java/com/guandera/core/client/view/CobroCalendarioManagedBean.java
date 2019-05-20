package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.CobroCalendarioService;
import com.guandera.core.server.service.CobroCalendarioServiceImpl;
import com.guandera.core.shared.model.CobroCalendario;

@ManagedBean(name = "cobroCalendarioMB")
@SessionScoped
public class CobroCalendarioManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private CobroCalendarioService cobroCalendarioServicio;
	private CobroCalendario itemSeleccionado;
	private List<CobroCalendario> listaItems;
	private boolean listing = false;

	public CobroCalendarioManagedBean() {
		cobroCalendarioServicio = new CobroCalendarioServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			cobroCalendarioServicio.actualizar(itemSeleccionado);
			mensajeInfo("CobroCalendarioUpdated");
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

			if (!cobroCalendarioServicio.existePeriodo(itemSeleccionado.getPeriodo())) {

				itemSeleccionado.setUsuarioCreacion(usuario());
				itemSeleccionado.setFechaCreacion(new Date());
				itemSeleccionado.setUsuarioModificacion(usuario());
				itemSeleccionado.setFechaModificacion(new Date());

				cobroCalendarioServicio.crear(itemSeleccionado);
				mensajeInfo("CobroCalendarioCreated");

				listing = false;
				inicializar();
				verificarLista();
				return prepararLista();

			} else {
				error("Error: el periodo ya se encuentra parametrizado!!");
				return null;

			}
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		cobroCalendarioServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public CobroCalendario getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<CobroCalendario> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = cobroCalendarioServicio.consultarTodos();
		}
		return listaItems;
	}

	public CobroCalendarioService getCobroCalendarioServicio() {
		return cobroCalendarioServicio;
	}

	private void inicializar() {

		itemSeleccionado = new CobroCalendario();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarCobroCalendario() {
		setListing(true);
		listaItems = cobroCalendarioServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new CobroCalendario();
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

	public void setItemSeleccionado(CobroCalendario itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<CobroCalendario> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setCobroCalendarioServicio(CobroCalendarioService cobroCalendarioServicio) {
		this.cobroCalendarioServicio = cobroCalendarioServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = cobroCalendarioServicio.consultarTodos();
		}
	}
}
