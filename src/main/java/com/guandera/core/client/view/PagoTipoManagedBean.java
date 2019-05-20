package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.server.service.PagoTipoServiceImpl;
import com.guandera.core.shared.model.PagoTipo;
import com.guandera.talento.nomina.client.service.PagoTipoService;

@ManagedBean(name = "pagoTipoMB")
@SessionScoped
public class PagoTipoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private PagoTipoService pagoTipoServicio;
	private PagoTipo itemSeleccionado;
	private List<PagoTipo> listaItems;
	private boolean listing = false;

	public PagoTipoManagedBean() {
		pagoTipoServicio = new PagoTipoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			pagoTipoServicio.actualizar(itemSeleccionado);
			mensajeInfo("PagoTipoUpdated");
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
			itemSeleccionado.setPagoTipoNumero(pagoTipoServicio.siguienteRegistro());
			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			pagoTipoServicio.crear(itemSeleccionado);
			mensajeInfo("PagoTipoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		pagoTipoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public PagoTipo getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<PagoTipo> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = pagoTipoServicio.consultarTodos();
		}
		return listaItems;
	}

	public PagoTipoService getPagoTipoServicio() {
		return pagoTipoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new PagoTipo();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarPagoTipo() {
		setListing(true);
		listaItems = pagoTipoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new PagoTipo();
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

	public void setItemSeleccionado(PagoTipo itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<PagoTipo> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setPagoTipoServicio(PagoTipoService pagoTipoServicio) {
		this.pagoTipoServicio = pagoTipoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = pagoTipoServicio.consultarTodos();
		}
	}
}
