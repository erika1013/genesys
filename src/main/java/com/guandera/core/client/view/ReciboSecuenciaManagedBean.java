package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.ReciboSecuenciaService;
import com.guandera.core.server.service.ReciboSecuenciaServiceImpl;
import com.guandera.core.shared.model.ReciboSecuencia;

@ManagedBean(name = "reciboSecuenciaMB")
@SessionScoped
public class ReciboSecuenciaManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private ReciboSecuenciaService reciboSecuenciaServicio;
	private ReciboSecuencia itemSeleccionado;
	private List<ReciboSecuencia> listaItems;
	private boolean listing = false;

	public ReciboSecuenciaManagedBean() {
		reciboSecuenciaServicio = new ReciboSecuenciaServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			reciboSecuenciaServicio.actualizar(itemSeleccionado);
			mensajeInfo("ReciboSecuenciaUpdated");
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

			reciboSecuenciaServicio.crear(itemSeleccionado);
			mensajeInfo("ReciboSecuenciaCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		reciboSecuenciaServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public ReciboSecuencia getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<ReciboSecuencia> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = reciboSecuenciaServicio.consultarTodos();
		}
		return listaItems;
	}

	public ReciboSecuenciaService getReciboSecuenciaServicio() {
		return reciboSecuenciaServicio;
	}

	private void inicializar() {

		itemSeleccionado = new ReciboSecuencia();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarReciboSecuencia() {
		setListing(true);
		listaItems = reciboSecuenciaServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new ReciboSecuencia();
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

	public void setItemSeleccionado(ReciboSecuencia itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<ReciboSecuencia> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setReciboSecuenciaServicio(ReciboSecuenciaService reciboSecuenciaServicio) {
		this.reciboSecuenciaServicio = reciboSecuenciaServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = reciboSecuenciaServicio.consultarTodos();
		}
	}
}
