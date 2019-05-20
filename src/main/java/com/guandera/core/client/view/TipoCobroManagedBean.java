package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.guandera.core.client.service.TipoCobroService;
import com.guandera.core.server.service.TipoCobroServiceImpl;
import com.guandera.core.shared.model.TipoCobro;

@ManagedBean(name = "tipoCobroMB")
@SessionScoped
public class TipoCobroManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoCobroService tipoCobroServicio;
	private TipoCobro itemSeleccionado;
	private List<TipoCobro> listaItems;
	private boolean listing = false;

	public TipoCobroManagedBean() {
		tipoCobroServicio = new TipoCobroServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			tipoCobroServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoCobroUpdated");
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

			tipoCobroServicio.crear(itemSeleccionado);
			mensajeInfo("TipoCobroCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoCobroServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoCobro getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoCobro> getListaItems() {

		if (!listing) {
			listing = true;
			listaItems = tipoCobroServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoCobroService getTipoCobroServicio() {
		return tipoCobroServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoCobro();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoCobro() {
		setListing(true);
		listaItems = tipoCobroServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoCobro();
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

	public void setItemSeleccionado(TipoCobro itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoCobro> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoCobroServicio(TipoCobroService tipoCobroServicio) {
		this.tipoCobroServicio = tipoCobroServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoCobroServicio.consultarTodos();
		}
	}
}
