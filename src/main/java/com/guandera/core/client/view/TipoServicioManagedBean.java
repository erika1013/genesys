package com.guandera.core.client.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.guandera.core.client.service.TipoServicioService;
import com.guandera.core.server.service.TipoServicioServiceImpl;
import com.guandera.core.shared.model.TipoCobro;
import com.guandera.core.shared.model.TipoServicio;

@ManagedBean(name = "tipoServicioMB")
@SessionScoped
public class TipoServicioManagedBean extends BaseManagedBean implements Serializable {

	private static final long serialVersionUID = 10L;

	private TipoServicioService tipoServicioServicio;
	private TipoServicio itemSeleccionado;
	private List<TipoServicio> listaItems;
	private boolean listing = false;

	private Long tipoCobroid;
	private List<SelectItem> tipoCobroItems;
	private boolean listingTipoCobro = false;

	public TipoServicioManagedBean() {
		tipoServicioServicio = new TipoServicioServiceImpl();

		inicializar();
	}

	public String actualizar() {

		try {

			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			TipoCobro tipoCobro = new TipoCobro();
			tipoCobro.setTipoCobroid(tipoCobroid);
			itemSeleccionado.setTipoCobro(tipoCobro);

			tipoServicioServicio.actualizar(itemSeleccionado);
			mensajeInfo("TipoServicioUpdated");
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

			TipoCobro tipoCobro = new TipoCobro();
			tipoCobro.setTipoCobroid(tipoCobroid);

			itemSeleccionado.setUsuarioCreacion(usuario());
			itemSeleccionado.setFechaCreacion(new Date());
			itemSeleccionado.setUsuarioModificacion(usuario());
			itemSeleccionado.setFechaModificacion(new Date());

			itemSeleccionado.setTipoCobro(tipoCobro);

			tipoServicioServicio.crear(itemSeleccionado);
			mensajeInfo("TipoServicioCreated");
			inicializar();
			verificarLista();
			return prepararLista();
		} catch (Exception e) {
			mensajeError("PersistenceErrorOccured");
			return null;
		}

	}

	public String eliminar() {

		tipoServicioServicio.eliminar(itemSeleccionado);
		listing = false;
		return prepararLista();
	}

	public TipoServicio getItemSeleccionado() {
		return itemSeleccionado;
	}

	public List<TipoServicio> getListaItems() {
		if (!listing) {
			listing = true;

			listaItems = tipoServicioServicio.consultarTodos();
		}
		return listaItems;
	}

	public TipoServicioService getTipoServicioServicio() {
		return tipoServicioServicio;
	}

	private void inicializar() {

		itemSeleccionado = new TipoServicio();

	}

	public boolean isListing() {
		return listing;
	}

	public void listarTipoServicio() {
		setListing(true);
		listaItems = tipoServicioServicio.consultarTodos();
	}

	public String prepararConsulta() {
		return "Detalle";
	}

	public String prepararCreacion() {
		itemSeleccionado = new TipoServicio();

		tipoCobroid = (long) 0;

		return "Crear";
	}

	public String prepararEdicion() {

		if (itemSeleccionado.getTipoCobro().getTipoCobroid() != null) {
			tipoCobroid = itemSeleccionado.getTipoCobro().getTipoCobroid();
		} else {
			tipoCobroid = (long) 0;
		}

		return "Editar";
	}

	public String prepararLista() {
		return "Lista";
	}

	@PostConstruct
	public void prerender() {
		inicializar();

	}

	public void setItemSeleccionado(TipoServicio itemSeleccionado) {
		this.itemSeleccionado = itemSeleccionado;
	}

	public void setListaItems(List<TipoServicio> listaItems) {
		this.listaItems = listaItems;
	}

	public void setListing(boolean listing) {
		this.listing = listing;
	}

	public void setTipoServicioServicio(TipoServicioService tipoServicioServicio) {
		this.tipoServicioServicio = tipoServicioServicio;
	}

	private void verificarLista() {
		if (listing) {
			this.listaItems = tipoServicioServicio.consultarTodos();
		}
	}

	public Long getTipoCobroid() {
		return tipoCobroid;
	}

	public void setTipoCobroid(Long tipoCobroid) {
		this.tipoCobroid = tipoCobroid;
	}

	public List<SelectItem> getTipoCobroItems() {

		if (!listingTipoCobro) {
			listingTipoCobro = true;
			tipoCobroItems = new ArrayList<SelectItem>();
			List<TipoCobro> tipoCobroList = tipoServicioServicio.consultarTipoCobro();

			for (TipoCobro tipoCobro : tipoCobroList) {
				tipoCobroItems.add(new SelectItem(tipoCobro.getTipoCobroid(), tipoCobro.getTipoCobronombre()));

			}
		}

		return tipoCobroItems;
	}

	public void setTipoCobroItems(List<SelectItem> tipoCobroItems) {
		this.tipoCobroItems = tipoCobroItems;
	}

}
