package com.guandera.talento.empleado.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.TipoContratoService;
import com.guandera.core.client.view.BaseManagedBean;
import com.guandera.talento.empleado.server.service.TipoContratoServiceImpl;
import com.guandera.talento.empleado.shared.model.TipoContrato;

@ManagedBean(name = "tipoContratoMB")
@SessionScoped
public class TipoContratoManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoContratoService tipoContratoServicio;
	private TipoContrato itemSeleccionado;
	private List<TipoContrato> listaItems;
	private boolean listing = false;

	public TipoContratoManagedBean() {
		tipoContratoServicio = new TipoContratoServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tipoContratoServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoContratoUpdated");
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
			tipoContratoServicio.crear(itemSeleccionado);
			mensajeInfo("TipoContratoCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoContratoServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoContrato getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoContrato> getListaItems() {
		if (!listing) {
			listing = true;
			listaItems = tipoContratoServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoContratoService getTipoContratoServicio() {
		return tipoContratoServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoContrato();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoContrato() {
		setListing(true);
		listaItems = tipoContratoServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoContrato();
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

	public void setItemSeleccionado(TipoContrato itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoContrato> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoContratoServicio(TipoContratoService tipoContratoServicio) {
		this.tipoContratoServicio = tipoContratoServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoContratoServicio.consultarTodos();
		}
	}
}
