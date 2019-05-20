package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.GastoConceptoService;
import com.guandera.core.server.service.GastoConceptoServiceImpl;
import com.guandera.core.shared.model.GastoConcepto;

@ManagedBean(name = "gastoConceptoMB")
@SessionScoped
public class GastoConceptoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private GastoConceptoService gastoConceptoServicio;
	private GastoConcepto itemSeleccionado;
	private List<GastoConcepto> listaItems;
	private boolean listing = false;

	public GastoConceptoManagedBean() {
		gastoConceptoServicio = new GastoConceptoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			gastoConceptoServicio.actualizar(itemSeleccionado);
			mensajeInfo("GastoConceptoUpdated");
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

			if (!gastoConceptoServicio.exiteCodigo(itemSeleccionado.getGastoConceptocodigo())) {

				itemSeleccionado.setUsuarioCreacion(usuario());
				itemSeleccionado.setFechaCreacion(new Date());
				itemSeleccionado.setUsuarioModificacion(usuario());
				itemSeleccionado.setFechaModificacion(new Date());

				gastoConceptoServicio.crear(itemSeleccionado);
				mensajeInfo("GastoConceptoCreated");
				inicializar();
				verificarLista();
				return prepararLista();
			} else {

				errorTexto("El c�digo del concepto ya existe");
				return null;

			}
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		gastoConceptoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public GastoConcepto getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<GastoConcepto> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = gastoConceptoServicio.consultarTodos();
		}
		return listaItems;
	}

	public GastoConceptoService getGastoConceptoServicio() {
		return gastoConceptoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new GastoConcepto();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarGastoConcepto() {
		setListing(true);
		listaItems = gastoConceptoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new GastoConcepto();
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

	public void setItemSeleccionado(GastoConcepto itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<GastoConcepto> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setGastoConceptoServicio(GastoConceptoService gastoConceptoServicio) {
		this.gastoConceptoServicio = gastoConceptoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = gastoConceptoServicio.consultarTodos();
		}
	}
}
